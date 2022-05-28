package org.schooling.process.transaction.dao;

import java.io.Serializable;

public interface SchoolingTransactionDAO<T> extends Serializable {

	T save(Object T) throws Exception;

	void saveOrUpdate(Object T) throws Exception;

	void get(Object T) throws Exception;
}
