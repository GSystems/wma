package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.util.WmaException;

import java.util.List;

public interface WithdrawTicketService {

	List<WithdrawTicket> findAll();

	WithdrawTicket findById(Long id);

	void save(WithdrawTicket withdrawTicket);

	void totalWithdraw(WithdrawTicket withdrawTicket) throws WmaException;

	void deleteById(Long id);

	void deleteByWithdrawTicket(WithdrawTicket withdrawTicket);
}
