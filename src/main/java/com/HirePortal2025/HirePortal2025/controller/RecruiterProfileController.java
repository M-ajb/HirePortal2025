package com.HirePortal2025.HirePortal2025.controller;

import ch.qos.logback.core.util.StringUtil;
import com.HirePortal2025.HirePortal2025.entity.RecruiterProfile;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import com.HirePortal2025.HirePortal2025.services.RecruiterProfileService;
import com.HirePortal2025.HirePortal2025.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

/**
 * The `RecruiterProfileController` class handles HTTP requests related to recruiter profiles.
 * It interacts with the `UsersRepository` and `RecruiterProfileService` to manage recruiter profile data.
 *
 * Fields:
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 * - `recruiterProfileService`: Service for managing recruiter profile data.
 *
 * Purpose:
 * - To provide endpoints for viewing and updating recruiter profiles.
 *
 * Key Functionalities:
 * - `recruiterProfile(Model model)`: Handles GET requests for the recruiter profile page, retrieves the current user's recruiter profile, and populates the model with the profile data.
 * - `addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model)`: Handles POST requests for adding or updating a recruiter profile, sets the user ID, saves the profile photo, and redirects to the dashboard.
 */
@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;


    /**
     * Constructs a new `RecruiterProfileController` with the specified repositories and services.
     *
     * @param usersRepository the repository for performing CRUD operations on `Users` entities
     * @param recruiterProfileService the service for managing recruiter profile data
     */
    @Autowired
    public RecruiterProfileController(UsersRepository usersRepository, RecruiterProfileService recruiterProfileService) {
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
    }


    @GetMapping("/")
    public String recruiterProfile(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = usersRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

            recruiterProfileService.getOne(user.getUserId())
                    .ifPresent(profile -> model.addAttribute("profile", profile));
        }
        return "recruiter_profile";
    }


    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image")MultipartFile multipartFile, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = usersRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

            recruiterProfile.setUserId(user);
            recruiterProfile.setUserAccountId(user.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);

        String fileName = multipartFile.isEmpty() ? "" : StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        recruiterProfile.setProfilePhoto(fileName);

        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);

        if (!fileName.isEmpty()) {
            try {
                FileUploadUtil.saveFile("photos/recruiter/" + savedUser.getUserAccountId(), fileName, multipartFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "redirect:/dashboard/";
    }
}
