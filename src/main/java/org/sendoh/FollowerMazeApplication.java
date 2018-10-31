package org.sendoh;

import org.sendoh.notifier.UserClientNotifier;
import org.sendoh.event.InOrderEventProcessor;
import org.sendoh.event.transform.EventTransformer;
import org.sendoh.handler.UserClientHandler;
import org.sendoh.notifier.InMemoryClientNotifier;
import org.sendoh.event.dispatch.EventDispatcherImpl;
import org.sendoh.handler.EventSourceHandler;
import org.sendoh.socket.ServerSocketUtil;
import org.sendoh.notifier.InMemoryFollowerNotifier;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Start two servers, one for user client, the other for event source.
 * */
public class FollowerMazeApplication {
    public static void main(String[] args) {
        // Size varies based on the connections
        Executor executor = Executors.newFixedThreadPool(100);

        final UserClientNotifier clientNotifier = new InMemoryClientNotifier();

        CompletableFuture.runAsync(() ->
                ServerSocketUtil.create(9099, new UserClientHandler(clientNotifier)), executor);

        ServerSocketUtil.create(9090,
                new EventSourceHandler(new InOrderEventProcessor(
                        new EventDispatcherImpl(clientNotifier, new InMemoryFollowerNotifier())),
                        new EventTransformer()));
    }
}
