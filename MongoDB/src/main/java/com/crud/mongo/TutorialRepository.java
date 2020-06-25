package com.crud.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crud.mongo.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
  List<Tutorial> findByTitleContaining(String title);
}