package org.sendoh.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * Wrap socket with buffers to provide read and notify ability
 * */
public class SocketIO {
    private static final Logger LOG = LoggerFactory.getLogger(SocketIO.class);

    private final BufferedReader reader;
    private final BufferedWriter writer;

    SocketIO(Socket socket) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public Stream<String> read() {
        return reader.lines();
    }

    public void write(String payload) {
        try {
            this.writer.write(payload);
            this.writer.write(System.getProperty("line.separator"));
            this.writer.flush();
        } catch (IOException e) {
            LOG.error("Error when writing to BufferWriter", e);
        }
    }
}
