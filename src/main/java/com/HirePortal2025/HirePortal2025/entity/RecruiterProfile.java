package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * Represents a recruiter's profile in the system.
 * This class is mapped to the "recruiter_profile" table in the database.
 * It contains information about the recruiter such as their user account, personal details, and company information.
 *
 * Fields:
 * - userAccountId: The unique identifier for the user account associated with the recruiter.
 * - userId: The user entity associated with the recruiter.
 * - firstName: The first name of the recruiter.
 * - lastName: The last name of the recruiter.
 * - city: The city where the recruiter is located.
 * - state: The state where the recruiter is located.
 * - country: The country where the recruiter is located.
 * - company: The company the recruiter is associated with.
 * - profilePhoto: The profile photo of the recruiter.
 *
 * Key Functionalities:
 * - Getters and setters for all fields.
 * - Default constructor and parameterized constructors.
 * - Method to get the path of the profile photo.
 * - toString method to provide a string representation of the recruiter profile entity.
 */
@Entity
@Table(name="recruiter_profile")
public class RecruiterProfile {

    @Id
    private int userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String company;
    @Column(nullable = true, length = 64)
    private String profilePhoto;


    public RecruiterProfile() {
    }


    public RecruiterProfile(Users userId) {
        this.userId = userId;
    }

    /**
     * Constructs a new RecruiterProfile object with the specified details.
     *
     * @param userAccountId The unique identifier for the user account associated with the recruiter.
     * @param userId The user entity associated with the recruiter.
     * @param firstName The first name of the recruiter.
     * @param lastName The last name of the recruiter.
     * @param city The city where the recruiter is located.
     * @param state The state where the recruiter is located.
     * @param country The country where the recruiter is located.
     * @param company The company the recruiter is associated with.
     * @param profilePhoto The profile photo of the recruiter.
     */
    public RecruiterProfile(int userAccountId, Users userId, String firstName, String lastName, String city, String state, String country, String company, String profilePhoto) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.company = company;
        this.profilePhoto = profilePhoto;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }


    @Transient
    public String getPhotosImagePath(){
        if(profilePhoto == null){
            return null;
        }
        return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
    }

    @Override
    public String toString() {
        return "RecruiterProfile{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", company='" + company + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
