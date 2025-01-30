package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.services.JobPostActivityService;
import com.HirePortal2025.HirePortal2025.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
    }

    @GetMapping("/job-details-apply/{id}")
    public String dislpay(@PathVariable("id") int id, Model model){

        JobPostActivity jobDetails = jobPostActivityService.getOne(id);

        model.addAttribute("jobDetails", jobDetails);
        model. addAttribute("user", usersService.getCurrentUserProfile());

        return "job-details";

    }


}
