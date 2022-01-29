package com.backend.csnotebook.service;

import com.backend.csnotebook.exceptions.InfoDoesNotExistException;
import com.backend.csnotebook.exceptions.NotLoggedInException;
import com.backend.csnotebook.exceptions.RestrictedAccessException;
import com.backend.csnotebook.model.Card;
import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.repository.CardRepository;
import com.backend.csnotebook.repository.TopicRepository;
import com.backend.csnotebook.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    /** Returns the cards belonging to the topic searched for if it exists.
     * @param topicName The name of the topic in which to return cards from.
     * @return A list of cards belonging to the searched topic.
     */
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


    /** Creates a new card in a given topic if the user is logged in and has access to the specific topic.
     * @param topicName The name of the topic in which to add the card to.
     * @param cardObject The card object containing the information for the new card
     * @return The newly created card.
     */
    public Card createNewCard(String topicName, Card cardObject) {
        LOGGER.info("Calling createNewCard() method from CardService!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().isEmpty()) {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Topic topic = topicRepository.findByName(topicName);
            if (topic == null){
                throw new InfoDoesNotExistException("A topic with the name of: '" + topicName +"' does not exist!");
            }
            else if (topic.getUser().getEmail().equals(userDetails.getUser().getEmail())){
                cardObject.setTopic(topic);
                return cardRepository.save(cardObject);
            }
            else{
                throw new RestrictedAccessException("You may not add cards to this topic!");
            }
        }
        else{
            throw new NotLoggedInException("You must be logged in to add custom cards!");
        }
    }

    /** Updates a card in a specific topic belonging to the logged-in user.
     * @param topicName The name of the topic in which the card to update exists.
     * @param cardId The ID of the card in which to update.
     * @param cardObject The card object containing the update information.
     * @return The updated card.
     */
    public Card updateCard(String topicName, Long cardId, Card cardObject) {
        LOGGER.info("Calling updateCard() method from CardService");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().isEmpty()) {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Topic topic = topicRepository.findByName(topicName);
            if (topic == null){
                throw new InfoDoesNotExistException("A topic with the name of: '" + topicName +"' does not exist!");
            }
            else if (Objects.equals(topic.getUser().getEmail(), userDetails.getUser().getEmail())){
                Card card = cardRepository.findByIdAndTopicId(cardId, topic.getId());
                if(card == null){
                    throw new InfoDoesNotExistException("A card with ID: " + cardId + " doesn't exist!");
                }
                else{
                    card.setQuestion(cardObject.getQuestion());
                    card.setAnswer(cardObject.getAnswer());
                    card.setLink(cardObject.getLink());
                    return cardRepository.save(card);
                }
            }
            else{

                throw new RestrictedAccessException("You cannot alter cards in this topic!");
            }
        }
        else{
            throw new NotLoggedInException("You must be logged in to add custom cards!");
        }
    }


    /** Deletes a card in a given topic that belongs to the logged-in user!
     * @param topicName The name of the topic in which the card exists.
     * @param cardId The ID of the card in which to delete!
     */
    public void deleteCard(String topicName, Long cardId) {
        LOGGER.info("Calling deleteCard() method from CardService!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().isEmpty()) {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Topic topic = topicRepository.findByName(topicName);
            if (topic == null){
                throw new InfoDoesNotExistException("A topic with the name of: '" + topicName +"' does not exist!");
            }
            else if (topic.getUser().getEmail().equals(userDetails.getUser().getEmail())){
                Card card = cardRepository.findByIdAndTopicId(cardId, topic.getId());
                if(card == null){
                    throw new InfoDoesNotExistException("A card with ID: " + cardId + " doesn't exist!");
                }
                else{
                    cardRepository.delete(card);
                }
            }
            else{

                throw new RestrictedAccessException("You cannot alter cards in this topic!");
            }
        }
        else{
            throw new NotLoggedInException("You must be logged in to add custom cards!");
        }
    }

    /** Returns all cards that are available to users not logged in.
     * @return A list of all freely available cards.
     */
    public List<Card> getAllFreeCards() {
        LOGGER.info("Calling getAllFreeCards method from CardService!");
        List<Topic> topics = topicRepository.findByUserId(null);
        List<Card> cards = new ArrayList<>();
        topics.forEach(topic -> {
            cards.addAll(topic.getCards());
        });
        return cards;
    }
}
