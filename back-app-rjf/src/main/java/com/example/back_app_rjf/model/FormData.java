package com.example.back_app_rjf.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "formdata")
public class FormData {
    private String _id;
    private String formSchemaId;
    private Map<String, Object> formData; // JSON object representation of FormData

    public FormData(String formSchemaId, Map<String, Object> formData) {
        this.formSchemaId = formSchemaId;
        this.formData = formData;
    }
}