package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;

import java.util.List;

public interface ClientDAO {

	List<GenericClientEntity> listAllClients();

	GenericClientEntity getClientById(Integer id);

	void saveClient(GenericClientEntity genericClientEntity);

	void deleteClient(Integer id);
}
