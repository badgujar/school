package org.schooling.process.transaction.dao;

import java.io.Serializable;
import java.util.List;

public interface SchoolingLookupEntityService<T> extends Serializable {

	List<T> findAll(Class T) throws Exception;

	Object findByClassNameAndId(Class T, Serializable id) throws Exception;
}
