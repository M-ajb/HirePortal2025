package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerSave;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.services.JobPostActivityService;
import com.HirePortal2025.HirePortal2025.services.JobSeekerProfileService;
import com.HirePortal2025.HirePortal2025.services.JobSeekerSaveService;
import com.HirePortal2025.HirePortal2025.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The `JobSeekerSaveController` class handles HTTP requests related to saving job posts for job seekers.
 *
 * Fields:
 * - `usersService`: Service for managing user data.
 * - `jobSeekerProfileService`: Service for managing job seeker profile data.
 * - `jobPostActivityService`: Service for managing job post activity data.
 * - `jobSeekerSaveService`: Service for managing saved job posts.
 *
 * Purpose:
 * - To provide endpoints for saving job posts and viewing saved job posts for job seekers.
 *
 * Key Functionalities:
 * - `save(int id, JobSeekerSave jobSeekerSave)`: Handles POST requests for saving a job post for the current user.
 * - `savedJobs(Model model)`: Handles GET requests for viewing the saved job posts of the current user.
 */
@Controller
public class JobSeekerSaveController {

    private final UsersService usersService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerSaveService jobSeekerSaveService;

    /**
     * Constructs a new `JobSeekerSaveController` with the specified services.
     *
     * @param usersService the service for managing user data
     * @param jobSeekerProfileService the service for managing job seeker profile data
     * @param jobPostActivityService the service for managing job post activity data
     * @param jobSeekerSaveService the service for managing saved job posts
     */
    @Autowired
    public JobSeekerSaveController(UsersService usersService, JobSeekerProfileService jobSeekerProfileService,
                                   JobPostActivityService jobPostActivityService, JobSeekerSaveService jobSeekerSaveService) {
        this.usersService = usersService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobPostActivityService = jobPostActivityService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }


    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, JobSeekerSave jobSeekerSave){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            return "redirect:/dashboard/";

        Users user = usersService.findByEmail(authentication.getName());
        JobSeekerProfile seekerProfile = jobSeekerProfileService.getOne(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);

        if (jobPostActivity != null) {
            jobSeekerSave = new JobSeekerSave();
            jobSeekerSave.setJob(jobPostActivity);
            jobSeekerSave.setUserId(seekerProfile);
            jobSeekerSaveService.addNew(jobSeekerSave);
        }

        return "redirect:/dashboard/";
    }


    @GetMapping("saved-jobs/")
    public String savedJobs(Model model){
        Object currentUserProfile = usersService.getCurrentUserProfile();

        List<JobPostActivity> jobPost = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile)
                .stream().map(JobSeekerSave::getJob).collect(Collectors.toList());

        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);
        return "saved-jobs";

    }
}
