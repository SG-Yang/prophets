package com.aheroboy.prophets.framework;

public interface SnapshotRepository extends AbstractRepository<ActorSnapshot> {
	ActorSnapshot findSnapshotById(String snapshotName);
}
