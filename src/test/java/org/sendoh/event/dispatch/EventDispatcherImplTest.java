package org.sendoh.event.dispatch;

import org.junit.Test;
import org.sendoh.notifier.UserClientNotifier;
import org.sendoh.event.model.*;
import org.sendoh.notifier.FollowerNotifier;

import static org.mockito.Mockito.*;

public class EventDispatcherImplTest {
    private UserClientNotifier clientNotifier = mock(UserClientNotifier.class);
    private FollowerNotifier followerNotifier = mock(FollowerNotifier.class);

    private EventDispatcher dispatcher = new EventDispatcherImpl(clientNotifier, followerNotifier);

    @Test
    public void givenBroadcast_clientNotifierBroadcast() {
        dispatcher.dispatch(new BroadcastEvent("test", 188));
        verify(clientNotifier, times(1)).broadcast("test");
    }

    @Test
    public void givenPrivateEvent_clientNotifierNotify() {
        dispatcher.dispatch(new PrivateMessageEvent("test", 9, "1", "3"));
        verify(clientNotifier, times(1)).notify("3", "test");
    }

    @Test
    public void givenFollowEvent_followerAddAndClientNotifierNotify() {
        dispatcher.dispatch(new FollowEvent("test", 1, "3", "2"));
        verify(clientNotifier, times(1)).notify("2", "test");
        verify(followerNotifier, times(1)).add("2", "3");
    }

    @Test
    public void givenUnfollowEvent_followerNotifierRemoveAndClientNotifierNotify() {
        dispatcher.dispatch(new UnfollowEvent("test", 1, "3", "2"));
        verify(followerNotifier, times(1)).remove("2", "3");
        verify(clientNotifier, never()).notify(any(), any());
    }


    @Test
    public void givenStatusUpdateEvent_followerNotifierNotifyAll() {
        dispatcher.dispatch(new StatusUpdateEvent("test", 1, "3"));
        verify(followerNotifier, times(1)).notifyAll(any(), any());
    }
}