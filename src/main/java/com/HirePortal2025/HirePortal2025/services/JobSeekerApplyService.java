package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerApply;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The `JobSeekerApplyService` class provides services related to job seeker application management.
 * It includes methods to retrieve applied jobs for a job seeker and to retrieve job candidates for a job post.
 *
 * Fields:
 * - `jobSeekerApplyRepository`: Repository for performing CRUD operations on `JobSeekerApply` entities.
 *
 * Key Functionalities:
 * - `getCandidatesJobs(JobSeekerProfile userAccountId)`: Retrieves a list of applied jobs for a given job seeker profile.
 * - `getJobCandidates(JobPostActivity job)`: Retrieves a list of job seekers who have applied for a particular job post.
 * - `addNew(JobSeekerApply jobSeekerApply)`: Adds a new job seeker application entry.
 */
@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;


    /**
     * Constructs a new `JobSeekerApplyService` with the specified repository.
     *
     * @param jobSeekerApplyRepository the repository for performing CRUD operations on `JobSeekerApply` entities
     */
    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }


    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job){
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);
    }


}
