package com.example.back_app_rjf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_app_rjf.model.FormData;
import com.example.back_app_rjf.service.FormDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/formdata")
public class FormDataController {

    private static final Logger logger = LoggerFactory.getLogger(FormDataController.class);
    private final FormDataService formDataService;

    // コンストラクタで初期化
    public FormDataController(FormDataService formDataService) {
        this.formDataService = formDataService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitFormData(@RequestBody FormData formData) {
        logger.info("submitFormData Method called.");
        try {
            formDataService.saveFormData(formData.getFormSchemaId(), formData.getFormData());
            return ResponseEntity.ok("Form data saved successfully.");
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }
}
