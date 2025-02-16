package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.JobSeekerProfileRepository;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * The `JobSeekerProfileService` class provides services related to job seeker profile management.
 * It includes methods to retrieve, add, and get the current authenticated job seeker's profile.
 *
 * Fields:
 * - `jobSeekerProfileRepository`: Repository for performing CRUD operations on `JobSeekerProfile` entities.
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 *
 * Key Functionalities:
 * - `getOne(Integer id)`: Retrieves a job seeker profile by its ID.
 * - `addNew(JobSeekerProfile jobSeekerProfile)`: Adds a new job seeker profile.
 * - `getCurrentSeekerProfile()`: Retrieves the profile of the currently authenticated job seeker.
 */
@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;

    /**
     * Constructs a new `JobSeekerProfileService` with the specified repositories.
     *
     * @param jobSeekerProfileRepository the repository for performing CRUD operations on `JobSeekerProfile` entities
     * @param usersRepository the repository for performing CRUD operations on `Users` entities
     */
    @Autowired
    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id){
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {

        return  jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not " + "found"));

            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            return seekerProfile.orElse(null);
        }else{
            return null;
        }
    }
}
