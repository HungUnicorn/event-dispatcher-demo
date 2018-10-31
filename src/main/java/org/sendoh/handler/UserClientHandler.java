package org.sendoh.handler;

import org.sendoh.notifier.UserClientNotifier;
import org.sendoh.socket.SocketIO;

import java.util.Optional;

/**
 * As soon as the connection is accepted, this handler receives the server the ID of
 * the represented user sent, so that the server knows which events to
 * inform them of. For example, once connected a *user client* may send down:
 * `2932\n`, indicating that they are representing user 2932.
 */

public class UserClientHandler implements Handler {
    private final UserClientNotifier notifier;

    public UserClientHandler(UserClientNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean handle(SocketIO handler) {
        Optional<String> id = handler.read().findFirst();
        if (id.isPresent() && !id.get().isEmpty()) {
            notifier.waitForNotified(id.get(), handler);
        }
        // It lets the socket opened until interrupted
        return true;
    }
}
