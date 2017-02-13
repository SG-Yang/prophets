package com.aheroboy.prophets.framework;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

import lombok.Data;
@Data
public abstract class AbstractActor<E extends AbstractEntity> implements Actor {
	private static final Logger logger = LoggerFactory.getLogger(AbstractActor.class);

	private String actorName;
	private boolean isRoot;
	private Map<String,? extends Actor> subActors = Maps.newHashMap();
	
	@Autowired
	protected SnapshotRepository snapshotRep;

	protected E entity;
	
	public AbstractActor(E entity){
		this.entity = entity;
	}

	public void init(String actorName,boolean isRoot) {
		this.actorName = actorName;
		this.isRoot = isRoot;
		if (isRoot && snapshotRep != null && getRepository() != null) {
			ActorSnapshot snapshot = snapshotRep.findSnapshotById(actorName);
			if(snapshot != null){
				entity = getRepository().findLatestByBizIdAndVersion(snapshot.getId(), snapshot.getVersion());
			}
		}
		logger.info(String.format("Initializing actor bizId:%s,bizName:%s",actorName,entity == null ? "" : entity.getName()));

	}

	public AbstractRepository<E> getRepository(){
		return null;
	}
}
