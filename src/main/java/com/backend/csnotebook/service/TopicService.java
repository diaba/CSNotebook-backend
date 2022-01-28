package com.backend.csnotebook.service;

import com.backend.csnotebook.exceptions.InfoDoesNotExistException;
import com.backend.csnotebook.exceptions.InfoExistsException;
import com.backend.csnotebook.exceptions.NotLoggedInException;
import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.repository.TopicRepository;
import com.backend.csnotebook.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/** The TopicService class responsible for handling business logic related to Topics. */
@Service
public class TopicService {

    private TopicRepository topicRepository;
    private final Logger LOGGER = Logger.getLogger(TopicService.class.getName());

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }

    /** Called from the TopicController to return a list of topics.
     * @return A list of topics. */
    public List<Topic> getAllTopics() {
        LOGGER.info("Called getAllTopics() method from TopicService");
        List<Topic> topics = topicRepository.findAll();
        if(topics.isEmpty()){
            throw new InfoDoesNotExistException("No Topics Found!");
        }
        else{
            return topics;
        }
    }

    /** Called by the TopicController to find a topic by its ID.
     * @param topicId The ID of the topic to search for.
     * @return topic matching the topic ID.
     */
    public Optional<Topic> getTopicById(Long topicId) {
        LOGGER.info("Calling getTopicById() method TopicService!");
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isPresent()){
            return topic;
        }
        else{
            throw new InfoDoesNotExistException("Topic with ID " + topicId + " does not exist!");
        }
    }

    /** Called by the TopicController to create a new object IF a user is logged in!
     * @param topicObject The object a logged-in user wants to create.
     * @return topic to be saved in the database.
     */
    public Topic createTopic(Topic topicObject) {
        LOGGER.info("Calling createTopic() service from TopicService!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().isEmpty()){
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Topic topic = topicRepository.findByUserIdAndName(userDetails
                    .getUser().getId(), topicObject.getName());
            if (topic == null){
                topicObject.setUser(userDetails.getUser());
                return topicRepository.save(topicObject);
            }
            else{
                throw new InfoExistsException("A topic with that name already exists!");
            }
        }
        else{
            throw new NotLoggedInException("You must be logged in!");
        }
    }

    public Topic updateTopic(String topicName, Topic topicObject) {
        LOGGER.info("Called updateTopic() method from TopicService!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().isEmpty()){
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            Topic topic = topicRepository.findByUserIdAndName(userDetails.getUser().getId(), topicName);
            if (topic == null){
                throw new InfoDoesNotExistException("A topic with the name '" + topicName + "' doesn't exist!");
            }
            else{
                topic.setName(topicObject.getName());
                topic.setDescription(topicObject.getDescription());
                topic.setUser(userDetails.getUser());
                return topicRepository.save(topic);
            }
        }
        else {
            throw new NotLoggedInException("You must be logged in!");
        }
    }
}
