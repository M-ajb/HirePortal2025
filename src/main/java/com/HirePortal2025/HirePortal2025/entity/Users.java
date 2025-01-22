package com.HirePortal2025.HirePortal2025.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
/**
 * De `Users`-entiteit vertegenwoordigt een gebruiker in het systeem.
 * Het bevat essentiële gebruikersinformatie, zoals e-mailadres, wachtwoord,
 * activatiestatus, registratiedatum en de gekoppelde gebruikersrol.
 *
 * Deze klasse wordt gebruikt om gebruikersgegevens te beheren en biedt ondersteuning
 * voor het koppelen van gebruikers aan verschillende rollen via een relatie met de
 * `UsersType`-entiteit.
 *
 * Belangrijk:
 * - `email` moet uniek zijn.
 * - `password` mag niet leeg zijn.
 * - De `registrationDate` volgt het patroon "dd-MM-yyyy".
 *
 * Annotaties:
 * - `@Entity` en `@Table`: Maken deze klasse een JPA-entiteit gekoppeld aan de `users`-tabel.
 * - `@Id` en `@GeneratedValue`: Gebruikt voor de primaire sleutel.
 * - `@ManyToOne`: Voor de relatie met de `UsersType`-entiteit.
 */
@Entity
@Table(name= "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private boolean isActive;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId")
    private UsersType userTypeId;


    public Users() {
    }


    public Users(int userId, String email, String password, boolean isActive, Date registrationDate, UsersType userTypeId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.registrationDate = registrationDate;
        this.userTypeId = userTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UsersType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UsersType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", registrationDate=" + registrationDate +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
