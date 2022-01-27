package com.backend.csnotebook.service;

import com.backend.csnotebook.exceptions.InfoDoesNotExistException;
import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(topics == null){
            throw new InfoDoesNotExistException("No Topics Found!");
        }
        else{
            return topics;
        }
    }
}
