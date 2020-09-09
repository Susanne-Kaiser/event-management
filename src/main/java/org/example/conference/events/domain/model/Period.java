package org.example.conference.events.domain.model;

import java.time.OffsetDateTime;

import javax.annotation.Nullable;

import org.example.conference.common.domain.model.ValidationResult;

import lombok.Getter;

@Getter
public class Period {

    private final OffsetDateTime startDate;
    private final OffsetDateTime endDate;

    public Period(OffsetDateTime startDate, OffsetDateTime endDate) {
        final ValidationResult validation = new ValidationResult();
        if (startDate == null) {
            validation.addError("startDate", "start date must be defined");
        }

        if (endDate == null) {
            validation.addError("endDate", "end date must be defined");
        }

        if (endDate.isBefore(startDate)) {
            validation.addError("endDate", "endDate must be after startDate");
        }
        validation.validate();

        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Nullable
    public static Period nullSafeCreate(@Nullable OffsetDateTime startDate, @Nullable OffsetDateTime endDate) {
        if (startDate == null && endDate == null) {
            return null;
        }
        return new Period(startDate, endDate);
    }

}
