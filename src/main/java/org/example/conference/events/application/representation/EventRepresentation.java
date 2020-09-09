package org.example.conference.events.application.representation;

import java.time.OffsetDateTime;

import org.example.conference.events.domain.model.Event;
import org.example.conference.events.domain.model.EventId;
import org.example.conference.events.domain.model.EventStatus;

import lombok.Getter;

@Getter
public class EventRepresentation {

    private EventId id;
    private String name;
    private String description;
    private EventStatus status;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private CfpRepresentation cfp;

    public EventRepresentation(Event event) {
        this.id = event.getId();
        this.name = event.getName().getValue();
        this.description = event.getDescription() != null? event.getDescription().getValue(): null;
        this.status = event.getStatus();
        this.startDate = event.getPeriod().getStartDate();
        this.endDate = event.getPeriod().getEndDate();
        this.cfp = event.getCfp() != null? new CfpRepresentation(event.getCfp()) : null;
    }
}
