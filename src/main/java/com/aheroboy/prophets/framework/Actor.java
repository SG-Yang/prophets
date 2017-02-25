package com.aheroboy.prophets.framework;

import com.aheroboy.prophets.actor.ActorPath;

public interface Actor {
    Actor getActorsByPath(ActorPath path);
    void update();

}
