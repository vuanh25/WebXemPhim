package com.example.webxemphim.Security;



import com.example.webxemphim.Services.UserDetailsServicelmpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServicelmpl();
    }

    @Bean
    protected DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }




    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/");
    }
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
        http.authorizeHttpRequests(requests -> requests
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/API/**").permitAll()
                        .antMatchers("/photos/**").permitAll()
                        .antMatchers("/́́́́́").permitAll()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/process_register").permitAll()
                        .antMatchers("/forgot_password").permitAll()
                        .antMatchers("/reset_password").permitAll()
                        .antMatchers("/register_request").permitAll()
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
