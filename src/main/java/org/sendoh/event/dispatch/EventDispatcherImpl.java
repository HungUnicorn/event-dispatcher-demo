package org.sendoh.event.dispatch;

import org.sendoh.notifier.UserClientNotifier;
import org.sendoh.notifier.FollowerNotifier;
import org.sendoh.event.model.*;

/**
 * Given events, handle to and remove from the follower followerNotifier and call client clientNotifier for actions dispatch necessary
 */
public class EventDispatcherImpl implements EventDispatcher {
    private final FollowerNotifier followerNotifier;
    private final UserClientNotifier clientNotifier;

    public EventDispatcherImpl(UserClientNotifier clientNotifier, FollowerNotifier followerNotifier) {
        this.clientNotifier = clientNotifier;
        this.followerNotifier = followerNotifier;
    }

    @Override
    public void dispatch(FollowEvent event) {
        followerNotifier.add(event.to, event.from);
        clientNotifier.notify(event.to, event.payload);
    }

    @Override
    public void dispatch(BroadcastEvent event) {
        clientNotifier.broadcast(event.payload);
    }

    @Override
    public void dispatch(UnfollowEvent event) {
        followerNotifier.remove(event.to, event.from);
    }

    @Override
    public void dispatch(PrivateMessageEvent event) {
        clientNotifier.notify(event.to, event.payload);
    }

    @Override
    public void dispatch(StatusUpdateEvent event) {
        followerNotifier.notifyAll(event.from, clientId ->
                clientNotifier.notify(clientId, event.payload)
        );
    }
}
