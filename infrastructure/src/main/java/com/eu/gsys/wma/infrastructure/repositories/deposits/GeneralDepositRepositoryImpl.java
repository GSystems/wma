package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public abstract class GeneralDepositRepositoryImpl implements GeneralDepositRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public GeneralDepositEntity getMostRecentRecord() {
		TypedQuery<GeneralDepositEntity> query = entityManager.createNamedQuery(
				GeneralDepositEntity.GET_MOST_RECENT_RECORD, GeneralDepositEntity.class
		);

		return query.getSingleResult();
	}
}
