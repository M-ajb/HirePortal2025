package com.HirePortal2025.HirePortal2025.services;

import com.HirePortal2025.HirePortal2025.entity.JobSeekerProfile;
import com.HirePortal2025.HirePortal2025.entity.RecruiterProfile;
import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.JobSeekerProfileRepository;
import com.HirePortal2025.HirePortal2025.repository.RecruiterProfileRepository;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


/**
 * The `UsersService` class provides services related to user management, including
 * adding new users, retrieving user information by email, and obtaining the current
 * authenticated user's profile and details.
 *
 * Fields:
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 * - `jobSeekerProfileRepository`: Repository for performing CRUD operations on `JobSeekerProfile` entities.
 * - `recruiterProfileRepository`: Repository for performing CRUD operations on `RecruiterProfile` entities.
 * - `passwordEncoder`: Encoder for encoding user passwords.
 *
 * Key Functionalities:
 * - `addNew(Users users)`: Adds a new user, sets the user as active, sets the registration date,
 *   encodes the user's password, and saves the user. Depending on the user type, it also saves
 *   a corresponding `RecruiterProfile` or `JobSeekerProfile`.
 * - `getUserByEmail(String email)`: Retrieves a user by their email address.
 * - `getCurrentUserProfile()`: Retrieves the profile of the currently authenticated user, either
 *   a `RecruiterProfile` or `JobSeekerProfile`, based on the user's role.
 * - `getCurrentUser()`: Retrieves the currently authenticated user.
 * - `findByEmail(String currentUsername)`: Finds a user by their email address.
 */
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;


    /**
         * Constructs a new `UsersService` with the specified repositories and password encoder.
         *
         * @param usersRepository the repository for performing CRUD operations on `Users` entities
         * @param jobSeekerProfileRepository the repository for performing CRUD operations on `JobSeekerProfile` entities
         * @param recruiterProfileRepository the repository for performing CRUD operations on `RecruiterProfile` entities
         * @param passwordEncoder the encoder for encoding user passwords
         */
    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository,PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));

        users.setPassword(passwordEncoder.encode(users.getPassword()));

        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if(userTypeId ==1){
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }

        return savedUser;
    }


    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }



    public Object getCurrentUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find " + "user"));
            int userId = users.getUserId();

            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
                return recruiterProfile;
            }else{
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
                return  jobSeekerProfile;
            }
        }
        return null;
    }

    public Users getCurrentUser() {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if(!(authentication instanceof AnonymousAuthenticationToken)){
         String username = authentication.getName();
          Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find " + "user"));

          return user;
      }
      return null;
    }


    public Users findByEmail(String currentUsername) {

        return usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not " + " found"));
    }
}
