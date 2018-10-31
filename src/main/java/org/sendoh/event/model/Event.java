package org.sendoh.event.model;

import org.sendoh.event.dispatch.EventDispatcher;

import java.util.Objects;

public abstract class Event implements Comparable<Event> {
    public final String payload;
    public final int sequence;

    Event(String payload, int sequence) {
        this.payload = payload;
        this.sequence = sequence;
    }

    public abstract void notified(EventDispatcher dispatcher);

    @Override
    public int compareTo(Event o) {
        return this.sequence - o.sequence;
    }

    @Override
    public String toString() {
        return this.payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return sequence == event.sequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence);
    }
}


