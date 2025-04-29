package com.example.back_app_rjf.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "formschema")
public class FormSchema {
    private String _id;
    private String formName;
    private String formDescription;
    private String formSchema; // JSON string representation of FormData
    private String uiSchema; // JSON string representation of UiSchema
}