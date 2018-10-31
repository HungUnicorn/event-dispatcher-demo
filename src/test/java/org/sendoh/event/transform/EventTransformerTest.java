package org.sendoh.event.transform;

import org.junit.Before;
import org.junit.Test;
import org.sendoh.event.model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EventTransformerTest {
    private EventTransformer transformer;

    @Before
    public void setUp() {
        transformer = new EventTransformer();
    }

    @Test
    public void givenInvalidPayload_notValidEventType() {
        assertFalse(transformer.transform("123").isPresent());
    }

    @Test
    public void givenFollowPayload_followEvent() {
        FollowEvent event = (FollowEvent) transformer.transform("666|F|60|50").orElse(null);
        assertNotNull(event);
        assertEquals(666, event.sequence);
        assertEquals("60", event.from);
        assertEquals("50", event.to);
    }

    @Test
    public void givenUnfollowPayload_unfollowEvent() {
        UnfollowEvent event = (UnfollowEvent) transformer.transform("1|U|12|9").orElse(null);
        assertNotNull(event);
        assertEquals(1, event.sequence);
        assertEquals("12", event.from);
        assertEquals("9", event.to);
    }

    @Test
    public void givenPrivatePayload_privateMessageEvent() {
        PrivateMessageEvent event = (PrivateMessageEvent) transformer.transform("43|P|32|56").orElse(null);
        assertNotNull(event);
        assertEquals(43, event.sequence);
        assertEquals("32", event.from);
        assertEquals("56", event.to);
    }

    @Test
    public void givenStatusUpdatePayload_statusUpdateEvent() {
        StatusUpdateEvent event = (StatusUpdateEvent) transformer.transform("634|S|32").orElse(null);
        assertNotNull(event);
        assertEquals(634, event.sequence);
        assertEquals("32", event.from);
    }

    @Test
    public void givenBroadcastPayload_broadcastEvent() {
        BroadcastEvent event = (BroadcastEvent) transformer.transform("542532|B").orElse(null);
        assertNotNull(event);
        assertEquals(542532, event.sequence);
    }


}
