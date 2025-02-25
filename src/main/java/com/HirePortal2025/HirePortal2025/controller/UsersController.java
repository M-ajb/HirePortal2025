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

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }


    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model) {

        Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail());

        if(optionalUsers.isPresent()){
            model.addAttribute("error" , "Email already registered, try to login or register with other email.");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNew(users);
        return "redirect:/dashboard/";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";

    }
}



