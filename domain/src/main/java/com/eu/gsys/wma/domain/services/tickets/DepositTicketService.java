package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.util.WmaException;

public interface DepositTicketService {

	Iterable<DepositTicket> listAllDepositTickets();

	DepositTicket getDepositTicketById(Integer id);

	void saveDepositTicket(DepositTicket gristTicket);

	void deleteDepositTicket(DepositTicket depositTicket) throws WmaException;
}
