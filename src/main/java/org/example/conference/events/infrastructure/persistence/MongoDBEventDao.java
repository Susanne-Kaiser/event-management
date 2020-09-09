package org.example.conference.events.infrastructure.persistence;

import org.example.conference.events.domain.model.EventId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBEventDao extends MongoRepository<EventDocument, EventId>{

}

