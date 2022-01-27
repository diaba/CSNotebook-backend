package com.backend.csnotebook.controller;

import com.backend.csnotebook.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/** The TopicController class serves as the controller for managing the flow of data concerning Topics. */
@RestController
@RequestMapping("/api")
public class TopicController {

    private TopicService topicService;
    private static final Logger LOGGER = Logger.getLogger(TopicController.class.getName());

}
