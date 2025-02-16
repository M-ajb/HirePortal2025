package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The `RecruiterProfileRepository` interface extends the JpaRepository interface to provide CRUD operations for the RecruiterProfile entity.
 * It includes methods to find a recruiter profile by ID and perform other database operations.
 */
public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Integer> {



}
