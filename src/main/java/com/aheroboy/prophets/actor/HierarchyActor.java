package com.aheroboy.prophets.actor;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.aheroboy.prophets.framework.AbstractActor;
import com.aheroboy.prophets.framework.AbstractRepository;
import com.aheroboy.prophets.framework.Actor;
import com.aheroboy.prophets.framework.MarketCenter;
import com.aheroboy.prophets.resource.MarketCategory;
import com.aheroboy.prophets.resource.MarketCenterRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.data.annotation.Transient;

public class HierarchyActor extends AbstractActor<MarketCenter> {
    public static final String GLOBLE_SNAPSHOT_NAME = "MARKET_CENTER";

    public HierarchyActor() {
        super(null);
    }

    private Map<String, CategoryActor> categories = Maps.newHashMap();

    @Autowired
    @Transient
    private MarketCenterRepository repository;

    public void init(String actorName, boolean isRoot) {
        super.init(actorName, isRoot);
        if (entity != null) {
            entity.getCategories().forEach((String category, MarketCategory u) -> {

                CategoryActor cActor = createActor(u);
                categories.put(category, cActor);
                if (CollectionUtils.isNotEmpty(u.getSubCategories())) {
                    Map<String, CategoryActor> subActors = createSubActor(u);
                    cActor.setSubActors(subActors);
                }
            });
        }
    }

    private CategoryActor createActor(MarketCategory u) {
        CategoryActor cActor = new CategoryActor(u);
        cActor.init(u.getBizId(), false);
        return cActor;
    }

    private Map<String, CategoryActor> createSubActor(MarketCategory u) {
        Map<String, CategoryActor> actors = Maps.newHashMap();
        List<MarketCategory> mcs = u.getSubCategories();
        if (CollectionUtils.isNotEmpty(mcs)) {
            mcs.forEach((MarketCategory sub) -> {
                CategoryActor cActor = createActor(sub);
                cActor.setLeaf(sub.isLeaf());
                actors.put(cActor.getActorName(), cActor);
                if (CollectionUtils.isNotEmpty(sub.getSubCategories())) {
                    if (!sub.isLeaf()) {
                        Map<String, CategoryActor> subActors = createSubActor(sub);
                        cActor.setSubActors(subActors);
                    }
                }

            });
        }
        return actors;
    }

    @Override
    public AbstractRepository<MarketCenter> getRepository() {
        return repository;
    }

    private List<CategoryActor> drillDownTree(CategoryActor actor) {
        List<CategoryActor> leaves = Lists.newArrayList();
        if (MapUtils.isNotEmpty(actor.getSubActors())) {
            actor.getSubActors().forEach((String actorName, Actor subActor) -> {
                CategoryActor ca = (CategoryActor) subActor;
                if (ca.isLeaf()) {
                    leaves.add(ca);
                } else {
                    leaves.addAll(drillDownTree(ca));
                }
            });
        }

        return leaves;
    }

    public List<CategoryActor> getLeafActors() {
        List<CategoryActor> leaves = Lists.newArrayList();
        categories.forEach((String t, CategoryActor u) -> {
            if (!u.isLeaf()) {
                leaves.addAll(drillDownTree(u));
            } else {
                leaves.add(u);
            }

        });
        return leaves;
    }

    public void update(){}
}
