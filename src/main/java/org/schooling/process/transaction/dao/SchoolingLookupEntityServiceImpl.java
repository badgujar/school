package org.schooling.process.transaction.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolingLookupEntityServiceImpl<T> implements SchoolingLookupEntityService<T> {

	@Autowired
	private transient HibernateTemplate hibernateTemplate;

	@Override
	public List<T> findAll(Class T) throws Exception {
		return hibernateTemplate.loadAll(T);
	}

	@Override
	public Object findByClassNameAndId(Class T, Serializable id) throws Exception {
		return hibernateTemplate.get(T, id);
	}

}
