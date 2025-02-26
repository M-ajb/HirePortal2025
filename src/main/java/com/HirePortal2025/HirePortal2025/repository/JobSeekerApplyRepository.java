package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerApply;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The `JobSeekerApplyRepository` interface extends the JpaRepository interface
 * to provide CRUD operations for the JobSeekerApply entity.
 *
 * This repository interface includes methods to find job applications
 * by a job seeker profile and by job post activity.
 *
 * Fields:
 * - JobSeekerApply: The entity type that this repository manages.
 * - Integer: The type of the entity's identifier.
 *
 * Key Functionalities:
 * - findByUserId(JobSeekerProfile userId): Retrieves a list of
 *   JobSeekerApply entities associated with a specific job seeker profile.
 * - findByJob(JobPostActivity job): Retrieves a list of JobSeekerApply
 *   entities associated with a specific job post activity.
 */
@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);

    @Modifying
    @Transactional
    @Query("DELETE FROM JobSeekerApply j WHERE j.job = :job")
    void deleteByJob(JobPostActivity job);


}
