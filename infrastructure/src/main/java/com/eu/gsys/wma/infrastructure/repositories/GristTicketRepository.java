package com.eu.gsys.wma.infrastructure.repositories;

import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface GristTicketRepository extends CrudRepository<GristTicketEntity, Integer> {
}
