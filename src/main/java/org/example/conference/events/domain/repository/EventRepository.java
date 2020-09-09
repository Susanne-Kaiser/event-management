package org.example.conference.events.domain.repository;

import org.example.conference.events.domain.model.Event;

public interface EventRepository {

    Event saveEvent(Event event);

}
