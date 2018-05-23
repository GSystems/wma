package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;

public interface DepositTicketDAO {

	Iterable<DepositTicketEntity> listAllDepositTickets();
	DepositTicketEntity getDepositTicketsById(Integer id);
	DepositTicketEntity saveDepositTicket(DepositTicketEntity gristTicket);
	void deleteDepositTicket(Integer id);
}
