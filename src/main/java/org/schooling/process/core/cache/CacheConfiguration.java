package org.schooling.process.core.cache;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CacheConfiguration implements Serializable {
	
	
	private static final long serialVersionUID = 3311174491910535234L;
	private String cacheName;
	private String cacheObjectType;
	private String cacheLoader;
	
	@JsonProperty(required=false)
	private String kayVariableName;
	
	@JsonProperty(required=false)
	private String keyType;
	
	
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public String getCacheObjectType() {
		return cacheObjectType;
	}
	public void setCacheObjectType(String cacheObjectType) {
		this.cacheObjectType = cacheObjectType;
	}
	public String getCacheLoader() {
		return cacheLoader;
	}
	public void setCacheLoader(String cacheLoader) {
		this.cacheLoader = cacheLoader;
	}
	public String getKayVariableName() {
		return kayVariableName;
	}
	public void setKayVariableName(String kayVariableName) {
		this.kayVariableName = kayVariableName;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	
	
}
