package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/** The TopicController class serves as the controller for managing the flow of data concerning Topics. */
@RestController
@RequestMapping("api")
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

    /** Returns a specific topic with the given topic name.
     * @param topicName - The topic's Name in which to search
     * @return The topic that matches the given topic name.
     */
    // GET TOPIC BY ID (localhost:9092/api/topics/{topicName})
    @GetMapping("/topics/{topicName}")
    public Topic getTopicByName(@PathVariable(value = "topicName")String topicName){
        LOGGER.info("Calling the getTopicByName() method from TopicController!");
        return topicService.getTopicByName(topicName);
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

    /** Updates a topic belonging to a particular user.
     * @param topicName The ID of the topic to update.
     * @param topicObject The topic object holding to update information.
     * @return The newly updated topic. */
    // UPDATE  TOPIC (localhost:9092/api/topics/{topicName})
    @PutMapping("/topics/{topicName}")
    public Topic updateTopic(@PathVariable String topicName, @RequestBody Topic topicObject){
        LOGGER.info("Calling updateTopic() method from TopicController");
        return topicService.updateTopic(topicName, topicObject);
    }

    /** Deletes a topic identified by its name.
     * @param topicName The name of the topic to delete
     * @return The topic that was deleted.
     */
    // DELETE TOPIC (localhost:9092/api/topics/{topicName})
    @DeleteMapping("/topics/{topicName}")
    public Topic deleteTopic(@PathVariable String topicName){
        LOGGER.info("Calling deleteTopic from TopicController!");
        return topicService.deleteTopic(topicName);
    }
}
