package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * Represents a user entity in the database.
 * This class is mapped to the "users" table in the database.
 * It contains information about the user such as email, password,
 * active status, registration date, and user type.
 *
 * Fields:
 * - userId: The unique identifier for the user.
 * - email: The email address of the user, which must be unique.
 * - password: The password for the user's account.
 * - isActive: Indicates whether the user's account is active.
 * - registrationDate: The date when the user registered.
 * - userTypeId: The type of user, represented by a foreign key to the UsersType entity.
 *
 * Key Functionalities:
 * - Getters and setters for all fields.
 * - Default constructor and parameterized constructor.
 * - toString method to provide a string representation of the user entity.
 */
@Entity
@Table(name= "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private boolean isActive;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId")
    private UsersType userTypeId;


    public Users() {
    }

/**
 * Constructs a new Users object with the specified details.
 *
 * @param userId the unique identifier for the user
 * @param email the email address of the user, which must be unique
 * @param password the password for the user's account
 * @param isActive indicates whether the user's account is active
 * @param registrationDate the date when the user registered
 * @param userTypeId the type of user, represented by a foreign key to the UsersType entity
 */
    public Users(int userId, String email, String password, boolean isActive, Date registrationDate, UsersType userTypeId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.registrationDate = registrationDate;
        this.userTypeId = userTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UsersType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UsersType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", registrationDate=" + registrationDate +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
