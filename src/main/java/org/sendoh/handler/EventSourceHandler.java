package org.sendoh.handler;

import org.sendoh.event.EventProcessor;
import org.sendoh.event.transform.EventTransformer;
import org.sendoh.socket.SocketIO;

import java.util.Optional;

public class EventSourceHandler implements Handler {
    private final EventProcessor processor;
    private final EventTransformer transformer;

    public EventSourceHandler(EventProcessor processor, EventTransformer transformer) {
        this.processor = processor;
        this.transformer = transformer;
    }

    @Override
    public boolean handle(SocketIO socketIOHandler) {
        socketIOHandler.read()
                .map(transformer::transform)
                .filter(Optional::isPresent)
                .forEach(it -> processor.execute(it.get()));
        // It lets the socket open until finish reading
        return false;
    }
}
