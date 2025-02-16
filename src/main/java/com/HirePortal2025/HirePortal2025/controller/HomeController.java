package com.HirePortal2025.HirePortal2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * The `HomeController` class handles HTTP requests for the home page.
 *
 * Purpose:
 * - To provide an endpoint for the home page.
 *
 * Key Functionalities:
 * - `home()`: Handles GET requests for the home page and returns the view name "index".
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
