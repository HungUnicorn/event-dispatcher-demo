package org.sendoh.event;

import org.junit.Test;
import org.mockito.InOrder;
import org.sendoh.event.dispatch.EventDispatcher;
import org.sendoh.event.model.BroadcastEvent;

import static org.mockito.Mockito.*;

public class InOrderEventProcessorTest {
    private EventDispatcher dispatcher = mock(EventDispatcher.class);
    private EventProcessor processor = new InOrderEventProcessor(dispatcher);

    @Test
    public void givenCorrectOrder_dispatch() {
        BroadcastEvent event = new BroadcastEvent("test", 1);

        processor.execute(event);

        verify(dispatcher, times(1)).dispatch(event);
    }

    @Test
    public void giveWrongOrder_doNotDispatch() {
        BroadcastEvent event = new BroadcastEvent("test", 3);

        processor.execute(event);

        verify(dispatcher, never()).dispatch(event);
    }

    @Test
    public void givenOutOfOrder_processInOrder() {
        BroadcastEvent event2 = new BroadcastEvent("test", 2);
        BroadcastEvent event1 = new BroadcastEvent("test", 1);

        processor.execute(event2);
        processor.execute(event1);

        InOrder inOrder = inOrder(dispatcher);
        inOrder.verify(dispatcher, times(1)).dispatch(event1);
        inOrder.verify(dispatcher, times(1)).dispatch(event2);
    }
}
