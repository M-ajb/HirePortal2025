package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The `JobCompany` class represents a company that posts jobs in the HirePortal2025 application.
 * It contains information about the company's name and logo.
 *
 * Fields:
 * - `id`: The unique identifier for the company.
 * - `name`: The name of the company.
 * - `logo`: The logo of the company.
 *
 * Purpose:
 * - To store and manage information about companies that post jobs in the HirePortal2025 application.
 *
 * Key Functionalities:
 * - Provides getter and setter methods for all fields to access and modify the company details.
 * - `toString` method to provide a string representation of the company.
 */
@Entity
public class JobCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String logo;

    public JobCompany() {
    }


    /**
     * Constructs a new `JobCompany` object with the specified details.
     *
     * @param id The unique identifier for the company.
     * @param name The name of the company.
     * @param logo The logo of the company.
     */
    public JobCompany(Integer id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "JobCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
