package org.sendoh.event.model;

import org.sendoh.event.dispatch.EventDispatcher;

public class StatusUpdateEvent extends Event {
    public final String from;

    public StatusUpdateEvent(String payload, int sequence, String from) {
        super(payload, sequence);
        this.from = from;
    }

    public void notified(EventDispatcher dispatcher) {
        dispatcher.dispatch(this);
    }
}
