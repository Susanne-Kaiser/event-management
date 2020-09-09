package org.example.conference.events.application.command;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class NewEventCommand {

    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private CfPCommand cfp;

}
