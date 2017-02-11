package com.aheroboy.prophets.framework;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "snapshot")
@EqualsAndHashCode(callSuper=true)
public class ActorSnapshot extends AbstractEntity{
}
