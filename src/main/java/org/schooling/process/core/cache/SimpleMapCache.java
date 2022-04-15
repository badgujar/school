package org.schooling.process.core.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;

public class SimpleMapCache implements Cache,Serializable {

	
	private static final long serialVersionUID = 8270887041274176451L;

	Map store = new HashMap();

	private CacheLoader cacheloader;

	public CacheLoader getCacheloader() {
		return cacheloader;
	}

	public void setCacheloader(CacheLoader cacheloader) {
		this.cacheloader = cacheloader;
	}

	public SimpleMapCache() {
	}

	@Override
	public void clear() {
		store.clear();

	}

	@Override
	public void close() {
		store.clear();

	}

	@Override
	public boolean containsKey(Object key) {
		return store.containsKey(key);
	}

	@Override
	public void deregisterCacheEntryListener(CacheEntryListenerConfiguration arg0) {

	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		MyEntry entry = (MyEntry) store.get(key);
		if (entry != null)
			obj = entry.getValue();
		if (obj == null && this.cacheloader != null) {
			obj = this.cacheloader.load(key);
			if (obj != null) {
				this.put(key, obj);
			}
		}
		return obj;
	}

	@Override
	public Map getAll(Set arg0) {
		return null;
	}

	@Override
	public Object getAndPut(Object arg0, Object arg1) {
		return null;
	}

	@Override
	public Object getAndRemove(Object arg0) {
		return null;
	}

	@Override
	public Object getAndReplace(Object arg0, Object arg1) {
		return null;
	}

	@Override
	public CacheManager getCacheManager() {
		return null;
	}

	@Override
	public Configuration getConfiguration(Class arg0) {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Object invoke(Object arg0, EntryProcessor arg1, Object... arg2) throws EntryProcessorException {
		return null;
	}

	@Override
	public Map invokeAll(Set arg0, EntryProcessor arg1, Object... arg2) {
		return null;
	}

	@Override
	public boolean isClosed() {
		return false;
	}

	@Override
	public Iterator iterator() {
		return this.store.values().iterator();
	}

	@Override
	public void loadAll(Set arg0, boolean arg1, CompletionListener arg2) {

	}

	@Override
	public void put(Object key, Object value) {
		MyEntry entry = new MyEntry(key, value);
		this.store.put(key, entry);

	}

	@Override
	public void putAll(Map map) {
		Set keys = map.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			Object key = iterator.next();
			Object value = map.get(key);
			this.put(key, value);

		}

	}

	@Override
	public boolean putIfAbsent(Object arg0, Object arg1) {
		return false;
	}

	@Override
	public void registerCacheEntryListener(CacheEntryListenerConfiguration arg0) {

	}

	@Override
	public boolean remove(Object key) {
		Object o = this.store.remove(key);
		if (o != null)
			return true;
		else {
			return false;
		}

	}

	@Override
	public boolean remove(Object arg0, Object arg1) {
		return false;
	}

	@Override
	public void removeAll() {
		this.store.clear();

	}

	@Override
	public void removeAll(Set arg0) {

	}

	@Override
	public boolean replace(Object arg0, Object arg1) {
		return false;
	}

	@Override
	public boolean replace(Object arg0, Object arg1, Object arg2) {
		return false;
	}

	class MyEntry implements Cache.Entry,Serializable {
		
		private static final long serialVersionUID = 3605693109936500110L;
		Object key = null;
		Object val = null;

		MyEntry(Object key, Object val) {
			this.key = key;
			this.val = val;
		}

		@Override
		public Object getKey() {
			return key;
		}

		@Override
		public Object getValue() {
			return val;
		}

		@Override
		public Object unwrap(Class arg0) {
			return null;
		}
	}

	@Override
	public Object unwrap(Class arg0) {
		return null;
	}
}
