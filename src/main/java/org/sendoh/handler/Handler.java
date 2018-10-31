package org.sendoh.handler;

import org.sendoh.socket.SocketIO;

@FunctionalInterface
public interface Handler {
    boolean handle(SocketIO handler);
}
