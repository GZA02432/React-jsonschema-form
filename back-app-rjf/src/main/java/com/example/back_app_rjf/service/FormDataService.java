package com.example.back_app_rjf.service;

import com.example.back_app_rjf.model.FormData;
import com.example.back_app_rjf.model.FormSchema;
import com.example.back_app_rjf.repository.FormDataRepository;
import com.example.back_app_rjf.repository.FormSchemaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.back_app_rjf.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

@Service
public class FormDataService {

    private static final Logger logger = LoggerFactory.getLogger(FormDataService.class);

    private final FormDataRepository formDataRepository;
    private final FormSchemaRepository formSchemaRepository;
    private final ObjectMapper objectMapper;

    public FormDataService(FormDataRepository formDataRepository, FormSchemaRepository formSchemaRepository,
            ObjectMapper objectMapper) {
        this.formDataRepository = formDataRepository;
        this.formSchemaRepository = formSchemaRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void saveFormData(String formSchemaId, Map<String, Object> formData) throws Exception {
        logger.info("saveFormData called with formSchemaId: {}", formSchemaId);

        FormSchema formSchema = formSchemaRepository.findById(formSchemaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("FormSchema with ID '%s' not found in the database.", formSchemaId)));

        JsonNode schemaNode = objectMapper.readTree(formSchema.getFormSchema());
        JsonNode dataNode = objectMapper.valueToTree(formData);

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemaNode);

        Set<ValidationMessage> validationMessages = schema.validate(dataNode);
        if (!validationMessages.isEmpty()) {
            validationMessages.forEach(message -> logger.error("Validation error: {}", message.getMessage()));
            throw new ValidationException("Validation failed", validationMessages);
        }

        formDataRepository.save(new FormData(formSchemaId, formData));
        logger.info("FormData saved successfully for formSchemaId: {}", formSchemaId);
    }
}
