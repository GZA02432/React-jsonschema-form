package com.example.back_app_rjf.exception;

import java.util.Set;
import java.util.stream.Collectors;
import com.networknt.schema.ValidationMessage;

public class ValidationException extends RuntimeException {
    private final Set<ValidationMessage> validationMessages;

    public ValidationException(String message, Set<ValidationMessage> validationMessages) {
        super(message);
        this.validationMessages = validationMessages;
    }

    public Set<ValidationMessage> getValidationMessages() {
        return validationMessages;
    }

    public String getFormattedMessages() {
        return validationMessages.stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.joining(", "));
    }
}