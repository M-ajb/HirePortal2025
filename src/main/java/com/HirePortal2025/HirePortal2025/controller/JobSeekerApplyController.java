package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.*;
import com.HirePortal2025.HirePortal2025.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;

    private final RecruiterProfileService recruiterProfileService;
    private final JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService, JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService, RecruiterProfileService recruiterProfileService, JobSeekerProfileService jobSeekerProfileService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.recruiterProfileService = recruiterProfileService;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @GetMapping("/job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getJobCandidates(jobDetails);
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobCandidates(jobDetails);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "job-details";
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
            RecruiterProfile user = recruiterProfileService.getCurrentRecruiterProfile();
            if (user != null) model.addAttribute("applyList", jobSeekerApplyList);
        } else {
            JobSeekerProfile user = jobSeekerProfileService.getCurrentSeekerProfile();
            if (user != null) {
                boolean exists = jobSeekerApplyList.stream().anyMatch(a -> a.getUserId().getUserAccountId() == user.getUserAccountId());
                boolean saved = jobSeekerSaveList.stream().anyMatch(s -> s.getUserId().getUserAccountId() == user.getUserAccountId());
                model.addAttribute("alreadyApplied", exists);
                model.addAttribute("alreadySaved", saved);
            }
        }

        model.addAttribute("applyJob", new JobSeekerApply());
        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());

        return "job-details";
    }


    @PostMapping("job-details/apply/{id}")
    public String apply(@PathVariable("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return "redirect:/dashboard/";

        Users user = usersService.findByEmail(authentication.getName());
        Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
        JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);

        if (seekerProfile.isPresent() && jobPostActivity != null) {
            JobSeekerApply jobSeekerApply = new JobSeekerApply();
            jobSeekerApply.setUserId(seekerProfile.get());
            jobSeekerApply.setJob(jobPostActivity);
            jobSeekerApply.setApplyDate(new Date());
            jobSeekerApplyService.addNew(jobSeekerApply);
        } else {
            throw new RuntimeException("User not found");
        }

        return "redirect:/dashboard/";
    }


}
