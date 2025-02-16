package com.HirePortal2025.HirePortal2025.entity;


/**
 * The `RecruiterJobsDto` class is a Data Transfer Object (DTO) that encapsulates the details of a job post created by a recruiter.
 * It includes information about the total number of candidates, job post ID, job title, job location, and job company.
 *
 * This class is used to transfer job-related data between different layers of the application.
 *
 * Fields:
 * - totalCandidates: The total number of candidates who have applied for the job.
 * - jobPostId: The unique identifier for the job post.
 * - jobTitle: The title of the job post.
 * - jobLocationId: The location details of the job post, represented by a `JobLocation` object.
 * - jobCompanyId: The company details of the job post, represented by a `JobCompany` object.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the job post details.
 */
public class RecruiterJobsDto {

    private Long totalCandidates;
    private Integer jobPostId;
    private String jobTitle;
    private JobLocation jobLocationId;
    private JobCompany jobCompanyId;


/**
 * Constructs a new `RecruiterJobsDto` object with the specified details.
 *
 * @param totalCandidates The total number of candidates who have applied for the job.
 * @param jobPostId The unique identifier for the job post.
 * @param jobTitle The title of the job post.
 * @param jobLocationId The location details of the job post, represented by a `JobLocation` object.
 * @param jobCompanyId The company details of the job post, represented by a `JobCompany` object.
 */
    public RecruiterJobsDto(Long totalCandidates, Integer jobPostId, String jobTitle, JobLocation jobLocationId, JobCompany jobCompanyId) {
        this.totalCandidates = totalCandidates;
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.jobLocationId = jobLocationId;
        this.jobCompanyId = jobCompanyId;
    }

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobLocation getJobLocationId() {
        return jobLocationId;
    }

    public void setJobLocationId(JobLocation jobLocationId) {
        this.jobLocationId = jobLocationId;
    }

    public JobCompany getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(JobCompany jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }



}
