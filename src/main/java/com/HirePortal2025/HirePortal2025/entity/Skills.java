package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;


/**
 * Represents a skill entity in the database.
 * This class is mapped to the "skills" table in the database.
 * It contains information about the skill such as name, experience level,
 * years of experience, and the associated job seeker profile.
 *
 * Fields:
 * - id: The unique identifier for the skill.
 * - name: The name of the skill.
 * - experienceLevel: The level of experience for the skill.
 * - yearsOfExperience: The number of years of experience with the skill.
 * - jobSeekerProfile: The job seeker profile associated with the skill, represented by a foreign key to the JobSeekerProfile entity.
 *
 * Key Functionalities:
 * - Getters and setters for all fields.
 * - Default constructor and parameterized constructor.
 * - toString method to provide a string representation of the skill entity.
 */
@Entity
@Table(name="skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String experienceLevel;

    private String yearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_seeker_profile")
    private JobSeekerProfile jobSeekerProfile;


    public Skills() {
    }

    /**
     * Creates a new skill entry for a job seeker.
     *
     * @param id Unique identifier for the skill.
     * @param name Skill name.
     * @param experienceLevel Experience level of the skill.
     * @param yearsOfExperience Number of years of experience.
     * @param jobSeekerProfile Associated job seeker profile.
     */
    public Skills(Integer id, String name, String experienceLevel, String yearsOfExperience, JobSeekerProfile jobSeekerProfile) {
        this.id = id;
        this.name = name;
        this.experienceLevel = experienceLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.jobSeekerProfile = jobSeekerProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public JobSeekerProfile getJobSeekerProfile() {
        return jobSeekerProfile;
    }

    public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
        this.jobSeekerProfile = jobSeekerProfile;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", jobSeekerProfile=" + jobSeekerProfile +
                '}';
    }
}
