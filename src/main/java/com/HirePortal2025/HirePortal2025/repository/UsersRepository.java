package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The `UsersRepository` interface extends the JpaRepository interface to provide CRUD operations for the Users entity.
 * It includes methods to find a user by email and perform other database operations.
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
