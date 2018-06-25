package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Qualifier("individualDepositRepository")
@Repository
public abstract class IndividualDepositRepositoryImpl implements IndividualDepositRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public IndividualClientDepositEntity getDepositByClientEntity(IndividualClientEntity clientEntity) {
		TypedQuery<IndividualClientDepositEntity> query = entityManager.createNamedQuery(
				IndividualClientDepositEntity.GET_DEPOSIT_BY_CLIENT, IndividualClientDepositEntity.class);

		query.setParameter(1, clientEntity);

		return query.getSingleResult();
	}
}
