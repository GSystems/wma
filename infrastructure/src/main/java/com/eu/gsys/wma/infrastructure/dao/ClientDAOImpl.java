package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<ClientEntity> listAllClients() {
		return (List<ClientEntity>) clientRepository.findAll();
	}

	@Override
	public ClientEntity getClientById(Integer id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public void saveClient(ClientEntity clientEntity) {
		clientRepository.save(clientEntity);
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
}
