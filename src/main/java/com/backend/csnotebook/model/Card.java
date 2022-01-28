package com.backend.csnotebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/** The Card class contains the properties of an individual card.
 * This class facilitates the creation and manipulation of Card objects.
 */
@Entity
@Table(name = "cards")
public class Card {

    /** Card ID Property Auto-generated to uniquely ID the Card. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 300)
    private String question;

    @Column(length = 4000)
    private String answer;

    /** The meal property associated with the food */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;


    /** Card Class Default Constructor */
    public Card() {}

    /** Retrieves the Card ID.
     * @return id - (auto-generated in database) */
    public Long getId() {
        return id;
    }

    /** Sets the Card ID.
     * @param id - ID to set is auto-generated in database. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Retrieves the question of the given card.
     * @return question */
    public String getQuestion() {
        return question;
    }

    /** Sets the question for the given Card.
     * @param question The question for the card. */
    public void setQuestion(String question) {
        this.question = question;
    }

    /** Retrieves the answer for the given card.
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /** Sets the answer for a given card.
     * @param answer The answer for the card.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /** Retrieves the topic in which the card is related
     * @return topic */
    public Topic getTopic() {
        return topic;
    }

    /** Sets the topic for the associated card.
     * @param topic  The topic related to the card. */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
