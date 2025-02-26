package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The `JobSeekerSaveRepository` interface extends the JpaRepository interface
 * to provide CRUD operations for the JobSeekerSave entity.
 *
 * This repository interface includes methods to find saved job posts
 * by a job seeker profile and by job post activity.
 *
 * Fields:
 * - JobSeekerSave: The entity type that this repository manages.
 * - Integer: The type of the entity's identifier.
 *
 * Key Functionalities:
 * - findByUserId(JobSeekerProfile userAccountId): Retrieves a list of
 *   JobSeekerSave entities associated with a specific job seeker profile.
 * - findByJob(JobPostActivity job): Retrieves a list of JobSeekerSave
 *   entities associated with a specific job post activity.
 */
@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave , Integer> {

     List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);


     List<JobSeekerSave> findByJob(JobPostActivity job);

     @Modifying
     @Transactional
     @Query("DELETE FROM JobSeekerSave j WHERE j.job = :job")
     void deleteByJob(JobPostActivity job);
}
