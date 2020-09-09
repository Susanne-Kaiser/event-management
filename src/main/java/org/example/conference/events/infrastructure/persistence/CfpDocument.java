package org.example.conference.events.infrastructure.persistence;

import java.time.OffsetDateTime;

import org.example.conference.events.domain.model.CfP;

import lombok.Data;

@Data
public class CfpDocument {
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    protected CfpDocument() { }

    public CfpDocument(CfP cfp) {
        this.description = cfp.getDescription() != null? cfp.getDescription().getValue() : null;
        this.startDate = cfp.getPeriod().getStartDate();
        this.endDate = cfp.getPeriod().getEndDate();
    }
}
