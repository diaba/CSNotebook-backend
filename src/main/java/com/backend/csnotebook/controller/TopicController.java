package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Topic;
import com.backend.csnotebook.service.TopicService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    // GET ALL TOPICS (localhost:9092/api/topics)
    public List<Topic> getAllTopics(){
        LOGGER.info("getAllTopics() Method called from TopicController!");
        return topicService.getAllDays();
    }

}
