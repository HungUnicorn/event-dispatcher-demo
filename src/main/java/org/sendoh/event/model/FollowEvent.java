package org.sendoh.event.model;

import org.sendoh.event.dispatch.EventDispatcher;

public class FollowEvent extends CompleteEvent {
    public FollowEvent(String payload, int sequence, String from, String to) {
        super(payload, sequence, from, to);
    }

    public void notified(EventDispatcher dispatcher) {
        dispatcher.dispatch(this);
    }
}
