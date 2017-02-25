package com.aheroboy.prophets.framework;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.aheroboy.prophets.resource.MarketCategory;
import com.google.common.collect.Maps;

import lombok.Data;
@Data
@Document(collection = "market_center")
public class MarketCenter extends AbstractEntity {
	private Map<String,MarketCategory> categories = Maps.newHashMap();
	
}
