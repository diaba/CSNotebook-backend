package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/** The TopicController class serves as the controller for managing the flow of data concerning Topics. */
@RestController
@RequestMapping("/api")
public class TopicController {

    private TopicService topicService;
    private static final Logger LOGGER = Logger.getLogger(TopicController.class.getName());

    @Autowired
    public void setTopicService(TopicService topicService){
        this.topicService = topicService;
    }

    /** Returns ALL topics available belonging to the user.
     * @return topics */
    // GET ALL TOPICS (localhost:9092/api/topics)
    @GetMapping("/topics")
    public List<Topic> getAllTopics(){
        LOGGER.info("getAllTopics() Method called from TopicController!");
        return topicService.getAllTopics();
    }

    /** Returns a specific topic with the given topic ID.
     * @param topicId - The topic's ID in which to search
     * @return The topic that matches the given topic ID.
     */
    // GET TOPIC BY ID (localhost:9092/api/topics/{topicId}
    @GetMapping("/topics/{topicId}")
    public Optional<Topic> getTopicById(@PathVariable(value = "topicId")Long topicId){
        LOGGER.info("Calling the getTopicById() method from TopicController!");
        return topicService.getTopicById(topicId);
    }

    /** Creates a new topic if a user is logged into the app.
     * @param topic The topic the user wishes to create.
     * @return The newly created topic
     */
    // CREATE NEW TOPIC (localhost:9092/api/topics)
    @PostMapping("/topics")
    public Topic createTopic(@RequestBody Topic topic) {
        LOGGER.info("Calling the createTopic() method from TopicController!");
        return topicService.createTopic(topic);
    }
}
