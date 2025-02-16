package com.HirePortal2025.HirePortal2025.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The `CustomAuthenticationSuccessHandler` class handles successful authentication events.
 * It implements the `AuthenticationSuccessHandler` interface to define custom behavior upon successful login.
 *
 * Purpose:
 * - To handle actions that should be taken when a user successfully logs in.
 *
 * Key Functionalities:
 * - `onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)`:
 *   This method is called when a user successfully logs in. It retrieves the username and roles of the authenticated user
 *   and redirects them to the appropriate dashboard based on their role.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       String username = userDetails.getUsername();
        System.out.println("The username " + username + " is logged in.");
        boolean hasJobSeekerRole = authentication.getAuthorities().stream().anyMatch((r -> r.getAuthority().equals("Job Seeker")));
        boolean hasRecruiterRole = authentication.getAuthorities().stream().anyMatch((r -> r.getAuthority().equals("Recruiter")));

        if(hasRecruiterRole || hasJobSeekerRole){
            response.sendRedirect("/dashboard/");
        }
    }
}
