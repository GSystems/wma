package com.eu.gsys.wma.infrastructure.repositories.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public abstract class DepositTicketRepositoryImpl implements DepositTicketRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DepositTicketEntity findByTicketNumber(Long ticketNumber) {
		TypedQuery<DepositTicketEntity> query = entityManager.createNamedQuery(
				DepositTicketEntity.GET_TICKET_BY_TICKET_NUMBER, DepositTicketEntity.class
		);

		query.setParameter(1, ticketNumber);

		return query.getSingleResult();
	}
}
