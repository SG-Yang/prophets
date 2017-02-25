package com.aheroboy.prophets.resource;

public interface Extractor<T> {

	T extract(String rawData);
}
