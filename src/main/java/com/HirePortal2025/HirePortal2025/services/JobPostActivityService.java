package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.*;
import com.HirePortal2025.HirePortal2025.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

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
}
