package com.HirePortal2025.HirePortal2025.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The `JobLocation` class represents a location where a job is based in the HirePortal2025 application.
 * It contains information about the city, state, and country of the job location.
 *
 * Fields:
 * - `Id`: The unique identifier for the job location.
 * - `city`: The city where the job is located.
 * - `state`: The state where the job is located.
 * - `country`: The country where the job is located.
 *
 * Purpose:
 * - To store and manage information about job locations in the HirePortal2025 application.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the job location details.
 * - `toString` method to provide a string representation of the job location.
 */
@Entity
public class JobLocation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;
    private String state;
    private String country;


    public JobLocation() {
    }

    /**
     * Constructs a new `JobLocation` object with the specified details.
     *
     * @param id The unique identifier for the job location.
     * @param city The city where the job is located.
     * @param state The state where the job is located.
     * @param country The country where the job is located.
     */
    public JobLocation(Integer id, String city, String state, String country) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "JobLocation{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
