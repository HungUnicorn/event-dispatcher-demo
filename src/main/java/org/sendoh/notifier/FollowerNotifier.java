package org.sendoh.notifier;

import java.util.function.Consumer;

public interface FollowerNotifier {
    void notifyAll(String to, Consumer<String> f);

    void add(String to, String from);

    void remove(String to, String from);
}
