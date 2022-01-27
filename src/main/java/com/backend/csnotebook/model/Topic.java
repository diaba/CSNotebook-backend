package com.backend.csnotebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

/** The Topic class encapsulates a User's selects / generated cards which hold study information.
 * This class facilitates the creation and manipulation of Topic objects.
 */
@Entity
@Table(name = "topics")
public class Topic {
    /** Topic ID Property - Auto-generated to uniquely ID the Topic. */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Topic name property - Represents the name of the given topic. */
    @Column
    private String name;

    /** Topic description property Represents the description of the topic. */
    @Column
    private String description;

    /** The Topic's Card Property - Contains all Cards entered by the user. */
    @OneToMany(mappedBy = "card", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Card> cards;

    /** The User in which a given day is associated. Many days can be associated with a single user.*/
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /** Topic Class default constructor */
    public Topic() {
    }

    /** Retrieves the Topic ID.
     * @return id - (auto-generated in database) */
    public Long getId() {
        return id;
    }

    /** Sets the Topic ID.
     * @param id - ID to set is auto-generated in database. */
    public void setId(Long id) {
        this.id = id;
    }

    /** Retrieves the Topic's Name.
     * @return name */
    public String getName() {
        return name;
    }

    /** Sets the Topic's name.
     * @param name - The name entered for the topic. */
    public void setName(String name) {
        this.name = name;
    }

    /** Retrieves the Topic's description and gives a general overview of the topic.
     * @return description  */
    public String getDescription() {
        return description;
    }

    /** Sets the Topic's description.
     * @param description - The description for the topic. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Retrieves all the cards assigned to the given topic.
     * @return cards */
    public List<Card> getCards() {
        return cards;
    }

    /** Sets the list of cards to the given topic.
     * @param cards The cards related to the topic. */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /** Retrieves the User ID associated with a specific card.
     * @return user */
    public User getUser() {
        return user;
    }

    /** Sets the user for a specific topic
     * @param user The user assigned to a particular topic.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
