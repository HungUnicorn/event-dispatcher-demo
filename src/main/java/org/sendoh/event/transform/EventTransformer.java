package org.sendoh.event.transform;

import org.sendoh.event.model.*;

import java.util.Optional;

/**
 * Transform events into different event types by parsing the payload
 *
 * example:
 *
 *     | Payload    | Sequence #| Type         | From User Id | To User Id |
 *     |------------|-----------|--------------|--------------|------------|
 *     |666|F|60|50 | 666       | Follow       | 60           | 50         |
 *     |1|U|12|9    | 1         | Unfollow     | 12           | 9          |
 *     |542532|B    | 542532    | Broadcast    | -            | -          |
 *     |43|P|32|56  | 43        | Private Msg  | 2            | 56         |
 *     |634|S|32    | 634       | Status Update| 32           | -          |
 *
 * */

public class EventTransformer {
    public Optional<Event> transform(String payload) {
        final String[] fields = payload.split("\\|");

        if (!isValid(fields)) return Optional.empty();

        String from = getString(fields, 2);
        String to = getString(fields, 3);

        final char type = fields[1].charAt(0);
        final int sequence = Integer.parseInt(fields[0]);

        switch (type) {
            case 'B':
                return Optional.of(new BroadcastEvent(payload, sequence));

            case 'S':
                return Optional.of(new StatusUpdateEvent(payload, sequence, from));

            case 'F':
                return Optional.of(new FollowEvent(payload, sequence, from, to));

            case 'U':
                return Optional.of(new UnfollowEvent(payload, sequence, from, to));

            case 'P':
                return Optional.of(new PrivateMessageEvent(payload, sequence, from, to));

            default:
                return Optional.empty();
        }
    }

    private String getString(String[] fields, int i) {
        String result = null;
        if (fields.length > i) {
            result = fields[i];
        }
        return result;
    }

    private boolean isValid(String[] fields) {
        return fields.length >= 2;
    }
}
