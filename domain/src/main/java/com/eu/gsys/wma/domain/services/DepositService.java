package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;

public interface DepositService {

	GenericDeposit findByClient(GenericClient client);
}
