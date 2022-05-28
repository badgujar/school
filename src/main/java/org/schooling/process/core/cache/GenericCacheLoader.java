package org.schooling.process.core.cache;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.cache.integration.CacheLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.transaction.dao.SchoolingLookupEntityService;


public class GenericCacheLoader implements CacheLoader, Serializable {

	private static final long serialVersionUID = -7057164096785341458L;

	private static final String THIS_CLASS_NAME = GenericCacheLoader.class.getName();

	private Logger log = LogManager.getLogger(GenericCacheLoader.class);

	private CacheConfiguration cacheConfiguration;

	private SchoolingLookupEntityService schoolingLookupEntityService;

	private Class cacheType;

	private transient Field keyField;

	@Override
	public Object load(Object key) {
		String methodName = "oad(Object key)";
		log.info(THIS_CLASS_NAME, methodName, "Start");
		final String load = "load";
		log.info(THIS_CLASS_NAME, load, "load called for Key " + key);
		Object value = null;
		try {
			value = schoolingLookupEntityService.findByClassNameAndId(cacheType, (Serializable) key);
		} catch (Exception exception) {
			log.error(THIS_CLASS_NAME, load, "exception value from DB for : " + this.cacheType.getCanonicalName()
					+ ",ExceptionMsg:" + exception.getMessage());
		} finally {
			log.info(THIS_CLASS_NAME, methodName, "End");
		}
		log.info(THIS_CLASS_NAME, load, "returning value  " + value);

		return value;

	}

	@Override
	public Map loadAll(Iterable keys) {
		try {
			return loadAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<Object, Object> loadAll() throws Exception {
		String methodName = "loadAll()";
		log.info(THIS_CLASS_NAME, methodName, "Start");
		final String loadAll = "loadAll";
		log.info(THIS_CLASS_NAME, loadAll, "load all called for Cache  " + cacheConfiguration.getCacheName());
		Map map = new HashMap();
		try {
			if (this.keyField == null) {
				init();
			}
			List values = schoolingLookupEntityService.findAll(this.cacheType);
			for (Object value : values) {
				Object key;
				try {
					key = keyField.get(value);
					map.put(key, value);
				} catch (IllegalArgumentException e) {
					log.error(THIS_CLASS_NAME, loadAll, "IllegalArgumentException value for chacheType: "
							+ this.cacheType.getCanonicalName() + ",ExceptionMsg:" + e.getMessage());
					throw new Exception("exception,While getting data for CacheType:" + this.cacheType);
				}
			}
		} catch (Exception exception) {
			log.error(THIS_CLASS_NAME, loadAll,
					"Got exception,for : " + this.cacheType + ",ExceptionMsg;" + exception.getMessage());
			throw new Exception("exception,While getting data for CacheType:" + this.cacheType);
		} finally {
			log.info(THIS_CLASS_NAME, methodName, "End");
		}
		log.info(THIS_CLASS_NAME, loadAll, "returning value from DB for : " + this.cacheType.getCanonicalName());

		return map;
	}

	// should be called before calling loading
	public synchronized void init() throws Exception {
		String methodName = "init()";
		log.info(THIS_CLASS_NAME, methodName, "Start");
		String cacheObjectType = this.cacheConfiguration.getCacheObjectType();
		String kayVariableName = this.cacheConfiguration.getKayVariableName();
		try {
			if (this.keyField != null) {
				return;
			}
			cacheType = Class.forName(cacheObjectType);

			this.keyField = cacheType.getDeclaredField(this.cacheConfiguration.getKayVariableName());

			this.keyField.setAccessible(true);
		} catch (ClassNotFoundException e) {
			log.error(THIS_CLASS_NAME, "init", "ClassNotFoundException for chacheType : "
					+ this.cacheType.getCanonicalName() + ",ExceptionMsg:" + e.getMessage());
			throw new Exception("exception,While getting data for CacheType:" + this.cacheType);
		} catch (NoSuchFieldException e) {
			log.error(THIS_CLASS_NAME, "init",
					"NoSuchFieldException,if a field with the specified name isnot found,Searching with Field:"
							+ kayVariableName + ",ExceptionMsg:" + e.getMessage());
			throw new Exception("exception,While getting data for CacheType:" + this.cacheType);
		} catch (SecurityException e) {
			log.error(THIS_CLASS_NAME, "init", "SecurityException,While Searching with Field:" + kayVariableName
					+ ",ExceptionMsg:" + e.getMessage());
			throw new Exception("exception,While getting data for CacheType:" + this.cacheType);
		} finally {
			log.info(THIS_CLASS_NAME, methodName, "End");
		}

	}

	public CacheConfiguration getCacheConfiguration() {
		return cacheConfiguration;
	}

	public void setCacheConfiguration(CacheConfiguration cacheConfiguration) {
		this.cacheConfiguration = cacheConfiguration;
	}

//	public SchoolingLookupEntityService getTbosLookupEntityService() {
//		return schoolingLookupEntityService;
//	}
//
//	public void setTbosLookupEntityService(SchoolingLookupEntityService schoolingLookupEntityService) {
//		this.tbosLookupEntityService = schoolingLookupEntityService;
//	}
}
