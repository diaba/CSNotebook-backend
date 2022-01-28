package com.backend.csnotebook.repository;

import com.backend.csnotebook.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Handles the search queries related to the Cards table. */
public interface CardRepository extends JpaRepository<Card, Long> {

    /** Finds a list of cards belonging to a specific Topic ID.
     * @param topicId The ID of the topic in which to search.
     * @return The list of cards belonging to the topic.
     */
    List<Card> findAllByTopicId(Long topicId);

    /** Find a card by its ID the ID of the topic to which it belongs.
     * @param cardId The id of the card in which to search.
     * @param topicId The topic ID of the topic in which to card belongs.
     * @return The card that matches the Card ID and belonging to the Topic matching the Topic ID.
     */
    Card findByIdAndTopicId(Long cardId, Long topicId);


}
