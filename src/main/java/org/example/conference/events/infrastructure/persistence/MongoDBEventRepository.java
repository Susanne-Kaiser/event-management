package org.example.conference.events.infrastructure.persistence;

import org.example.conference.events.domain.model.CfP;
import org.example.conference.events.domain.model.Description;
import org.example.conference.events.domain.model.Event;
import org.example.conference.events.domain.model.Name;
import org.example.conference.events.domain.model.Period;
import org.example.conference.events.domain.repository.EventRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDBEventRepository implements EventRepository {

    private final MongoDBEventDao dao;

    public MongoDBEventRepository(MongoDBEventDao dao) {
        this.dao = dao;
    }


    @Override
    public Event saveEvent(Event eventToSave) {
        final EventDocument eventDoc = new EventDocument(eventToSave);
        final EventDocument savedEventDoc = this.dao.save(eventDoc);

        final Event event = toEvent(savedEventDoc);

        return event;
    }

    private Event toEvent(final EventDocument savedEventDoc) {
        final CfP cfp = savedEventDoc.getCfp() == null ? null
                : CfP.builder()
                        .description(Description.nullSafeCreate(savedEventDoc.getCfp().getDescription()))
                        .period(Period.nullSafeCreate(savedEventDoc.getCfp().getStartDate(),savedEventDoc.getCfp().getEndDate()))
                        .build();

        final Event event = Event.builder()
                .id(savedEventDoc.getId())
                .name(Name.nullSafeCreate(savedEventDoc.getName()))
                .description(Description.nullSafeCreate(savedEventDoc.getDescription()))
                .period(Period.nullSafeCreate(savedEventDoc.getStartDate(), savedEventDoc.getEndDate()))
                .status(savedEventDoc.getStatus())
                .cfp(cfp)
                .build();
        return event;
    }

}
