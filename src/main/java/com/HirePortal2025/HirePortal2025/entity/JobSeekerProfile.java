package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * The `JobSeekerProfile` class represents the profile of a job seeker in the HirePortal2025 application.
 * It contains personal information, work authorization details, employment type, resume, profile photo, and skills.
 *
 * Fields:
 * - `userAccountId`: The unique identifier for the job seeker's user account.
 * - `userId`: The user associated with this job seeker profile, represented by a `Users` object.
 * - `firstName`: The first name of the job seeker.
 * - `lastName`: The last name of the job seeker.
 * - `city`: The city where the job seeker resides.
 * - `state`: The state where the job seeker resides.
 * - `country`: The country where the job seeker resides.
 * - `workAuthorization`: The work authorization status of the job seeker.
 * - `employmentType`: The type of employment the job seeker is looking for.
 * - `resume`: The resume of the job seeker.
 * - `profilePhoto`: The profile photo of the job seeker.
 * - `skills`: A list of skills possessed by the job seeker, represented by `Skills` objects.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the job seeker profile details.
 * - `getPhotosImagePath` method to generate the path for the job seeker's profile photo.
 * - `toString` method to provide a string representation of the job seeker profile.
 */
@Entity
@Table(name="job_seeker_profile")
public class JobSeekerProfile {

    @Id
    private Integer userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String workAuthorization;
    private String employmentType;
    private String resume;
    @Column(nullable = true, length = 64)
    private String profilePhoto;

    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
    private List<Skills> skills;

    public JobSeekerProfile() {
    }

    public JobSeekerProfile(Users userId) {
        this.userId = userId;
    }



    /**
         * Constructs a new `JobSeekerProfile` object with the specified details.
         *
         * @param userAccountId The unique identifier for the job seeker's user account.
         * @param userId The user associated with this job seeker profile, represented by a `Users` object.
         * @param firstName The first name of the job seeker.
         * @param lastName The last name of the job seeker.
         * @param city The city where the job seeker resides.
         * @param state The state where the job seeker resides.
         * @param country The country where the job seeker resides.
         * @param workAuthorization The work authorization status of the job seeker.
         * @param employmentType The type of employment the job seeker is looking for.
         * @param resume The resume of the job seeker.
         * @param profilePhoto The profile photo of the job seeker.
         * @param skills A list of skills possessed by the job seeker, represented by `Skills` objects.
         */
    public JobSeekerProfile(Integer userAccountId, Users userId, String firstName, String lastName, String city, String state, String country, String workAuthorization, String employmentType, String resume, String profilePhoto, List<Skills> skills) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.workAuthorization = workAuthorization;
        this.employmentType = employmentType;
        this.resume = resume;
        this.profilePhoto = profilePhoto;
        this.skills = skills;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
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

    public String getWorkAuthorization() {
        return workAuthorization;
    }

    public void setWorkAuthorization(String workAuthorization) {
        this.workAuthorization = workAuthorization;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }


    @Transient
    public String getPhotosImagePath(){
        if(profilePhoto == null || userAccountId == null){
            return null;
        }
        return "/photos/candidate/" + userAccountId + "/" + profilePhoto;
    }

    @Override
    public String toString() {
        return "JobSeekerProfile{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", workAuthorization='" + workAuthorization + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", resume='" + resume + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
