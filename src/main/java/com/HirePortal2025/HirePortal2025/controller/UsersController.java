package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.entity.UsersType;
import com.HirePortal2025.HirePortal2025.services.UsersService;
import com.HirePortal2025.HirePortal2025.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
/**
 * The `UsersController` class handles HTTP requests related to user registration, login, and logout.
 * It interacts with the `UsersService` and `UsersTypeService` to manage user data and types.
 *
 * Fields:
 * - `usersTypeService`: Service for managing user types.
 * - `usersService`: Service for managing user data.
 *
 * Purpose:
 * - To provide endpoints for user registration, login, and logout.
 *
 * Key Functionalities:
 * - `register(Model model)`: Handles GET requests for the registration page, populates the model with user types and a new user object.
 * - `userRegistration(@Valid Users users, Model model)`: Handles POST requests for user registration, checks if the email is already registered, and adds a new user if not.
 * - `login()`: Handles GET requests for the login page.
 * - `logout(HttpServletRequest request, HttpServletResponse response)`: Handles GET requests for logging out the user, invalidates the session, and redirects to the home page.
 */
@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;


    /**
     * Constructs a new `UsersController` with the specified services.
     *
     * @param usersTypeService the service for managing user types
     * @param usersService the service for managing user data
     */
    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;

    }

    /**
     * Displays the registration page with the user types.
     *
     * @param model the model to which attributes are added
     * @return the registration page
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("getAllTypes", usersTypeService.getAll());
        model.addAttribute("user", new Users());
        return "register";
    }


    /**
     * Registers a new user with the specified details.
     *
     * @param users the user entity to be registered
     * @param model the model to which attributes are added
     * @return the dashboard page
     */
    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model) {

        if (usersService.getUserByEmail(users.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already registered, try to login or register with another email.");
            model.addAttribute("getAllTypes", usersTypeService.getAll());
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNew(users);
        return "redirect:/dashboard/";
    }

    /**
     * Displays the login page.
     *
     * @return the login page
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    /**
     * Logs out the current user and redirects to the home page.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the home page
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/";
    }
}



