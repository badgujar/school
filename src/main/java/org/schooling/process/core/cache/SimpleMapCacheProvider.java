package org.schooling.process.core.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.cache.Cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.CacheProvider;
import org.springframework.stereotype.Component;

@Component
public class SimpleMapCacheProvider implements CacheProvider {

	private Logger log = LogManager.getLogger(SimpleMapCacheProvider.class);
	
	Map<String, Set> sets = new ConcurrentHashMap<>();

	// for unit testing and local testing
	Map<String, Cache> localCaches = new ConcurrentHashMap<String, Cache>();

	@Override
	public Object getCacheData(String cacheName, Object key) {
		Object value = null;
		Cache cache = this.localCaches.get(cacheName);
		if (cache != null)
			value = cache.get(key);
		return value;
	}

	@Override
	public void putCacheData(String cacheName, Object key, Object value) {
		Cache cache = this.localCaches.get(cacheName);
		if (cache == null) {
			cache = createCache(cacheName);
		}
		cache.put(key, value);

	}

	public Object serializeIfBinary(Object binObj, Class OfType)  {
		return binObj;
	}

	@Override
	public List<Object> getAllCacheData(String cacheName) {
		List<Object> values = new ArrayList<Object>();
		Cache cache = this.localCaches.get(cacheName);
		if (cache != null) {
			Iterator<Cache.Entry> iterator = cache.iterator();
			while (iterator.hasNext()) {
				values.add(iterator.next().getValue());
			}
		}
		return values;

	}

	@Override
	public void removeCacheData(String cacheName, Object key) {
		Cache cache = this.localCaches.get(cacheName);
		if (cache != null) {
			cache.remove(key);
		}

	}

	@Override
	public Cache createCache(String cacheName) {
		Cache cache = localCaches.get(cacheName);
		if (cache == null) {
			cache = new SimpleMapCache();
			localCaches.put(cacheName, cache);
		}

		return cache;
	}

	public Cache getOrCreateCache(String cacheName, CacheProvider.CACHE_CONFIGURATION config) {
		return createCache(cacheName);
	}

	@Override
	public Cache getCache(String cacheName) {
		return localCaches.get(cacheName);
	}

	@Override
	public Set getOrCreateCachedSet(String setName) {
		Set set = this.sets.get(setName);
		if (set == null) {
			set = new HashSet();
			sets.put(setName, set);

		}
		return set;
	}

	@Override
	public int getCacheSize(String cacheName) {
		Cache cache = this.createCache(cacheName);
		int i = 0;
		Iterator iterator = cache.iterator();
		while (iterator.hasNext()) {
			i++;
			iterator.next();
		}
		return i;
	}

	@Override
	public void destroyCache(String cacheName) {
		this.localCaches.remove(cacheName);

	}

	@Override
	public void init()  {
		
	}

	public void createCache(CacheConfiguration cacheConfiguration)  {
//		SimpleMapCache cache = new SimpleMapCache();
//		try {
//			if (cacheConfiguration.getCacheLoader() != null) {
//				Class cacheloaderClass = Class.forName(cacheConfiguration.getCacheLoader());
//				if (cacheloaderClass != null) {
//					GenericCacheLoader cacheLoader = (GenericCacheLoader) cacheloaderClass.newInstance();
//					cacheLoader.setCacheConfiguration(cacheConfiguration);
//					ITbosLookupEntityService service = (ITbosLookupEntityService) SpringContextUtil
//							.getApplicationContext().getBean("tbosLookupEntityServiceImpl");
//					cacheLoader.setTbosLookupEntityService(service);
//					cacheLoader.init();
//					cache.setCacheloader(cacheLoader);
//
//					Map map = cacheLoader.loadAll();
//					cache.putAll(map);
//					this.localCaches.put(cacheConfiguration.getCacheName(), cache);
////					LogUtil.info("SimplemapCacheProvider", "createCache",
////							"Cache Created for Cache " + cacheConfiguration.getCacheName());
//				}
//			}
//		} catch (Exception e) {
////			LogUtil.error("SimplemapCacheProvider", "createCache",
////					"Exception in creating Cache " + cacheConfiguration.getCacheName(), e);
//		}
	}

	@Override
	public boolean putCacheDataInMap(Object key, Object value) {
		return false;
	}

	@Override
	public Object getCacheDataFromMap(Object key) {
		return null;
	}

	@Override
	public void removeCacheDataFromMap(Object key) {

	}

	@Override
	public boolean isKeyContains(Object key) {
		return false;
	}

	@Override
	public boolean isCacheExists(String cacheName) {
		return false;
	}

	@Override
	public Set<Entry<Object, Object>> getKeySetFromMultiMap() {
		return null;
	}

}