package org.schooling.process.core;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.cache.Cache;

import org.schooling.process.core.cache.CacheConfiguration;

public interface CacheProvider {
	String CACHE_TPADATA = "CACHE_TESTDATA";

	enum CACHE_CONFIGURATION {
		PARTITIONED, REPLICATED, LOCAL
	}

	Object getCacheData(String cacheName, Object key) throws Exception;

	void putCacheData(String cacheName, Object key, Object value) throws Exception;

	List<Object> getAllCacheData(String cacheName) throws Exception;

	void removeCacheData(String cacheName, Object key) throws Exception;

	Object serializeIfBinary(Object binObj, Class OfType) throws Exception;

	default Cache getCache(String cacheName) throws Exception {
		return null;
	}

	default Cache createCache(String cacheName) throws Exception {
		throw new Exception("Can not create Cache. Not implemented ");
	}

	default Set getOrCreateCachedSet(String setName) throws Exception {
		throw new Exception("Can not create Set. Not implemented ");
	}

	default int getCacheSize(String cacheName) throws Exception {
		throw new Exception("Can not create Set. Not implemented ");
	}

	Cache getOrCreateCache(String cacheName, CacheProvider.CACHE_CONFIGURATION config) throws Exception;

	void destroyCache(String cacheName) throws Exception;

	void init() throws Exception, Exception;

	void createCache(CacheConfiguration configuration) throws  Exception;

	/* MultiMap */
	boolean putCacheDataInMap(Object key, Object value) throws Exception;

	Object getCacheDataFromMap(Object key) throws Exception;

	void removeCacheDataFromMap(Object key) throws Exception;

	boolean isKeyContains(Object key) throws Exception;

	boolean isCacheExists(String cacheName) throws Exception;

	Set<Entry<Object, Object>> getKeySetFromMultiMap() throws Exception;

}
