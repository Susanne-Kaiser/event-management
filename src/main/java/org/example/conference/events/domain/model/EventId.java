package org.example.conference.events.domain.model;

import java.util.UUID;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EventId {

    private final UUID id;

    public EventId() {
        this(null);
    }

    public EventId(@Nullable UUID id) {
        this.id = id == null? UUID.randomUUID(): id;
    }
}
