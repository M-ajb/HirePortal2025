package com.HirePortal2025.HirePortal2025.repository;

import com.HirePortal2025.HirePortal2025.entity.IRecruiterJobs;
import com.HirePortal2025.HirePortal2025.entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * The `JobPostActivityRepository` interface extends the JpaRepository interface
 * to provide CRUD operations for the JobPostActivity entity.
 *
 * This repository interface includes methods to retrieve job post activities
 * based on various criteria such as recruiter ID, job title, location, job type,
 * remote status, and posted date.
 *
 * Fields:
 * - JobPostActivity: The entity type that this repository manages.
 * - Integer: The type of the entity's identifier.
 *
 * Key Functionalities:
 * - getRecruiterJobs(int recruiter): Retrieves a list of job post activities
 *   along with the total number of candidates, job details, location, and company
 *   information for a specific recruiter.
 * - searchWithoutDate(String job, String location, List<String> remote, List<String> type):
 *   Searches for job post activities based on job title, location, job type, and remote status
 *   without considering the posted date.
 * - search(String job, String location, List<String> remote, List<String> type, LocalDate date):
 *   Searches for job post activities based on job title, location, job type, remote status,
 *   and posted date.
 */
public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Integer> {


    @Query(value = " SELECT COUNT(s.user_id) as totalCandidates,j.job_post_id,j.job_title,l.id as locationId,l.city,l.state,l.country,c.id as companyId,c.name FROM job_post_activity j " +
            " inner join job_location l " +
            " on j.job_location_id = l.id " +
            " INNER join job_company c  " +
            " on j.job_company_id = c.id " +
            " left join job_seeker_apply s " +
            " on s.job = j.job_post_id " +
            " where j.posted_by_id = :recruiter " +
            " GROUP By j.job_post_id" ,nativeQuery = true)
    List<IRecruiterJobs> getRecruiterJobs(@Param("recruiter") int recruiter);


    @Query(value = "SELECT * FROM job_post_activity j INNER JOIN job_location l on j.job_location_id=l.id  WHERE j" +
            ".job_title LIKE %:job%"
            + " AND (l.city LIKE %:location%"
            + " OR l.country LIKE %:location%"
            + " OR l.state LIKE %:location%) " +
            " AND (j.job_type IN(:type)) " +
            " AND (j.remote IN(:remote)) ", nativeQuery = true)
    List<JobPostActivity> searchWithoutDate(@Param("job") String job,
                                            @Param("location") String location,
                                            @Param("remote") List<String> remote,
                                            @Param("type") List<String> type);


    @Query(value = "SELECT * FROM job_post_activity j INNER JOIN job_location l on j.job_location_id=l.id  WHERE j" +
            ".job_title LIKE %:job%"
            + " AND (l.city LIKE %:location%"
            + " OR l.country LIKE %:location%"
            + " OR l.state LIKE %:location%) " +
            " AND (j.job_type IN(:type)) " +
            " AND (j.remote IN(:remote)) " +
            " AND (posted_date >= :date)", nativeQuery = true)
    List<JobPostActivity> search(@Param("job") String job,
                                 @Param("location") String location,
                                 @Param("remote") List<String> remote,
                                 @Param("type") List<String> type,
                                 @Param("date") LocalDate date);
}
