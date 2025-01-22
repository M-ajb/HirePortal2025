package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
