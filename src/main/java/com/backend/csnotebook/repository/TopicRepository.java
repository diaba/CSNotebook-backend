package com.backend.csnotebook.repository;

import com.backend.csnotebook.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Handles all queries related to the Topics table */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    /** Finds a topic by its name and the user ID associated with it.
     * @param id The user's ID
     * @param name The name of the topic
     * @return The topic matching the search name and belonging to the user with the matching ID.
     */
    Topic findByUserIdAndName(Long id, String name);

    /** Finds a topic by the searched name.
     * @param topicName The name of the topic in which to search.
     * @return The topic with the matching name.*/
    Topic findByName(String topicName);

    /** Finds a list of topics belonging to a specific User.
     * @param id The ID of the user which is assigned to the Topic.
     * @return A list of topics belonging to the User with the matching ID.
     */
    List<Topic> findByUserId(Long id);

    /** Finds all categories belonging to a specific user.
     * @param id The ID of the user in which to search.
     * @return A list of topics belonging to the user with the matching ID
     */
    List<Topic> findAllByUserId(Long id);
}
