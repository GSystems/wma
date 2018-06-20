package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Iterable<ClientEntity> listAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public ClientEntity getClientById(Integer id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public ClientEntity saveClient(ClientEntity clientEntity) {
		return clientRepository.save(clientEntity);
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
}
