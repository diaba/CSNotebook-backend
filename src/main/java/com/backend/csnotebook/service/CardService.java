package com.backend.csnotebook.service;

import com.backend.csnotebook.exceptions.InfoDoesNotExistException;
import com.backend.csnotebook.model.Card;
import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.repository.CardRepository;
import com.backend.csnotebook.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/** The CardService class handles the business logic involving the study cards. */
@Service
public class CardService {

    private final Logger LOGGER = Logger.getLogger(CardService.class.getName());

    private TopicRepository topicRepository;
    @Autowired
    public void setTopicRepository(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }

    private CardRepository cardRepository;
    @Autowired
    public void setCardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCardsByTopic(String topicName) {
        LOGGER.info("Calling getAllCardsByTopic() method from CardService!");
        Topic topic = topicRepository.findByName(topicName);
        if(topic == null){
            throw new InfoDoesNotExistException("A topic with the name: '" + topicName +"' does not exist!");
        }
        else{
            List<Card> cards = cardRepository.findAllByTopicId(topic.getId());
            if (cards.isEmpty()){
                throw new InfoDoesNotExistException("No cards exist for the topic: " + topicName + "!");
            }
            else{
                return cards;
            }
        }
    }








}
