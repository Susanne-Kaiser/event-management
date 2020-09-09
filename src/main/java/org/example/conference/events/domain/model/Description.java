package org.example.conference.events.domain.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.example.conference.common.domain.model.ValidationResult;

import lombok.Getter;

@Getter
public class Description {

    private static final int MAX_LENGTH = 1024;
    private final String value;

    public Description(@Nonnull String description) {
        final ValidationResult validation = new ValidationResult();
        if (description.trim().isEmpty()) {
            validation.addError("description", "description must not be empty");
        }
        if (description.length() > MAX_LENGTH) {
            validation.addError("description", "description must not be longer than " + MAX_LENGTH + " characters");
        }
        validation.validate();
        this.value = description;

    }

    @Nullable
    public static Description nullSafeCreate(@Nullable String description) {
        if (description == null) {
            return null;
        }
        return new Description(description);
    }

}
