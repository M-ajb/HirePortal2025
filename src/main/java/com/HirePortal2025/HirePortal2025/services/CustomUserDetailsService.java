package com.HirePortal2025.HirePortal2025.services;


import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.repository.UsersRepository;
import com.HirePortal2025.HirePortal2025.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The `CustomUserDetailsService` class implements the `UserDetailsService` interface
 * to provide custom user authentication and authorization services.
 * It includes methods to load user details by username.
 *
 * Fields:
 * - `usersRepository`: Repository for performing CRUD operations on `Users` entities.
 *
 * Purpose:
 * - To provide custom user details for authentication and authorization in the application.
 *
 * Key Functionalities:
 * - `loadUserByUsername(String username)`: Loads user details by username (email) and returns a `CustomUserDetails` object.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    /**
     * Constructs a new `CustomUserDetailsService` with the specified repository.
     *
     * @param usersRepository the repository for performing CRUD operations on `Users` entities
     */
    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
        return new CustomUserDetails(user);
    }
}
