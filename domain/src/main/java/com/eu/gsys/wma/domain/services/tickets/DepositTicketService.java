package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.models.tickets.DepositTicket;
import com.eu.gsys.wma.domain.util.WmaException;

public interface DepositTicketService {

	Iterable<DepositTicket> findAll();

	DepositTicket findById(Long id);

	void save(DepositTicket depositTicket);

	void deleteById(Long id);

	DepositTicket findByTicketNumber(Long ticketNumber);

	void addNew(DepositTicket depositTicket) throws WmaException;

	void removeDepositTicket(DepositTicket depositTicket) throws WmaException;
}
