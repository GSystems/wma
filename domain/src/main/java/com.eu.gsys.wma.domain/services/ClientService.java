package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.users.GenericClient;

public interface ClientService {

	Iterable<GenericClient> listAllClients();

	GenericClient getClientById(Integer id);

	void saveClientTicket(GenericClient genericClient);

	void deleteClientTicket(Integer id);
}
