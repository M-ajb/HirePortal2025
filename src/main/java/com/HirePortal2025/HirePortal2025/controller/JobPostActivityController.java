package com.HirePortal2025.HirePortal2025.controller;

import com.HirePortal2025.HirePortal2025.entity.*;
import com.HirePortal2025.HirePortal2025.services.JobPostActivityService;
import com.HirePortal2025.HirePortal2025.services.JobSeekerApplyService;
import com.HirePortal2025.HirePortal2025.services.JobSeekerSaveService;
import com.HirePortal2025.HirePortal2025.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The `JobPostActivityController` class handles HTTP requests related to job post activities.
 * It provides endpoints for searching, adding, editing, and globally searching job posts.
 *
 * Fields:
 * - `usersService`: Service for managing user data.
 * - `jobPostActivityService`: Service for managing job post activity data.
 * - `jobSeekerApplyService`: Service for managing job seeker applications.
 * - `jobSeekerSaveService`: Service for managing saved job posts.
 *
 * Purpose:
 * - To provide endpoints for job seekers and recruiters to interact with job posts.
 *
 * Key Functionalities:
 * - `searchJobs(Model model, String job, String location, String partTime, String fullTime, String freelance, String remoteOnly, String officeOnly, String partialRemote, boolean today, boolean days7, boolean days30)`: Handles GET requests for searching job posts on the dashboard.
 * - `addJobs(Model model)`: Handles GET requests for displaying the add job post form.
 * - `addNew(JobPostActivity jobPostActivity, Model model)`: Handles POST requests for adding a new job post.
 * - `editJob(int id, Model model)`: Handles POST requests for editing an existing job post.
 * - `globalSearch(Model model, String job, String location, String partTime, String fullTime, String freelance, String remoteOnly, String officeOnly, String partialRemote, boolean today, boolean days7, boolean days30)`: Handles GET requests for globally searching job posts.
 */
@Controller
public class JobPostActivityController {

    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;

    /**
     * Constructs a new `JobPostActivityController` with the specified services.
     *
     * @param usersService the service for managing user data
     * @param jobPostActivityService the service for managing job post activity data
     * @param jobSeekerApplyService the service for managing job seeker applications
     * @param jobSeekerSaveService the service for managing saved job posts
     */
    @Autowired
    public JobPostActivityController(UsersService usersService, JobPostActivityService jobPostActivityService, JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService) {
        this.usersService = usersService;
        this.jobPostActivityService = jobPostActivityService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }

    /**
     * Handles job search functionality for the dashboard.
     * Retrieves job listings based on filters such as job type, location,
     * work mode, and posting date, and adjusts the results for job seekers
     * and recruiters accordingly.
     *
     * @param model the Model object to pass attributes to the view
     * @param job the job title filter
     * @param location the location filter
     * @param partTime filter for part-time jobs
     * @param fullTime filter for full-time jobs
     * @param freelance filter for freelance jobs
     * @param remoteOnly filter for remote jobs
     * @param officeOnly filter for office-based jobs
     * @param partialRemote filter for hybrid jobs
     * @param today filter for jobs posted today
     * @param days7 filter for jobs posted in the last 7 days
     * @param days30 filter for jobs posted in the last 30 days
     * @return the name of the view to be rendered
     */
    @GetMapping("/dashboard/")
    public  String searchJobs(Model model,
                              @RequestParam(value = "job", required = false) String job,
                              @RequestParam(value = "location", required = false) String location,
                              @RequestParam(value = "partTime", required = false) String partTime,
                              @RequestParam(value = "fullTime", required = false) String fullTime,
                              @RequestParam(value = "freelance", required = false) String freelance,
                              @RequestParam(value = "remoteOnly", required = false) String remoteOnly,
                              @RequestParam(value = "officeOnly", required = false) String officeOnly,
                              @RequestParam(value = "partialRemote", required = false) String partialRemote,
                              @RequestParam(value = "today", required = false) boolean today,
                              @RequestParam(value = "days7", required = false) boolean days7,
                              @RequestParam(value = "days30", required = false) boolean days30
    ){
        model.addAttribute("partTime", Objects.equals(partTime, "Part-Time"));
        model.addAttribute("fullTime", Objects.equals(partTime, "Full-Time"));
        model.addAttribute("freelance", Objects.equals(partTime, "Freelance"));

        model.addAttribute("remoteOnly", Objects.equals(partTime, "Remote-Only"));
        model.addAttribute("officeOnly", Objects.equals(partTime, "Office-Only"));
        model.addAttribute("partialRemote", Objects.equals(partTime, "Partial-Remote"));

        model.addAttribute("today", today);
        model.addAttribute("days7", days7);
        model.addAttribute("days30", days30);

        model.addAttribute("job", job);
        model.addAttribute("location", location);

        LocalDate searchDate = null;
        List<JobPostActivity> jobPost = null;
        boolean dateSearchFlag = true;
        boolean remote = true;
        boolean type = true;

        if (days30) {
            searchDate = LocalDate.now().minusDays(30);
        } else if (days7) {
            searchDate = LocalDate.now().minusDays(7);
        } else if (today) {
            searchDate = LocalDate.now();
        } else {
            dateSearchFlag = false;
        }

        if (partTime == null && fullTime == null && freelance == null) {
            partTime = "Part-Time";
            fullTime = "Full-Time";
            freelance = "Freelance";
            remote = false;
        }

        if (officeOnly == null && remoteOnly == null && partialRemote == null) {
            officeOnly = "Office-Only";
            remoteOnly = "Remote-Only";
            partialRemote = "Partial-Remote";
            type = false;
        }

        if (!dateSearchFlag && !remote && !type && !StringUtils.hasText(job) && !StringUtils.hasText(location)) {
            jobPost = jobPostActivityService.getAll();
        } else {
            jobPost = jobPostActivityService.search(job, location, Arrays.asList(partTime, fullTime, freelance),
                    Arrays.asList(remoteOnly, officeOnly, partialRemote), searchDate);
        }

        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                List<RecruiterJobsDto> recruiterJobs = jobPostActivityService.getRecruiterJobs(((RecruiterProfile) currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost", recruiterJobs);
            } else {
                List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getCandidatesJobs((JobSeekerProfile) currentUserProfile);
                List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);

                boolean exist;
                boolean saved;

                for (JobPostActivity jobActivity : jobPost) {
                    exist = false;
                    saved = false;
                    for (JobSeekerApply jobSeekerApply : jobSeekerApplyList) {
                        if (Objects.equals(jobActivity.getJobPostId(), jobSeekerApply.getJob().getJobPostId())) {
                            jobActivity.setIsActive(true);
                            exist = true;
                            break;
                        }
                    }

                    for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
                        if (Objects.equals(jobActivity.getJobPostId(), jobSeekerSave.getJob().getJobPostId())) {
                            jobActivity.setIsSaved(true);
                            saved = true;
                            break;
                        }
                    }

                    if (!exist) {
                        jobActivity.setIsActive(false);
                    }
                    if (!saved) {
                        jobActivity.setIsSaved(false);
                    }

                    model.addAttribute("jobPost", jobPost);

                }
            }
        }

        model.addAttribute("user", currentUserProfile);

        return "dashboard";
    }




    /**
     * Handles the addition of new job post activities.
     * Displays the add job post form and adds the new job post activity to the database.
     *
     * @param model the Model object to pass attributes to the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/dashboard/add")
    public String addJobs(Model model){

        model.addAttribute("jobPostActivity", new JobPostActivity());
        model.addAttribute("user", usersService.getCurrentUserProfile());

        return "add-jobs";
    }




    /**
     * Adds a new job post activity to the database.
     *
     * @param jobPostActivity the job post activity to be added
     * @param model the Model object to pass attributes to the view
     * @return the name of the view to be rendered
     */
    @PostMapping("/dashboard/addNew")
    public String addNew(JobPostActivity jobPostActivity, Model model){
        Users user = usersService.getCurrentUser();
        if (user != null) jobPostActivity.setPostedById(user);
        jobPostActivity.setPostedDate(new Date());

        model.addAttribute("jobPostActivity", jobPostActivity);
        jobPostActivityService.addNew(jobPostActivity);

        return "redirect:/dashboard/";
    }


    /**
     * Edits an existing job post activity.
     *
     * @param id the ID of the job post activity to be edited
     * @param model the Model object to pass attributes to the view
     * @return the name of the view to be rendered
     */
    @PostMapping("dashboard/edit/{id}")
    public String editJob(@PathVariable("id") int id, Model model){
        JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
        model.addAttribute("jobPostActivity", jobPostActivity);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-jobs";
    }



    /**
     * Handles global job search functionality.
     * Retrieves job listings based on filters such as job type, location,
     * work mode, and posting date. Returns job listings for all users,
     * regardless of authentication status.
     *
     * @param model the Model object to pass attributes to the view
     * @param job the job title filter
     * @param location the location filter
     * @param partTime filter for part-time jobs
     * @param fullTime filter for full-time jobs
     * @param freelance filter for freelance jobs
     * @param remoteOnly filter for remote jobs
     * @param officeOnly filter for office-based jobs
     * @param partialRemote filter for hybrid jobs
     * @param today filter for jobs posted today
     * @param days7 filter for jobs posted in the last 7 days
     * @param days30 filter for jobs posted in the last 30 days
     * @return the name of the view to be rendered
     */
    @GetMapping("/global-search/")
    public String globalSearch(Model model,
                               @RequestParam(value = "job", required = false) String job,
                               @RequestParam(value = "location", required = false) String location,
                               @RequestParam(value = "partTime", required = false) String partTime,
                               @RequestParam(value = "fullTime", required = false) String fullTime,
                               @RequestParam(value = "freelance", required = false) String freelance,
                               @RequestParam(value = "remoteOnly", required = false) String remoteOnly,
                               @RequestParam(value = "officeOnly", required = false) String officeOnly,
                               @RequestParam(value = "partialRemote", required = false) String partialRemote,
                               @RequestParam(value = "today", required = false) boolean today,
                               @RequestParam(value = "days7", required = false) boolean days7,
                               @RequestParam(value = "days30", required = false) boolean days30){

        List<String> jobTypes = Arrays.asList(partTime, fullTime, freelance);
        List<String> remoteTypes = Arrays.asList(remoteOnly, officeOnly, partialRemote);
        LocalDate searchDate = today ? LocalDate.now() : days7 ? LocalDate.now().minusDays(7) : days30 ? LocalDate.now().minusDays(30) : null;
        boolean isFilterApplied = searchDate != null || jobTypes.contains(null) || remoteTypes.contains(null) || StringUtils.hasText(job) || StringUtils.hasText(location);

        model.addAttribute("job", job);
        model.addAttribute("location", location);
        model.addAttribute("today", today);
        model.addAttribute("days7", days7);
        model.addAttribute("days30", days30);
        model.addAttribute("partTime", "Part-Time".equals(partTime));
        model.addAttribute("fullTime", "Full-Time".equals(fullTime));
        model.addAttribute("freelance", "Freelance".equals(freelance));
        model.addAttribute("remoteOnly", "Remote-Only".equals(remoteOnly));
        model.addAttribute("officeOnly", "Office-Only".equals(officeOnly));
        model.addAttribute("partialRemote", "Partial-Remote".equals(partialRemote));

        List<JobPostActivity> jobPost = isFilterApplied ?
                jobPostActivityService.search(job, location, jobTypes, remoteTypes, searchDate) :
                jobPostActivityService.getAll();

        model.addAttribute("jobPost", jobPost);
        return "global-search";
    }
}
