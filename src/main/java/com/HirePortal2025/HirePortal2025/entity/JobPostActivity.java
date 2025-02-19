package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * The `JobPostActivity` class represents a job post activity in the HirePortal2025 application.
 * It contains information about the job post, including the job details, the user who posted it,
 * the job location, the company, and other relevant details.
 *
 * Fields:
 * - `jobPostId`: The unique identifier for the job post.
 * - `postedById`: The user who posted the job, represented by a `Users` object.
 * - `jobLocationId`: The location of the job, represented by a `JobLocation` object.
 * - `jobCompanyId`: The company offering the job, represented by a `JobCompany` object.
 * - `isActive`: A transient field indicating whether the job post is active.
 * - `isSaved`: A transient field indicating whether the job post is saved by the user.
 * - `descriptionOfJob`: A detailed description of the job.
 * - `jobType`: The type of job (e.g., full-time, part-time).
 * - `salary`: The salary offered for the job.
 * - `remote`: Indicates if the job is remote.
 * - `postedDate`: The date when the job was posted.
 * - `jobTitle`: The title of the job.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the job post details.
 * - `toString` method to provide a string representation of the job post activity.
 */
@Entity
public class JobPostActivity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;


    @ManyToOne
    @JoinColumn(name = "postedById" , referencedColumnName = "userId")
    private Users postedById;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId" , referencedColumnName = "Id")
    private JobLocation jobLocationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobCompanyId", referencedColumnName = "Id")
    private JobCompany jobCompanyId;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSaved;

    @Column(columnDefinition = "TEXT")
    private String descriptionOfJob;

    private String jobType;
    private String salary;
    private String remote;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postedDate;
    private String jobTitle;

    public JobPostActivity() {
    }


    /**
     * Constructs a new `JobPostActivity` object with the specified details.
     *
     * @param jobPostId The unique identifier for the job post.
     * @param postedById The user who posted the job, represented by a `Users` object.
     * @param jobLocationId The location of the job, represented by a `JobLocation` object.
     * @param jobCompanyId The company offering the job, represented by a `JobCompany` object.
     * @param isActive A transient field indicating whether the job post is active.
     * @param isSaved A transient field indicating whether the job post is saved by the user.
     * @param descriptionOfJob A detailed description of the job.
     * @param jobType The type of job (e.g., full-time, part-time).
     * @param salary The salary offered for the job.
     * @param remote Indicates if the job is remote.
     * @param postedDate The date when the job was posted.
     * @param jobTitle The title of the job.
     */
    public JobPostActivity(Integer jobPostId, Users postedById, JobLocation jobLocationId, JobCompany jobCompanyId, Boolean isActive, Boolean isSaved, String descriptionOfJob, String jobType, String salary, String remote, Date postedDate, String jobTitle) {
        this.jobPostId = jobPostId;
        this.postedById = postedById;
        this.jobLocationId = jobLocationId;
        this.jobCompanyId = jobCompanyId;
        this.isActive = isActive;
        this.isSaved = isSaved;
        this.descriptionOfJob = descriptionOfJob;
        this.jobType = jobType;
        this.salary = salary;
        this.remote = remote;
        this.postedDate = postedDate;
        this.jobTitle = jobTitle;
    }


    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean saved) {
        isSaved = saved;
    }


    public String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "JobPostActivity{" +
                "jobPostId=" + jobPostId +
                ", postedById=" + postedById +
                ", jobLocationId=" + jobLocationId +
                ", jobCompanyId=" + jobCompanyId +
                ", isActive=" + isActive +
                ", isSaved=" + isSaved +
                ", descriptionOfJob='" + descriptionOfJob + '\'' +
                ", jobType='" + jobType + '\'' +
                ", salary='" + salary + '\'' +
                ", remote='" + remote + '\'' +
                ", postedDate=" + postedDate +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
