package com.aheroboy.prophets.framework;

import com.aheroboy.prophets.actor.CategoryActor;
import com.aheroboy.prophets.actor.MarketCenterActor;
import com.aheroboy.prophets.actor.StockActor;
import com.aheroboy.prophets.actor.StockEntityRepository;
import com.aheroboy.prophets.resource.MarketCategory;
import com.aheroboy.prophets.resource.stock.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AutoUpdateService implements ActorService {
    @Autowired
    private SnapshotRepository snapshotRep;

    @Autowired
    private StockEntityRepository stockEntityRep;

    @Autowired
    private ActorManager actorManager;

    private List<CategoryActor> leafActors;

    public void init() {
        MarketCenterActor mca = actorManager.getMca();
        leafActors = mca.getLeafActors();
        leafActors.forEach((CategoryActor t) -> {
            Map<String, MarketCategory> leaves = t.getEntity().getLeafCategories();
            leaves.forEach((String bizId, MarketCategory u) -> {

                ActorSnapshot as = snapshotRep.findSnapshotById(bizId);
                StockEntity se = null;
                if (as != null) {
                    se = stockEntityRep.findLatestByBizIdAndVersion(as.getBizId(), as.getVersion());
                }
                StockActor sa = new StockActor(se);
                sa.setActorName(bizId);
                sa.setRoot(true);
                sa.setSnapshotRep(snapshotRep);
                sa.setRep(stockEntityRep);
                actorManager.addStockActor(sa);
            });
        });
    }

    @Override
    public void start() {

    }

}
