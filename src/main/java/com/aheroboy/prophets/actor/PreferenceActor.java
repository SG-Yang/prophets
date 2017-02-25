package com.aheroboy.prophets.actor;

import com.aheroboy.prophets.framework.AbstractActor;
import com.aheroboy.prophets.resource.Preference;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sgyang on 2/24/17.
 */
@Data
@Document(collection = "preference")
public class PreferenceActor extends AbstractActor<Preference> {
    @Override public void update() {

    }
}
