package org.example.conference.events.application;

import org.example.conference.events.application.command.NewEventCommand;
import org.example.conference.events.application.representation.EventRepresentation;
import org.example.conference.events.domain.model.CfP;
import org.example.conference.events.domain.model.Description;
import org.example.conference.events.domain.model.Event;
import org.example.conference.events.domain.model.EventId;
import org.example.conference.events.domain.model.Name;
import org.example.conference.events.domain.model.Period;
import org.example.conference.events.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventApplicationService {

    private EventRepository eventRepository;

    public EventApplicationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

	public EventRepresentation newEvent(NewEventCommand command) {
        log.debug("Created new event {}", command);

        final CfP cfp = command.getCfp() == null? null:
            CfP.nullSafeCreate(
                Description.nullSafeCreate(command.getCfp().getDescription()),
                Period.nullSafeCreate(command.getCfp().getStartDate(), command.getCfp().getEndDate()));

        final Event event =
            Event.builder()
                .id(new EventId())
                .name(Name.nullSafeCreate(command.getName()))
                .period(Period.nullSafeCreate(command.getStartDate(), command.getEndDate()))
                .description(Description.nullSafeCreate(command.getDescription()))
                .cfp(cfp)
            .build();

        final Event savedEvent = this.eventRepository.saveEvent(event);

		return new EventRepresentation(savedEvent);
	}

}
