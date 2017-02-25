package com.aheroboy.prophets.actor;

import com.aheroboy.prophets.framework.AbstractActor;
import com.aheroboy.prophets.resource.ServiceProvider;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sgyang on 2/24/17.
 */
@Data
@Document(collection = "service")
public class ServiceActor extends AbstractActor<ServiceProvider> {
    @Override public void update() {

    }
}
