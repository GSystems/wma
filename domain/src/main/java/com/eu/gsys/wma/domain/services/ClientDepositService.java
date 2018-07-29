package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.models.clients.GenericClient;
import com.eu.gsys.wma.domain.models.deposits.GenericDeposit;

public interface ClientDepositService {

	void save(GenericDeposit deposit);
	GenericDeposit findByClient(GenericClient client);

	GenericDeposit findByTicketNumber(Long ticketNumber);
}
