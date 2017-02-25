package com.aheroboy.prophets.framework;

import com.aheroboy.prophets.actor.CategoryActor;
import com.aheroboy.prophets.actor.HierarchyActor;
import com.aheroboy.prophets.actor.StockActor;
import com.aheroboy.prophets.resource.MarketCategory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by aheroboy on 17-2-11.
 */
@Service
@Data
public class ActorManager implements ActorService,APIService {
    private static final Map<String, StockActor> stockActorMap = Maps.newConcurrentMap();

    @Autowired
    private HierarchyActor mca;

    @Autowired
    private Executor asychExecutor;

    public StockActor addStockActor(StockActor actor) {
        return stockActorMap.put(actor.getActorName(), actor);
    }

    public void init() {
        mca.init(HierarchyActor.GLOBLE_SNAPSHOT_NAME, Boolean.TRUE);

    }

    @Override public List<AbstractEntity> getActors(String typeId) {
        List<AbstractEntity> entities = Lists.newArrayList();
        List<CategoryActor> leaves = mca.getLeafActors();
        leaves.forEach((CategoryActor t) -> {
            Map<String, MarketCategory> stockRefs = t.getEntity().getLeafCategories();
            stockRefs.forEach((String bizId, MarketCategory u) -> {
                StockActor stockActor = stockActorMap.get(bizId);
                if (stockActor != null) {
                    entities.add(stockActor.getEntity());
                }
            });
        });
        return entities;
    }

    @Override
    public void start() {
        asychExecutor.execute(() -> {
            while (true) {
                try {
                    stockActorMap.forEach((String key, StockActor stockActor) -> {
                        stockActor.update();
                    });

                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
