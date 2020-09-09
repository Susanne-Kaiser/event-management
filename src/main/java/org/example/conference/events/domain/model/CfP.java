package org.example.conference.events.domain.model;

import javax.annotation.Nullable;

import org.example.conference.common.domain.model.ValidationResult;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CfP {

    private final Description description;
    private final Period period;

    @Builder
    public CfP(Description description, Period period) {

        final ValidationResult validation = new ValidationResult();
        if (period == null) {
            validation.addError("period", "period must be defined");
        }
        if (description == null) {
            validation.addError("description", "description must be defined");
        }
        validation.validate();
        this.description = description;
        this.period = period;
    }

    @Nullable
    public static CfP nullSafeCreate(@Nullable Description description, @Nullable Period period ) {
        if (description == null && period == null) {
            return null;
        }
        return new CfP(description, period);
    }
}
