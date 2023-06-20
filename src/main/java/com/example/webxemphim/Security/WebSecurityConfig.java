package com.example.webxemphim.Security;


import com.example.webxemphim.Security.jwt.AuthEntryPointJwt;
import com.example.webxemphim.Security.jwt.AuthTokenFilter;
import com.example.webxemphim.Services.UserDetailsServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServicelmpl userDetailsServicelmpl;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter(JwtUtil jwtUtil, UserDetailsService customUserDetailsService) {
//        return new AuthTokenFilter(jwtUtil, customUserDetailsService);
//    }




    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsServicelmpl);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }






    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
        http.authorizeHttpRequests(requests -> requests
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/API/**").permitAll()
                        .antMatchers("/photos/**").permitAll()
                        .antMatchers("/videos/**").permitAll()
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
