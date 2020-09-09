package org.example.conference.events.domain.model;

import javax.annotation.Nullable;

import org.example.conference.common.domain.model.ValidationResult;

import lombok.Getter;

@Getter
public class Name {

    private static final int MAX_LENGTH = 100;
    private final String value;

    public Name(String name) {
        final ValidationResult validation = new ValidationResult();
        if (name == null || name.trim().isEmpty()) {
            validation.addError("name", "Name must not be empty");
        }

        if (name.length() > MAX_LENGTH) {
            validation.addError("name", "Name must not be longer than " + MAX_LENGTH + " characters");
        }
        validation.validate();
        this.value = name;
    }

    @Nullable
    public static Name nullSafeCreate(@Nullable String name) {
        if (name == null) {
            return null;
        }
        return new Name(name);
    }

}
