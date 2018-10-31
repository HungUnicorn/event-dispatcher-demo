package org.sendoh.event.dispatch;

import org.sendoh.event.model.*;

public interface EventDispatcher {
    void dispatch(FollowEvent event);

    void dispatch(BroadcastEvent event);

    void dispatch(UnfollowEvent event);

    void dispatch(PrivateMessageEvent event);

    void dispatch(StatusUpdateEvent event);
}
