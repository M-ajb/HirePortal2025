package com.HirePortal2025.HirePortal2025.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Configuratieklasse voor applicatiebeveiliging.
 * Stelt toegangsregels in, definieert openbare URL-patronen
 * en configureert authenticatievereisten voor beveiligde resources.
 */
@Configuration
public class WebSecurityConfig {


    /**
     * Lijst met openbare URL-patronen die toegankelijk zijn zonder inloggen.
     * Inclusief statische bronnen (CSS, JS, etc.) en pagina's zoals registratie.
     */
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


    /**
     * Configureert het beveiligingsfilter om openbare URLs zonder authenticatie toe te staan
     * en overige verzoeken te beveiligen.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth->{
            auth.requestMatchers(publicUrl).permitAll();
            auth.anyRequest().authenticated();
        });
        return http.build();

    }
}
