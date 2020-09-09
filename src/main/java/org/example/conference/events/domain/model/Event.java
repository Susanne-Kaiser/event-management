package org.example.conference.events.domain.model;

import org.example.conference.common.domain.model.ValidationResult;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Event {
    private EventId id;
    private Name name;
    private Description description;
    private Period period;
    private CfP cfp;
    private EventStatus status;

    @Builder
    public Event(EventId id, Name name, Description description, EventStatus status, Period period, CfP cfp) {
        final ValidationResult validation = new ValidationResult();

        if (name == null) {
            validation.addError("name", "name must be defined");
        }
        if (period == null) {
            validation.addError("period", "period must be defined");
        }

        if (cfp != null && period != null) {
            if (cfp.getPeriod().getEndDate().isAfter(period.getStartDate())) {
                validation.addError("cfp", "cfp end date must be before event start date");
            }
        }

        validation.validate();

        this.id = id == null? new EventId(): id;
        this.name = name;
        this.description = description;
        this.status = status == null? EventStatus.CREATED : status;
        this.period = period;
        this.cfp = cfp;
    }


    public void publish() {
        final ValidationResult validation = new ValidationResult();
        if (status == EventStatus.CLOSED) {
            validation.addError("status", "you cannot publish a closed event");
        }
        if (status == EventStatus.PUBLISHED) {
            validation.addError("status", "this event has already been published");
        }

        if (cfp == null) {
            validation.addError("cfp", "this event cannot be published without a CfP");
        }
        validation.validate();
        this.status = EventStatus.PUBLISHED;

    }

}
