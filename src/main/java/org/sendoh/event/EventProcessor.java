package org.sendoh.event;

import org.sendoh.event.model.Event;

public interface EventProcessor {
    int execute(Event event);
}
