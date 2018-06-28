package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.util.WmaException;

public interface DepositTicketService {

	Iterable<DepositTicket> listAll();

	DepositTicket findById(Long id);

	void save(DepositTicket gristTicket);

	void deleteById(Long id);

	void deleteByDepositTicket(DepositTicket depositTicket) throws WmaException;

	DepositTicket findByTicketNumber(Long ticketNumber);

	void addNew(DepositTicket depositTicket) throws WmaException;

	void withdrawWithDepositTicket(DepositTicket depositTicket);
}
