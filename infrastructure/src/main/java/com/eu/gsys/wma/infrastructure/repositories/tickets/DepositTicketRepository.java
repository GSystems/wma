package com.eu.gsys.wma.infrastructure.repositories.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepositTicketRepository extends CrudRepository<DepositTicketEntity, Long> {

	DepositTicketEntity findByTicketNumber(Long ticketNumber);
}
