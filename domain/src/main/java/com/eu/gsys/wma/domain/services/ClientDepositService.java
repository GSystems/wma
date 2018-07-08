package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;

public interface ClientDepositService {

	void save(GenericDeposit deposit);
	GenericDeposit findByClient(GenericClient client);

	GenericDeposit findByTicketNumber(Long ticketNumber);
}
