package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerApply;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;


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
}
