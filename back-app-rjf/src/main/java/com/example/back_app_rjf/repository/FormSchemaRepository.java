package com.example.back_app_rjf.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.back_app_rjf.model.FormSchema;

@Repository
public interface FormSchemaRepository extends MongoRepository<FormSchema, String> {
    // Custom query methods can be defined here if needed
    // For example, find by a specific field in FormSchema
    // List<FormSchema> findByFieldName(String fieldName);

    // formschemaコレクションから_idでFormSchemaを取得するメソッド
    Optional<FormSchema> findById(String id); // _idでFormSchemaを取得するメソッド

    // formnameでFormSchemaを取得するメソッド
    Optional<FormSchema> findByFormName(String formName);
}
