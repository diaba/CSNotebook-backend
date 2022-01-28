package com.backend.csnotebook.repository;

import com.backend.csnotebook.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByUserIdAndName(Long id, String name);
}
