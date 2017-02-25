package com.aheroboy.prophets.resource;
import com.aheroboy.prophets.framework.ID;

public interface Resource <T>{
	T get(ID id, Extractor<T> extractor);
}
