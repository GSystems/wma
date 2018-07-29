package com.eu.gsys.wma.infrastructure.repositories.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.WithdrawTicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawTicketRepository extends CrudRepository<WithdrawTicketEntity, Long> {
}
