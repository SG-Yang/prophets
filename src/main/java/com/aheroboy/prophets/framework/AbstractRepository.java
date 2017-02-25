package com.aheroboy.prophets.framework;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity> extends MongoRepository<E, String> {
	
	E findLatestByBizIdAndVersion(String bizId, Long version);

}
