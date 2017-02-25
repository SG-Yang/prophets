package com.aheroboy.prophets.framework;

import java.util.List;

/**
 * Created by sgyang on 2/24/17.
 */
public interface APIService {
    List<AbstractEntity> getActors(String typeId);
}
