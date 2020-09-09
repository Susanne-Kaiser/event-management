package org.example.conference.events.infrastructure.persistence;

import java.time.OffsetDateTime;

import org.example.conference.events.domain.model.Event;
import org.example.conference.events.domain.model.EventId;
import org.example.conference.events.domain.model.EventStatus;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class EventDocument {

    @Id
    private EventId id;
    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private EventStatus status;
    private CfpDocument cfp;

    protected EventDocument() {}

    public EventDocument(Event event) {
        this.id = event.getId();
        this.name = event.getName().getValue();
        this.description = event.getDescription() != null? event.getDescription().getValue() : null;
        this.startDate = event.getPeriod().getStartDate();
        this.endDate = event.getPeriod().getEndDate();
        this.cfp = event.getCfp() != null? new CfpDocument(event.getCfp()): null;
        this.status = event.getStatus();
    }


}
