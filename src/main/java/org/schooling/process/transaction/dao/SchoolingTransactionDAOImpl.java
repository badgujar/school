package org.schooling.process.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolingTransactionDAOImpl<T> implements SchoolingTransactionDAO<T> {

	@Autowired
	private transient HibernateTemplate hibernateTemplate;

	@Override
	public T save(Object T) throws Exception {
		return (T) hibernateTemplate.save(T);
	}

	@Override
	public void saveOrUpdate(Object T) throws Exception {
		hibernateTemplate.saveOrUpdate(T);
	}

	@Override
	public void get(Object T) throws Exception {
//		hibernateTemplate.

	}

}
