# follower-maze

Receive events and distribute them to connected user clients
based on the information in the events

## How to start
Import the project and run `FollowerMazeApplication`


## Explanation
`Handler` controls the socket connections, and the general data flow is as follows:

```
clients <-> UserClientNotifier
                ^
                |
           FollowerNotifier
                | 
events  ->  EventProcessor
```
`UserClientNotifier`: control the communication between connected clients and 
the information about follow-relationship, called by `FollowerNotifier`

`EventProcessor`: process events into event types and store
the relationships in FollowerNotifier

`FollowerNotifier`: the follow-relationship is stored, and 
call `UserClientNotifier` when necessary 

## Further improvement
A more robust solution would be to use Akka-stream to replace the above data flow, 
it would provide better code structure in a functional programming style,
error-prone for concurrency.
