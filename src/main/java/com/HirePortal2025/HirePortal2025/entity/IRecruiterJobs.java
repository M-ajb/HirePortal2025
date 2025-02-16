package com.HirePortal2025.HirePortal2025.entity;
/**
 * The `IRecruiterJobs` interface represents recruiter job details.
 *
 * Fields:
 * - `totalCandidates`: Total number of candidates.
 * - `job_Post_Id`: Job post ID.
 * - `job_title`: Job title.
 * - `locationId`: Location ID.
 * - `city`: Job city.
 * - `state`: Job state.
 * - `country`: Job country.
 * - `companyId`: Company ID.
 * - `name`: Company name.
 *
 * Purpose:
 * - Retrieve job-related information for recruiters.
 *
 * Key Functionalities:
 * - Getter methods for all fields.
 */
public interface IRecruiterJobs {

    Long getTotalCandidates();

    int getJob_Post_Id();

    String getJob_title();

    int getLocationId();

    String getCity();

    String getState();

    String getCountry();

    int getCompanyId();

    String getName();
}
