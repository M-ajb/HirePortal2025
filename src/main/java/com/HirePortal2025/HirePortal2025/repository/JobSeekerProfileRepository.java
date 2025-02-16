package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The `JobSeekerProfileRepository` interface extends the JpaRepository interface to provide CRUD operations for the JobSeekerProfile entity.
 * It includes methods to find a job seeker profile by ID and perform other database operations.
 */
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {


}
