package org.sendoh.event.model;

import org.sendoh.event.dispatch.EventDispatcher;

public class BroadcastEvent extends Event {
    public BroadcastEvent(String payload, int sequence) {
        super(payload, sequence);
    }

    public void notified(EventDispatcher dispatcher) {
        dispatcher.dispatch(this);
    }
}
