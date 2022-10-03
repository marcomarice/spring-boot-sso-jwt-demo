package it.bitrock.springsecurityjwtdemo.service;

import it.bitrock.springsecurityjwtdemo.repository.AccountRepository;
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

import static it.bitrock.springsecurityjwtdemo.service.AccountRole.DEV;
import static it.bitrock.springsecurityjwtdemo.service.AccountRole.HR;

@Service
@Qualifier("CustomUserDetailsService")
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

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
                        true))
                .toList();
    }

    private Set<SimpleGrantedAuthority> getApplicationUserRole(String role) {
        if (role.equals("DEV")) {
            return DEV.getGrantedAuthorities();
        }
        return HR.getGrantedAuthorities();
    }
}
