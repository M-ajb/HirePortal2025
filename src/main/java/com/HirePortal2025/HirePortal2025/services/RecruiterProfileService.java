package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.RecruiterProfile;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.RecruiterProfileRepository;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The `RecruiterProfileService` class provides services related to recruiter profile management.
 * It includes methods to retrieve, add, and get the current authenticated recruiter's profile.
 *
 * Fields:
 * - `recruiterProfileRepository`: Repository for performing CRUD operations on `RecruiterProfile` entities.
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 *
 * Key Functionalities:
 * - `getOne(Integer id)`: Retrieves a recruiter profile by its ID.
 * - `addNew(RecruiterProfile recruiterProfile)`: Adds a new recruiter profile.
 * - `getCurrentRecruiterProfile()`: Retrieves the profile of the currently authenticated recruiter.
 */
@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersRepository usersRepository;


    /**
         * Constructs a new `RecruiterProfileService` with the specified repositories.
         *
         * @param recruiterProfileRepository the repository for performing CRUD operations on `RecruiterProfile` entities
         * @param usersRepository the repository for performing CRUD operations on `Users` entities
         */
    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UsersRepository usersRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }


    public Optional<RecruiterProfile> getOne(Integer id){
        return recruiterProfileRepository.findById(id);
    }



    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {

        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
           Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        }else{
            return null;
        }
    }
}
