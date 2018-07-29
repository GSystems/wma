package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public abstract class CompanyDepositRepositoryImpl implements CompanyDepositRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CompanyClientDepositEntity getDepositByClientEntity(CompanyClientEntity clientEntity) {
		TypedQuery<CompanyClientDepositEntity> query = entityManager.createNamedQuery(
				CompanyClientDepositEntity.GET_DEPOSIT_BY_CLIENT, CompanyClientDepositEntity.class);

		query.setParameter(1, clientEntity);

		return query.getSingleResult();
	}
}
