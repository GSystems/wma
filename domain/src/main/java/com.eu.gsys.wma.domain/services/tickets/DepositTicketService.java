package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;

public interface DepositTicketService {

	Iterable<DepositTicket> listAllDepositTickets();

	DepositTicket getDepositTicketById(Integer id);

	void saveDepositTicket(DepositTicket gristTicket);

	void deleteDepositTicket(Integer id);
}
