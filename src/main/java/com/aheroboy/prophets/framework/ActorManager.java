package com.aheroboy.prophets.framework;

import com.aheroboy.prophets.actor.MarketCenterActor;
import com.aheroboy.prophets.actor.StockActor;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by aheroboy on 17-2-11.
 */
@Service
@Data
public class ActorManager implements ActorService {
    private static final Map<String,StockActor> stockActorMap = Maps.newConcurrentMap();

    @Autowired
    private MarketCenterActor mca;

    @Autowired
    private Executor asychExecutor;

    public StockActor addStockActor(StockActor actor){
        return stockActorMap.put(actor.getActorName(),actor);
    }

    public void init(){
       mca.init(MarketCenterActor.GLOBLE_SNAPSHOT_NAME,Boolean.TRUE);
    }

    @Override
    public void start() {
        /**
        asychExecutor.execute(() -> {
           while(true){
               try {
                   stockActorMap.forEach((String key,StockActor stockActor) ->{
                       stockActor.update();
                   });

                   Thread.sleep(3000);
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
        });
         **/
    }
}
