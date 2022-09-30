package it.bitrock.springbootssojwtdemo.config;

import it.bitrock.springbootssojwtdemo.filter.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("CustomAuthenticationProvider")
    AuthenticationProvider authenticationProvider;

//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement(
//                        c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling(
//                        c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/curriculum").hasRole("HR")
//                .antMatchers(HttpMethod.GET, "/curriculum/{\\d+}").hasRole("DEV")
//                .antMatchers(HttpMethod.DELETE, "/curriculum/{\\d+}").hasAuthority("curriculum:write")
//                .anyRequest().authenticated()
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement(
                        c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(
                        c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/login").permitAll()
                        .antMatchers("/curriculum").hasRole("HR")
                        .antMatchers(HttpMethod.GET, "/curriculum/{\\d+}").hasRole("DEV")
                        .antMatchers(HttpMethod.DELETE, "/curriculum/{\\d+}").hasAuthority("curriculum:write")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
