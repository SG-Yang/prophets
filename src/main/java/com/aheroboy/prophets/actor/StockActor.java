package com.aheroboy.prophets.actor;

import com.aheroboy.prophets.framework.AbstractActor;
import com.aheroboy.prophets.framework.AbstractRepository;
import com.aheroboy.prophets.framework.ActorSnapshot;
import com.aheroboy.prophets.framework.SnapshotRepository;
import com.aheroboy.prophets.resource.stock.StockEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

@Data
@Slf4j
public class StockActor extends AbstractActor<StockEntity> {
    private String url = "http://hq.sinajs.cn/list={stockId}";
    private static final RestTemplate restTemplate = new RestTemplate();
    private AbstractRepository<StockEntity> rep;
    private ObjectMapper objectMapper;
    private SnapshotRepository snapshotRepository;

    public StockActor(StockEntity entity) {
        super(entity);
    }

    public void update() {
        try {
            String stockJson = restTemplate.getForObject(url, String.class, getActorName());
            if (StringUtils.isNotBlank(stockJson) && !"null".equals(stockJson)) {
                StockEntity stockEntity = objectMapper.readValue(stockJson, new TypeReference<StockEntity>() {
                });
                if (!stockEntity.equals(entity)) {
                    stockEntity.setVersion(entity == null ? 1 : entity.getVersion() + 1);
                    rep.save(stockEntity);
                    ActorSnapshot snapshot = new ActorSnapshot();
                    snapshot.setBizId(stockEntity.getBizId());
                    snapshot.setVersion(stockEntity.getVersion());
                    snapshotRepository.save(snapshot);
                    entity = stockEntity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AbstractRepository<StockEntity> getRepository() {
        return rep;
    }

}
