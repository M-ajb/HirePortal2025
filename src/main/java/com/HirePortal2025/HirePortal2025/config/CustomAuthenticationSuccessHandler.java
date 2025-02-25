package com.HirePortal2025.HirePortal2025.config;

import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.services.UsersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The `CustomAuthenticationSuccessHandler` class implements the `AuthenticationSuccessHandler` interface to handle the successful authentication event.
 *
 * Purpose:
 * - To handle the successful authentication event.
 *
 * Key Functionalities:
 * - `onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)`: Handles the successful authentication event.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    @Lazy
    private UsersService usersService;

    /**
     * Handles the successful authentication event.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the authentication object containing the user's details
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet exception occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("The username " + username + " is logged in.");

        boolean hasJobSeekerRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("Job Seeker"));

        boolean hasRecruiterRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("Recruiter"));

        Users user = usersService.findByEmail(username);

        // Check if the user is a Job Seeker and if the session attribute "firstTimeLoginJobSeeker" is present
        Boolean isFirstLoginJobSeeker = (Boolean) request.getSession().getAttribute("firstTimeLoginJobSeeker");
        Boolean isFirstLoginRecruiter = (Boolean) request.getSession().getAttribute("firstTimeLoginRecruiter");

        if (hasJobSeekerRole && Boolean.TRUE.equals(isFirstLoginJobSeeker)) {
            request.getSession().removeAttribute("firstTimeLoginJobSeeker");
            response.sendRedirect("/job-seeker-profile/");
            return;
        }

        if (hasRecruiterRole && Boolean.TRUE.equals(isFirstLoginRecruiter)) {
            request.getSession().removeAttribute("firstTimeLoginRecruiter"); // Verwijder session-attribuut
            response.sendRedirect("/recruiter-profile/"); // Stuur naar profielpagina voor Recruiters
            return;
        }

        response.sendRedirect("/dashboard/");
    }
}
