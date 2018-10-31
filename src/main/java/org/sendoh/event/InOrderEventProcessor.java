package org.sendoh.event;

import org.sendoh.event.dispatch.EventDispatcher;
import org.sendoh.event.model.Event;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Process events in order, using expectedSequence starting from 1
 */

public class InOrderEventProcessor implements EventProcessor {
    private final Queue<Event> queue = new PriorityQueue<>();
    private final EventDispatcher dispatcher;
    private int expectedSequence = 1;

    public InOrderEventProcessor(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public int execute(Event event) {
        queue.add(event);
        while (true) {
            if (!processInSequence()) break;
        }
        return queue.size();
    }

    private boolean processInSequence() {
        final Event head = queue.peek();

        if ((head != null) && (head.sequence == this.expectedSequence)) {
            expectedSequence++;
            Event event = queue.poll();
            event.notified(this.dispatcher);
            return true;
        }
        return false;
    }
}
