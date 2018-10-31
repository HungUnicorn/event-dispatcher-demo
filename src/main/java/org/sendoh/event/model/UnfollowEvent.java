package org.sendoh.event.model;

import org.sendoh.event.dispatch.EventDispatcher;

public class UnfollowEvent extends CompleteEvent {
    public UnfollowEvent(String payload, int sequence, String from, String to) {
        super(payload, sequence, from, to);
    }

    public void notified(EventDispatcher dispatcher) {
        dispatcher.dispatch(this);
    }
}
