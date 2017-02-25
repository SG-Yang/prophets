package com.aheroboy.prophets.resource.stock;

import org.springframework.web.client.RestTemplate;

import com.aheroboy.prophets.framework.ID;
import com.aheroboy.prophets.resource.Extractor;
import com.aheroboy.prophets.resource.Resource;

public class StockResource<T> implements Resource<T> {
	private String url = "http://hq.sinajs.cn/list={id}";

	public T get(ID id,Extractor<T> extractor) {
		RestTemplate template = new RestTemplate();
		String rawData = template.getForObject(url, String.class, id.getId());
		return extractor.extract(rawData);
	}
}
