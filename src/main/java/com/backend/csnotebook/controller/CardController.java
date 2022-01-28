package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Card;
import com.backend.csnotebook.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
