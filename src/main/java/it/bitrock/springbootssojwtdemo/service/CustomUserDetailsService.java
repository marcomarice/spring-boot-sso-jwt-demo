package it.bitrock.springbootssojwtdemo.service;

import it.bitrock.springbootssojwtdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static it.bitrock.springbootssojwtdemo.service.AccountRole.DEV;
import static it.bitrock.springbootssojwtdemo.service.AccountRole.HR;

@Service
@Qualifier("CustomUserDetailsService")
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("username".equals(username)) {
//            return new User(username, encoder.encode("password"),
//                    List.of(new SimpleGrantedAuthority("ROLE_USER")));
//        }
//        throw new UsernameNotFoundException(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getApplicationUsers()
                .stream()
                .filter(userDetailsImpl -> username.equals(userDetailsImpl.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private List<UserDetailsImpl> getApplicationUsers() {
        return accountRepository.findAll().stream()
                .map(account -> new UserDetailsImpl(account.getUsername(),
                        passwordEncoder.encode("password"),
                        getApplicationUserRole(account.getRole().getName()),
                        true,
                        true,
                        true,
                        true)).collect(Collectors.toList());
    }

    private Set<SimpleGrantedAuthority> getApplicationUserRole(String role) {
        if (role.equals("DEV")) {
            return DEV.getGrantedAuthorities();
        }
        return HR.getGrantedAuthorities();
    }
}
