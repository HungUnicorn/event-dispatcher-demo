package org.sendoh.notifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * StatusUpdate event requires to notify all followers of FROM.
 * Here is the place to store the follower of FROM
 * */

public class InMemoryFollowerNotifier implements FollowerNotifier {
    private final HashMap<String, Set<String>> followers = new HashMap<>();

    @Override
    public void notifyAll(String to, Consumer<String> f) {
        getClients(to).ifPresent(clients -> clients.forEach(f));
    }

    @Override
    public void add(String to, String from) {
        getClients(to).orElseGet(() -> {
            HashSet<String> temp = new HashSet<>();
            followers.put(to, temp);
            return temp;
        }).add(from);
    }

    @Override
    public void remove(String to, String from) {
        getClients(to).ifPresent(f -> f.remove(from));
    }

    private Optional<Set<String>> getClients(String key) {
        return Optional.ofNullable(this.followers.get(key));
    }
}
