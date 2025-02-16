package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The `JobSeekerSave` class represents an entity that captures the relationship between a job seeker and a saved job post.
 * It is used to store information about which job posts have been saved by which job seekers.
 *
 * Fields:
 * - `id`: The unique identifier for the saved job entry.
 * - `userId`: The job seeker who saved the job post, represented by a `JobSeekerProfile` object.
 * - `job`: The job post that has been saved, represented by a `JobPostActivity` object.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the saved job entry details.
 * - Default constructor and parameterized constructor for creating instances of `JobSeekerSave`.
 * - `toString` method to provide a string representation of the saved job entry.
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "job"})
})
public class JobSeekerSave implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
    private JobSeekerProfile userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job", referencedColumnName = "jobPostId")
    private JobPostActivity job;

    public JobSeekerSave() {
    }

    /**
     * Constructs a new `JobSeekerSave` object with the specified details.
     *
     * @param id The unique identifier for the saved job entry.
     * @param userId The job seeker who saved the job post, represented by a `JobSeekerProfile` object.
     * @param job The job post that has been saved, represented by a `JobPostActivity` object.
     */
    public JobSeekerSave(Integer id, JobSeekerProfile userId, JobPostActivity job) {
        this.id = id;
        this.userId = userId;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobSeekerProfile getUserId() {
        return userId;
    }

    public void setUserId(JobSeekerProfile userId) {
        this.userId = userId;
    }

    public JobPostActivity getJob() {
        return job;
    }

    public void setJob(JobPostActivity job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobSeekerSave{" +
                "id=" + id +
                ", userId=" + userId.toString() +
                ", jobPostId=" + job.toString() +
                '}';
    }
}
