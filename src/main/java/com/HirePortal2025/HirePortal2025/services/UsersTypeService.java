package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.entity.UsersType;
import com.HirePortal2025.HirePortal2025.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The `UsersTypeService` class provides services related to user type management.
 * It includes methods to retrieve all user types from the database.
 *
 * Fields:
 * - `usersTypeRepository`: Repository for performing CRUD operations on `UsersType` entities.
 *
 * Key Functionalities:
 * - `getAll()`: Retrieves a list of all user types.
 */
@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    /**
     * Constructs a new `UsersTypeService` with the specified repository.
     *
     * @param usersTypeRepository the repository for performing CRUD operations on `UsersType` entities
     */
    @Autowired
    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll(){
        return usersTypeRepository.findAll();
    }



}
