package com.aheroboy.prophets.framework;

import com.aheroboy.prophets.actor.CategoryActor;
import com.aheroboy.prophets.actor.MarketCenterActor;
import com.aheroboy.prophets.actor.StockActor;
import com.aheroboy.prophets.actor.StockEntityRepository;
import com.aheroboy.prophets.resource.MarketCategory;
import com.aheroboy.prophets.resource.stock.SinaStockResource;
import com.aheroboy.prophets.resource.stock.StockEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Service
public class AutoDiscoveryService implements ActorService {

    @Autowired
    private StockEntityRepository seRep;
    @Autowired
    private SnapshotRepository snapshotRep;
    @Autowired
    private SinaStockResource resource;
    @Autowired
    private ActorManager actorManager;
    @Autowired
    private Executor asyncExecutor;
    @Autowired
    private ObjectMapper mapper;

    private List<CategoryActor> leafActors;

    @Override
    public void start() {
        asyncExecutor.execute(() -> {
            while (true) {
                try {
                    discoveryStock();
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void discoveryStock() {
        MarketCenterActor mca = actorManager.getMca();
        leafActors = mca.getLeafActors();
        leafActors.forEach((CategoryActor t) -> {
            try {
                Map<String, MarketCategory> leaves = t.getEntity().getLeafCategories();
                String data;
                int pageSize = 80;
                int startIdx = 1;
                int endIdx = pageSize;
                while ((data = resource.get(startIdx, endIdx, t.getActorName())) != null && !"null".equals(data)) {
                    List<StockEntity> stockEntries = mapper.readValue(data,
                            new TypeReference<List<StockEntity>>() {
                            });
                    stockEntries.forEach((StockEntity se) -> {
                        if (!leaves.containsKey(se.getSymbol())) {
                            StockActor stockActor = new StockActor(se);
                            stockActor.setRep(seRep);
                            stockActor.setSnapshotRep(snapshotRep);
                            //String name = Character.isLetter(se.getCode().charAt(0)) ?
                            stockActor.init(se.getSymbol(), Boolean.TRUE);
                            stockActor.setObjectMapper(mapper);
                            actorManager.addStockActor(stockActor);
                        }
                    });
                    startIdx++;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void init() {
        discoveryStock();
    }

}
