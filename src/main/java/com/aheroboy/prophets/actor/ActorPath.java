package com.aheroboy.prophets.actor;

import lombok.Data;

/**
 * Created by sgyang on 2/24/17.
 */
@Data
public class ActorPath {
    private String pathName;
    private ActorPath next;
    public boolean hasNext(){
        return next != null;
    }
}
