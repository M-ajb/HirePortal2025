package com.HirePortal2025.HirePortal2025.config;

import com.HirePortal2025.HirePortal2025.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * The `WebSecurityConfig` class configures the security settings for the application.
 * It sets up authentication, authorization, and other security-related configurations.
 *
 * Fields:
 * - `customUserDetailsService`: Service for loading user-specific data.
 * - `customAuthenticationSuccessHandler`: Handler for successful authentication events.
 * - `publicUrl`: Array of URLs that are publicly accessible without authentication.
 *
 * Purpose:
 * - To configure security settings such as authentication providers, password encoding, and security filter chains.
 *
 * Key Functionalities:
 * - `securityFilterChain(HttpSecurity http)`: Configures the security filter chain, including authentication, authorization, login, and logout settings.
 * - `authenticationProvider()`: Sets up the authentication provider with a password encoder and user details service.
 * - `passwordEncoder()`: Provides a password encoder bean for encoding passwords.
 */
@Configuration
public class WebSecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    /**
     * Constructs a new `WebSecurityConfig` with the specified user details service and authentication success handler.
     *
     * @param customUserDetailsService the service for loading user-specific data
     * @param customAuthenticationSuccessHandler the handler for successful authentication events
     */
    @Autowired
    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }


    private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"};



    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth->{
            auth.requestMatchers(publicUrl).permitAll();
            auth.anyRequest().authenticated();
        });

        http.formLogin(form-> form.loginPage("/login").permitAll()
                        .successHandler(customAuthenticationSuccessHandler))
                .logout(logout->{
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/");
               }).cors(Customizer.withDefaults())
                .csrf(csrf-> csrf.disable());

        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
