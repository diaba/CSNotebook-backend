package com.backend.csnotebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

/** The User class represents the individual user
 * that interacts with the application. This class facilitates the creation
 * and manipulation of User objects. */
@Entity
@Table(name = "users")
public class User {

    /** User ID Property automatically generated in the database. */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** User's Email Property which is used to uniquely
     * ID the user in the application */
    @Column(unique = true)
    private String email;

    /** User's Password Property when allows the user access to the app.*/
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /** User's Days Property - containing the number of days */
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Topic> topics;

    /** Default Constructor
     */
    public User() {
    }

    /** Retrieves the User's ID.
     * @return id - (auto-generated in database) */
    public Long getId() {
        return id;
    }

    /** Sets the User's ID
     * @param id - ID to set is auto-generated in database.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Retrieve's the User's E-Mail Address.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /** Sets the User's E-Mail Address.
     * @param email - Email address chosen by the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Retrieve's the User's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /** Sets the User's Password.
     * @param password - Password chosen by the User.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Retrieves the User's log of Topics. Topics contain Cards with flash cards info.
     * @return topics
     */
    public List<Topic> getTopics() {
        return topics;
    }

    /** Sets the User's log of days.
     * @param topics - The topics selected by the User which hold Cards with study information.
     */
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
