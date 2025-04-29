package com.example.back_app_rjf.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.back_app_rjf.model.FormSchema;
import com.example.back_app_rjf.service.FormSchemaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/formschema")
public class FormSchemaController {

    private static final Logger logger = LoggerFactory.getLogger(FormSchemaController.class);
    private final FormSchemaService formSchemaService;
    private final ObjectMapper objectMapper;

    public FormSchemaController(FormSchemaService formSchemaService, ObjectMapper objectMapper) {
        this.formSchemaService = formSchemaService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Map<String, String>> getFormSchemaList() {
        logger.info("getFormSchemaList Method called.");
        List<FormSchema> formSchemaList = formSchemaService.getFormSchemaAll();

        return formSchemaList.stream()
                .map(formSchema -> Map.of(
                        "id", formSchema.get_id(),
                        "formName", formSchema.getFormName(),
                        "formDescription", formSchema.getFormDescription()))
                .toList();
    }

    @GetMapping("/{id}/schema-and-ui")
    public ResponseEntity<?> getFormSchemaAndUiSchema(@PathVariable("id") String id) {
        logger.info("getFormSchemaAndUiSchema Method called with id: " + id);
        FormSchema formSchema = formSchemaService.getFormSchemaById(id);
        if (formSchema == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "FormSchema not found"));
        }

        try {
            Map<String, Object> schema = objectMapper.readValue(
                    formSchema.getFormSchema(),
                    new TypeReference<Map<String, Object>>() {
                    });
            Map<String, Object> uiSchema = objectMapper.readValue(
                    formSchema.getUiSchema(),
                    new TypeReference<Map<String, Object>>() {
                    });
            return ResponseEntity.ok(Map.of("schema", schema, "uiSchema", uiSchema));
        } catch (JsonProcessingException e) {
            logger.error("JSON parsing error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid JSON format"));
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

}
