package com.example.back_app_rjf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.back_app_rjf.model.FormData;

@Repository
public interface FormDataRepository extends MongoRepository<FormData, String> {
    // Custom query methods can be defined here if needed
    // For example, find by a specific field in FormData
    // List<FormData> findByFieldName(String fieldName);

}
