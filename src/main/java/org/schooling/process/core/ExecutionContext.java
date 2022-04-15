package org.schooling.process.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ExecutionContext {

	private CacheProvider cacheProvider;

	private Map<String, Map> localCache = new ConcurrentHashMap<>();
	private Map<String, Object> localCacheMap = new ConcurrentHashMap<>();

	public ExecutionContext() {

	}

	public ExecutionContext(CacheProvider cacheProvider) {
		super();
		this.cacheProvider = cacheProvider;
	}

	public Object get(String cacheName, Object key) throws Exception {
		Map cacheMap = localCache.get(cacheName);
		if (cacheMap == null) {
			cacheMap = new ConcurrentHashMap();
			localCache.put(cacheName, cacheMap);
		}
		Object value = cacheMap.get(key);
		if (value == null) {
			if (this.cacheProvider != null) {
				Object obj = this.cacheProvider.getCacheData(cacheName, key);
				if (obj != null) {
					value = obj;
					cacheMap.put(key, value);
				}
			}
		}
		return value;
	}

	public Object get(String cacheName) {
		return localCacheMap.get(cacheName);
	}

	public void put(String cacheName, Object key, Object value) {
		Map cacheMap = localCache.get(cacheName);
		if (cacheMap == null) {
			cacheMap = new ConcurrentHashMap();
			localCache.put(cacheName, cacheMap);
		}
		cacheMap.put(key, value);

	}

	public void put(String cacheName, Object value) {
		localCacheMap.put(cacheName, value);
	}

	public void clear() {
		localCacheMap = new ConcurrentHashMap<>();
	}

	public CacheProvider getCacheProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(CacheProvider cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

}
