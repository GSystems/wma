package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.util.WmaException;

public interface DepositTicketService {

	Iterable<DepositTicket> findAll();

	DepositTicket findById(Long id);

	void save(DepositTicket gristTicket);

	void deleteById(Long id);

	DepositTicket findByTicketNumber(Long ticketNumber);

	void addNew(DepositTicket depositTicket) throws WmaException;

	void deleteByDepositTicket(DepositTicket depositTicket) throws WmaException;
}
