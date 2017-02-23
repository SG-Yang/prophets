package com.aheroboy.prophets.api;

import java.util.List;
import java.util.Map;

import com.aheroboy.prophets.resource.stock.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aheroboy.prophets.framework.ActorSnapshot;
import com.aheroboy.prophets.framework.ActorSystem;
import com.aheroboy.prophets.framework.MarketCenter;
import com.aheroboy.prophets.framework.SnapshotRepository;
import com.aheroboy.prophets.resource.MarketCategory;
import com.aheroboy.prophets.resource.MarketCenterRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping("/as")
public class ActorSystemApi {

	@Autowired
	private MarketCenterRepository mcRepository;
	@Autowired
	private SnapshotRepository snapshotRep;

	@Autowired
	private ActorSystem actorSystem;
	
	@RequestMapping(value = "/actor/count", method = RequestMethod.GET)
	public @ResponseBody Long getActorCount() {
		return 1000L;
	}
	@RequestMapping(value = "/stocks/{typeId}", method = RequestMethod.GET)
	public @ResponseBody List<StockEntity> listAll(@PathVariable String typeId) {
		List<StockEntity> stocks = Lists.newArrayList();
		StockEntity stockEntity = new StockEntity();
		stockEntity.setSymbol("abc");
		stockEntity.setBizId(stockEntity.getSymbol());
		stockEntity.setCode(stockEntity.getSymbol());
		stockEntity.setAmount(100L);
		stocks.add(stockEntity);

		stockEntity = new StockEntity();
		stockEntity.setSymbol("def");
		stockEntity.setAmount(200L);
		stockEntity.setBizId(stockEntity.getSymbol());
		stockEntity.setCode(stockEntity.getSymbol());
		stocks.add(stockEntity);
		System.out.println("hello......");
		return stocks;
	}

	@RequestMapping(value = "/actor/marketcenter/init", method = RequestMethod.GET)
	public @ResponseBody MarketCenter init() {
		MarketCenter mc = new MarketCenter();
		mc.setBizId("MARKET_CENTER");
		mc.setName("行情中心");
		Map<String, MarketCategory> categories = Maps.newHashMap();
		MarketCategory hsgs = new MarketCategory();
		hsgs.setBizId("hsgs");
		hsgs.setName("沪深股市");
		hsgs.setCountryCode("cn");
		categories.put("hsgs", hsgs);
		List<MarketCategory> subCategories = Lists.newArrayList();
		hsgs.setSubCategories(subCategories);
		
		MarketCategory sinahy = new MarketCategory();
		sinahy.setCode("sinahy");
		sinahy.setBizId(sinahy.getCode());
		sinahy.setCountryCode("cn");
		sinahy.setName("新浪行业");

		subCategories.add(sinahy);

		List<MarketCategory> sinahySubs = Lists.newArrayList();
		sinahy.setSubCategories(sinahySubs);

		MarketCategory new_blhy = new MarketCategory();
		new_blhy.setLeaf(Boolean.TRUE);
		new_blhy.setCode("new_blhy");
		new_blhy.setBizId(new_blhy.getCode());
		new_blhy.setName("玻璃行业");
		Map<String,MarketCategory> sm1 = Maps.newHashMap();
		MarketCategory stock = new MarketCategory();
		stock.setBizId("sh600176");
		stock.setCode("600176");
		stock.setName("中国巨石");
		sm1.put(stock.getBizId(), stock);

		stock = new MarketCategory();
		stock.setBizId("sh600184");
		stock.setCode("600184");
		stock.setName("光电股份");
		sm1.put(stock.getBizId(), stock);

		stock = new MarketCategory();
		stock.setBizId("sh600293");
		stock.setCode("600293");
		stock.setName("三峡新材");
		sm1.put(stock.getBizId(), stock);
		new_blhy.setLeafCategories(sm1);
		
		sinahySubs.add(new_blhy);
		MarketCategory new_cmyl = new MarketCategory();
		new_cmyl.setCode("new_cmyl");
		new_cmyl.setBizId(new_cmyl.getCode());
		new_cmyl.setName("传媒娱乐");
		sinahySubs.add(new_cmyl);


		/**
		MarketCategory cyb_root = new MarketCategory();
		categories.put("cyb_root", cyb_root);
		**/
		mc.setCategories(categories);

		mc = mcRepository.save(mc);
		ActorSnapshot snapshot = new ActorSnapshot();

		snapshot.setId(mc.getBizId());
		snapshot.setVersion(mc.getVersion());
		snapshotRep.save(snapshot);

		return mc;
	}
}
