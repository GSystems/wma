package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Client;

public interface ClientService {

	Iterable<Client> listAllClients();

	Client getClientById(Integer id);

	void saveClientTicket(Client client);

	void deleteClientTicket(Integer id);
}
