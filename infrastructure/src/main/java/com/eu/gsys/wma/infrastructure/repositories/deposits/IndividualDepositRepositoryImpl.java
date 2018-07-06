package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity.GET_DEPOSIT_BY_CLIENT;
import static com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity.GET_DEPOSIT_BY_TICKET_NUMBER;

@Repository
public abstract class IndividualDepositRepositoryImpl implements IndividualDepositRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public IndividualClientDepositEntity findDepositByClient(IndividualClientEntity clientEntity) {
		TypedQuery<IndividualClientDepositEntity> query = entityManager.createNamedQuery(
				GET_DEPOSIT_BY_CLIENT, IndividualClientDepositEntity.class);

		query.setParameter(1, clientEntity);

		return query.getSingleResult();
	}

	@Override
	public IndividualClientDepositEntity findDepositByTicketNumber(Long ticketNumber) {
		TypedQuery<IndividualClientDepositEntity> query = entityManager.createNamedQuery(
				GET_DEPOSIT_BY_TICKET_NUMBER, IndividualClientDepositEntity.class
		);

		query.setParameter(1, ticketNumber);

		return query.getSingleResult();
	}
}
