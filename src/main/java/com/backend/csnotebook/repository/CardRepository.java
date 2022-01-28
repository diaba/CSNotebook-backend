package com.backend.csnotebook.repository;

import com.backend.csnotebook.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByTopicId(Long topicId);
}
