package org.sendoh.event.model;

public abstract class CompleteEvent extends Event {
    public final String to;
    public final String from;

    CompleteEvent(String payload, int sequence, String from, String to) {
        super(payload, sequence);
        this.to = to;
        this.from = from;
    }
}
