package com.aheroboy.prophets.resource;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.aheroboy.prophets.framework.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_EMPTY)
@Document(collection = "market_catetory")
public class MarketCategory extends AbstractEntity {
	private String code;
	private String countryCode;
//	@Transient
	private List<MarketCategory> subCategories = Lists.newArrayList();
	private Map<String,MarketCategory> leafCategories = Maps.newHashMap();
}
