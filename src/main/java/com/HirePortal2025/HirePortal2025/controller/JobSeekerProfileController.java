package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.Skills;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import com.HirePortal2025.HirePortal2025.services.JobSeekerProfileService;
import com.HirePortal2025.HirePortal2025.util.FileDownloadUtil;
import com.HirePortal2025.HirePortal2025.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * The `JobSeekerProfileController` class handles HTTP requests related to job seeker profiles.
 *
 * Fields:
 * - `jobSeekerProfileService`: Service for managing job seeker profile data.
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 *
 * Purpose:
 * - To provide endpoints for viewing, updating, and managing job seeker profiles.
 *
 * Key Functionalities:
 * - `JobSeekerProfile(Model model)`: Handles GET requests for the job seeker profile page, retrieves the current user's job seeker profile, and populates the model with the profile data.
 * - `addNew(JobSeekerProfile jobSeekerProfile, @RequestParam("image") MultipartFile image, @RequestParam("pdf") MultipartFile pdf, Model model)`: Handles POST requests for adding or updating a job seeker profile, sets the user ID, saves the profile photo and resume, and redirects to the dashboard.
 * - `candidateProfile(@PathVariable("id") int id, Model model)`: Handles GET requests for viewing a specific job seeker profile by ID and populates the model with the profile data.
 * - `downloadResume(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "userID") String userId)`: Handles GET requests for downloading a job seeker's resume.
 */
@Controller
@RequestMapping("/job-seeker-profile")
public class JobSeekerProfileController {

    private JobSeekerProfileService jobSeekerProfileService;
    private UsersRepository usersRepository;


    /**
     * Constructs a new `JobSeekerProfileController` with the specified services and repository.
     *
     * @param jobSeekerProfileService the service for managing job seeker profile data
     * @param usersRepository the repository for performing CRUD operations on `Users` entities
     */
    @Autowired
    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService, UsersRepository usersRepository) {
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.usersRepository = usersRepository;
    }


    @GetMapping("/")
    public String jobSeekerProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return "job-seeker-profile";

        Users user = usersRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        JobSeekerProfile jobSeekerProfile = jobSeekerProfileService.getOne(user.getUserId())
                .orElse(new JobSeekerProfile());

        if (jobSeekerProfile.getSkills().isEmpty())
            jobSeekerProfile.setSkills(Collections.singletonList(new Skills()));

        model.addAttribute("skills", jobSeekerProfile.getSkills());
        model.addAttribute("profile", jobSeekerProfile);
        return "job-seeker-profile";
    }




    @PostMapping("/addNew")
    public String addNew(JobSeekerProfile jobSeekerProfile, @RequestParam("image")MultipartFile image, @RequestParam("pdf") MultipartFile pdf, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = usersRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            jobSeekerProfile.setUserId(user);
            jobSeekerProfile.setUserAccountId(user.getUserId());
        }

        model.addAttribute("profile", jobSeekerProfile);
        model.addAttribute("skills", new ArrayList<>());

        jobSeekerProfile.getSkills().forEach(skill -> skill.setJobSeekerProfile(jobSeekerProfile));

        if (!Objects.equals(image.getOriginalFilename(), "")) {
            jobSeekerProfile.setProfilePhoto(StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename())));
        }

        if (!Objects.equals(pdf.getOriginalFilename(), "")) {
            jobSeekerProfile.setResume(StringUtils.cleanPath(Objects.requireNonNull(pdf.getOriginalFilename())));
        }

        jobSeekerProfileService.addNew(jobSeekerProfile);

        try {
            String uploadDir = "photos/candidate/" + jobSeekerProfile.getUserAccountId();
            if (!Objects.equals(image.getOriginalFilename(), ""))
                FileUploadUtil.saveFile(uploadDir, jobSeekerProfile.getProfilePhoto(), image);
            if (!Objects.equals(pdf.getOriginalFilename(), ""))
                FileUploadUtil.saveFile(uploadDir, jobSeekerProfile.getResume(), pdf);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return "redirect:/dashboard/";
    }


    @GetMapping("/{id}")
    public String candidateProfile(@PathVariable("id") int id, Model model){
        jobSeekerProfileService.getOne(id).ifPresent(profile -> model.addAttribute("profile", profile));
        return "job-seeker-profile";
    }


    @GetMapping("/downloadResume")
    public ResponseEntity<?> downloadResume(@RequestParam(value = "fileName") String fileName,
                                            @RequestParam(value = "userID") String userId){

        FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
        Resource resource;

        try {
            resource = fileDownloadUtil.getFileAsResourse("photos/candidate/" + userId, fileName);
        } catch (IOException io) {
            return ResponseEntity.badRequest().build();
        }

        if (resource == null) return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
