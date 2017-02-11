package com.aheroboy.prophets.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.aheroboy.prophets.resource.stock.SinaStockResource;
import com.aheroboy.prophets.resource.stock.StockEntity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.fge.jackson.JacksonUtils;
import com.github.fge.jackson.JsonLoader;
import com.google.common.collect.Lists;
import com.mongodb.util.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import com.aheroboy.prophets.actor.CategoryActor;
import com.aheroboy.prophets.actor.MarketCenterActor;
import com.aheroboy.prophets.actor.StockActor;
import com.aheroboy.prophets.actor.StockEntityRepository;
import com.aheroboy.prophets.resource.MarketCategory;
import org.springframework.web.client.RestTemplate;

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
    private StockEntityRepository stockEntityRepository;
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
                        if (!leaves.containsKey(se.getCode())) {
                            StockActor stockActor = new StockActor(se);
                            stockActor.setRep(stockEntityRepository);
                            stockActor.setSnapshotRep(snapshotRep);
                            stockActor.init(se.getCode(), Boolean.TRUE);
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
