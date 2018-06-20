package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;

import java.util.List;

public interface ClientDAO {

	List<ClientEntity> listAllClients();

	ClientEntity getClientById(Integer id);

	ClientEntity saveClient(ClientEntity clientEntity);

	void deleteClient(Integer id);
}
