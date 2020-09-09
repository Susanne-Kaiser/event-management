package org.example.conference.events.controller;

import javax.annotation.Resource;

import org.example.conference.events.application.EventApplicationService;
import org.example.conference.events.application.command.NewEventCommand;
import org.example.conference.events.application.representation.EventRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Resource
    private EventApplicationService applicationService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventRepresentation> newEvent(@RequestBody NewEventCommand command) {
        final EventRepresentation event = this.applicationService.newEvent(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);

    }
}
