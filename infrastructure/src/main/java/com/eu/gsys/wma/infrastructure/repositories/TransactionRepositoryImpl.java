package com.eu.gsys.wma.infrastructure.repositories;

import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public abstract class TransactionRepositoryImpl implements TransactionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public TransactionEntity getMostRecentTransaction() {
		TypedQuery<TransactionEntity> query = entityManager.createNamedQuery(
				TransactionEntity.GET_MOST_RECENT_TRANSACTION, TransactionEntity.class
		);

		return query.getSingleResult();
	}
}
