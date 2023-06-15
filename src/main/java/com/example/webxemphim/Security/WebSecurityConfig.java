package com.example.webxemphim.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
        http.authorizeHttpRequests(requests -> requests
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/API/**").permitAll()
                        .antMatchers("/́́́́́").permitAll()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/process_register").permitAll()
                        .antMatchers("/forgot_password").permitAll()
                        .antMatchers("/reset_password").permitAll()
                        .antMatchers("/register_request").permitAll()
                        .antMatchers("/users/").hasAnyAuthority("USER", "CREATER", "EDITOR", "ADMIN")
                        .antMatchers("/books/").hasAnyAuthority("USER", "CREATER", "EDITOR", "ADMIN")
                        .antMatchers("/books/new").hasAnyAuthority("ADMIN", "CREATER")
                        .antMatchers("/books/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                        .antMatchers("/books/delete/**")
                        .hasAuthority("ADMIN").anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"))
                .csrf()
                .disable();
        return http.build();
    }
}
