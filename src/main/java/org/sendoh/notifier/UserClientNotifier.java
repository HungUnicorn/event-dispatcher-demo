package org.sendoh.notifier;

import org.sendoh.socket.SocketIO;

public interface UserClientNotifier {
    void waitForNotified(String clientId, SocketIO handler);

    void broadcast(String payload);

    void notify(String clientId, String payload);
}
