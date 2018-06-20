package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;

public interface ClientDAO {

	Iterable<ClientEntity> listAllClients();
	ClientEntity getClientById(Integer id);
	ClientEntity saveClient(ClientEntity clientEntity);
	void deleteClient(Integer id);
}
