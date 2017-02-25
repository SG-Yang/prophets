package com.aheroboy.prophets.framework;

import java.util.Map;

import com.aheroboy.prophets.actor.ActorPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
public abstract class AbstractActor<E extends AbstractEntity> implements Actor {
    private static final Logger logger = LoggerFactory.getLogger(AbstractActor.class);

    private String actorName;
    private boolean isRoot;
    private Map<String, ? extends Actor> subActors = Maps.newHashMap();

    @Autowired
    @Transient
    protected SnapshotRepository snapshotRep;

    protected E entity;

    public Actor getActorsByPath(ActorPath subPath) {
        Actor actor = subActors.get(subPath.getPathName());
        return (actor != null && subPath.hasNext()) ? actor.getActorsByPath(subPath.getNext()) : actor;
    }

    public AbstractActor(E entity) {
        this.entity = entity;
    }

    public void init(String actorName, boolean isRoot) {
        this.actorName = actorName;
        this.isRoot = isRoot;
        if (isRoot && snapshotRep != null && getRepository() != null) {
            ActorSnapshot snapshot = snapshotRep.findSnapshotById(actorName);
            if (snapshot != null) {
                entity = getRepository().findLatestByBizIdAndVersion(snapshot.getId(), snapshot.getVersion());
            }
        }
        logger.info(String.format("Initializing actor bizId:%s,bizName:%s", actorName, entity == null ? "" : entity.getName()));

    }

    public AbstractRepository<E> getRepository() {
        return null;
    }
}
