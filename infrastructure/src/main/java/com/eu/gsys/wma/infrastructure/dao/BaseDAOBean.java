package com.eu.gsys.wma.infrastructure.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDAOBean <T, K extends Serializable> implements BaseDAO<T, K> {

	@Autowired
	private EntityManagerAccessor emAccessor;

	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		entityManager = emAccessor.getEntityManager();
	}

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDAOBean() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public T insert(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public void delete(K id) {
		entityManager.remove(entityManager.getReference(type, id));
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@Override
	public T findById(K id) {
		return entityManager.find(type, id);
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery("from " + type.getSimpleName(), type);
		return query.getResultList();
	}

	@Override
	public void flush() {
		entityManager.flush();
	}
}
