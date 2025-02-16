package com.HirePortal2025.HirePortal2025.util;

import com.HirePortal2025.HirePortal2025.entity.Users;
import com.HirePortal2025.HirePortal2025.entity.UsersType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * The `CustomUserDetails` class implements the `UserDetails` interface to provide custom user details for Spring Security.
 * It wraps a `Users` entity and provides user information such as username, password, and authorities.
 *
 * Fields:
 * - `user`: A `Users` object representing the user entity.
 *
 * Purpose:
 * - To provide custom user details required by Spring Security for authentication and authorization.
 *
 * Key Functionalities:
 * - `getAuthorities()`: Retrieves the authorities granted to the user based on their user type.
 * - `getPassword()`: Returns the password of the user.
 * - `getUsername()`: Returns the username (email) of the user.
 * - `isAccountNonExpired()`: Indicates whether the user's account has expired.
 * - `isAccountNonLocked()`: Indicates whether the user is locked or unlocked.
 * - `isCredentialsNonExpired()`: Indicates whether the user's credentials (password) have expired.
 * - `isEnabled()`: Indicates whether the user is enabled or disabled.
 */
public class CustomUserDetails implements UserDetails {

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UsersType usersType = user.getUserTypeId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usersType.getUserTypeName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
