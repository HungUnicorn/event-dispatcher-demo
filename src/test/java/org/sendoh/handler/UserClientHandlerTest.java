package org.sendoh.handler;

import org.junit.Test;
import org.sendoh.notifier.UserClientNotifier;
import org.sendoh.socket.SocketIO;

import java.util.stream.Stream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserClientHandlerTest {
    private UserClientNotifier userClientNotifier = mock(UserClientNotifier.class);
    private UserClientHandler handler = new UserClientHandler(userClientNotifier);
    private SocketIO io = mock(SocketIO.class);

    @Test
    public void handle_givenId_waitForNotified() {
        when(io.read()).thenReturn(Stream.of("123"));

        handler.handle(io);

        verify(userClientNotifier, times(1)).waitForNotified(any(), any());
    }

    @Test
    public void handle_givenNoId_waitForNotifiedNotCalled() {
        when(io.read()).thenReturn(Stream.of(""));

        handler.handle(io);

        verify(userClientNotifier, never()).waitForNotified(any(), any());
    }
}
