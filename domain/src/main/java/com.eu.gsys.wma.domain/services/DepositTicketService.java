package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.DepositTicket;

public interface DepositTicketService {

	Iterable<DepositTicket> listAllDepositTickets();

	DepositTicket getDepositTicketById(Integer id);

	void saveDepositTicket(DepositTicket gristTicket);

	void deleteDepositTicket(Integer id);
}
