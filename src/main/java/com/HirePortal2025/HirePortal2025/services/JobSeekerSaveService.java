package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerSave;
import com.HirePortal2025.HirePortal2025.repository.JobSeekerSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The `JobSeekerSaveService` class provides services related to job seeker save management.
 * It includes methods to retrieve saved jobs for a job seeker and to retrieve job candidates for a job post.
 *
 * Fields:
 * - `jobSeekerSaveRepository`: Repository for performing CRUD operations on `JobSeekerSave` entities.
 *
 * Key Functionalities:
 * - `getCandidatesJob(JobSeekerProfile userAccountId)`: Retrieves a list of saved jobs for a given job seeker profile.
 * - `getJobCandidates(JobPostActivity job)`: Retrieves a list of job seekers who have saved a particular job post.
 * - `addNew(JobSeekerSave jobSeekerSave)`: Adds a new job seeker save entry.
 */
@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;


    /**
     * Constructs a new `JobSeekerSaveService` with the specified repository.
     *
     * @param jobSeekerSaveRepository the repository for performing CRUD operations on `JobSeekerSave` entities
     */
    @Autowired
    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId){
        return jobSeekerSaveRepository.findByUserId(userAccountId);

    }


    public List<JobSeekerSave> getJobCandidates(JobPostActivity job){
        return jobSeekerSaveRepository.findByJob(job);
    }



    public void addNew(JobSeekerSave jobSeekerSave) {
        jobSeekerSaveRepository.save(jobSeekerSave);
    }
}
