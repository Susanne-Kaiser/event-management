package org.example.conference.events.application.representation;

import java.time.OffsetDateTime;

import org.example.conference.events.domain.model.CfP;

import lombok.Data;
import lombok.Getter;

@Getter
public class CfpRepresentation {
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public CfpRepresentation(CfP cfp) {
        this.description = cfp.getDescription() != null? cfp.getDescription().getValue() : null;
        this.startDate = cfp.getPeriod().getStartDate();
        this.endDate = cfp.getPeriod().getEndDate();
    }

}
