package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a type of user in the system.
 * This class is mapped to the "users_type" table in the database.
 * It contains information about the user type such as the type ID and type name.
 *
 * Fields:
 * - userTypeId: The unique identifier for the user type.
 * - userTypeName: The name of the user type.
 * - users: A list of users associated with this user type.
 *
 * Key Functionalities:
 * - Getters and setters for all fields.
 * - Default constructor and parameterized constructor.
 * - toString method to provide a string representation of the user type entity.
 */
@Entity
@Table(name = "users_type")
public class UsersType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;

    private String userTypeName;

    @OneToMany(targetEntity = Users.class, mappedBy = "userTypeId" ,cascade = CascadeType.ALL)
    private List<Users> users;



    public UsersType() {
    }

     /**
     * Constructs a new UsersType object with the specified details.
     *
     * @param userTypeId the unique identifier for the user type
     * @param userTypeName the name of the user type
     * @param users a list of users associated with this user type
     */
    public UsersType(int userTypeId, String userTypeName, List<Users> users) {
        this.userTypeId = userTypeId;
        this.userTypeName = userTypeName;
        this.users = users;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                '}';
    }
}
