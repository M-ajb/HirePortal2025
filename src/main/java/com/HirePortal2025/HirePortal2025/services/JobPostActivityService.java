package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.*;
import com.HirePortal2025.HirePortal2025.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The `JobPostActivityService` class provides services related to job post activity management.
 * It includes methods to add new job posts, retrieve job posts by ID, get all job posts,
 * search for job posts based on various criteria, and retrieve jobs posted by a specific recruiter.
 *
 * Fields:
 * - `jobPostActivityRepository`: Repository for performing CRUD operations on `JobPostActivity` entities.
 *
 * Key Functionalities:
 * - `addNew(JobPostActivity jobPostActivity)`: Adds a new job post activity.
 * - `getRecruiterJobs(int recruiter)`: Retrieves a list of jobs posted by a specific recruiter.
 * - `getOne(int id)`: Retrieves a job post activity by its ID.
 * - `getAll()`: Retrieves all job post activities.
 * - `search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate)`:
 *   Searches for job posts based on job title, location, type, remote status, and search date.
 */
@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    /**
     * Constructs a new `JobPostActivityService` with the specified repository.
     *
     * @param jobPostActivityRepository the repository for performing CRUD operations on `JobPostActivity` entities
     */
    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){

        return jobPostActivityRepository.save(jobPostActivity);
    }


    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){

        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for(IRecruiterJobs rec : recruiterJobsDtos){
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());

            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");

            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_Post_Id(), rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;

    }

    public JobPostActivity getOne(int id) {

        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }




    public List<JobPostActivity> getAll() {
       return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {

        return Objects.isNull(searchDate)? jobPostActivityRepository.searchWithoutDate(job,location, remote, type):
                jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }
}
