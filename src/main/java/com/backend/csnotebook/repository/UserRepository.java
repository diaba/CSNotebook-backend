package com.backend.csnotebook.repository;

import com.backend.csnotebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** Handles custom queries in the Users table */
public interface UserRepository extends JpaRepository<User, Long> {
    /** Determines if the user exists based on searched email result.
     * @param email The email in which to search and find if it exists.
     * @return A true / false result on if the search email exists.
     */
    Boolean existsByEmail(String email);

    /** Returns the user with the matching email address.
     * @param emailAddress The email in which to search for a given user.
     * @return The user with the matching email address.
     */
    User findUserByEmail(String emailAddress);
}
