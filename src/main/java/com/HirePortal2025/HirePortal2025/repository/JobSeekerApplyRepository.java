package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerApply;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);

}
