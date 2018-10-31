package org.sendoh.socket;

import org.sendoh.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Open socket for given port with the given handler
 */
public class ServerSocketUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ServerSocketUtil.class);
    private static boolean running = true;

    private ServerSocketUtil() {
    }

    public static void create(final int port, final Handler handler) {
        try (ServerSocket socket = new ServerSocket(port)) {
            LOG.info("Open Port {}", port);
            while (running) {
                if (!handler.handle(new SocketIO(socket.accept()))) {
                    running = false;
                }
            }
        } catch (IOException e) {
            LOG.error("Error when opening port " + port, e);
        } finally {
            LOG.info("Port {} closed", port);
        }
    }
}
