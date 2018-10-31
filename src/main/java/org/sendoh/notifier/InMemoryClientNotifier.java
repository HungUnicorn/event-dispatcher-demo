package org.sendoh.notifier;

import org.sendoh.socket.SocketIO;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Handle the communication between client and event
 * */
public class InMemoryClientNotifier implements UserClientNotifier {
    private final ConcurrentHashMap<String, SocketIO> clients;

    public InMemoryClientNotifier() {
        clients = new ConcurrentHashMap<>();
    }

    @Override
    public void waitForNotified(String clientId, SocketIO handler) {
        this.clients.put(clientId, handler);
    }

    @Override
    public void broadcast(String payload) {
        this.clients.values().forEach(handler -> handler.write(payload));
    }

    @Override
    public void notify(String clientId, String payload) {
        SocketIO handler = this.clients.get(clientId);
        if (handler != null) {
            handler.write(payload);
        }
    }
}
