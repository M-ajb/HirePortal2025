package com.HirePortal2025.HirePortal2025.repository;


import com.HirePortal2025.HirePortal2025.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The `UsersTypeRepository` interface extends the JpaRepository interface to provide CRUD operations for the UsersType entity.
 * It includes methods to find a user type by ID and perform other database operations.
 */
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
