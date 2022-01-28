package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Card;
import com.backend.csnotebook.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/** The CardController class serves as the controller for managing the flow of food data. */
@RestController
@RequestMapping("api/topics")
public class CardController {

    private CardService cardService;
    private static final Logger LOGGER = Logger.getLogger(CardController.class.getName());

    @Autowired
    public void setCardService(CardService cardService){
        this.cardService = cardService;
    }

    /** Returns all cards of a specific topic!
     * @param topicName The name of the topic to retrieve its cards
     * @return The list of cards belonging to the searched topic. */
    // GET ALL CARDS BY TOPIC
    @GetMapping("/{topicName}/cards")
    public List<Card> getAllCardsByTopic(@PathVariable(value = "topicName") String topicName) {
        LOGGER.info("Calling getAllCardsByTopic() Method from CardController");
        return cardService.getAllCardsByTopic(topicName);
    }

    /** Create a new card within the designated topic.
     * @param topicName The name of the topic in which to create the card.
     * @param cardObject The card object containing the information about the card.
     * @return The newly created card.
     */
    // CREATE NEW CARD
    @PostMapping("{topicName}/cards")
    public Card createNewCard(@PathVariable(value = "topicName") String topicName,
                              @RequestBody Card cardObject) {
        LOGGER.info("Calling createNewCard() method from CardController!");
        return cardService.createNewCard(topicName, cardObject);
    }

    /** Updates a card with new information for a given topic.
     * @param topicName The name of the topic in which the card to update exists.
     * @param cardId The ID of the card in which to update
     * @param cardObject The card object containing the new card information.
     * @return A newly updated object.
     */
    // UPDATE CARD BY ID
    @PutMapping("{topicName}/cards/{cardId}")
    public Card updateCard(@PathVariable(value = "topicName") String topicName,
                           @PathVariable(value = "cardId") Long cardId,
                           @RequestBody Card cardObject){
        LOGGER.info("Calling updateCard() method from CardController");
        return cardService.updateCard(topicName, cardId, cardObject);
    }


    /** Deletes a card belonging to a topic of the logged-in user.
     * @param topicName The name of the topic to search containing the card to be deleted.
     * @param cardId The ID of the card to delete.
     */
    // DELETE A CARD BY ID
    @DeleteMapping("{topicName}/cards/{cardId}")
    public void deleteCard(@PathVariable(value = "topicName") String topicName,
                           @PathVariable(value = "cardId") Long cardId){
        LOGGER.info("Calling deleteCard method from CardController!");
        cardService.deleteCard(topicName, cardId);
    }
}
