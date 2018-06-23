package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<GenericClientEntity> listAllClients() {
		return (List<GenericClientEntity>) clientRepository.findAll();
	}

	@Override
	public GenericClientEntity getClientById(Integer id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public void saveClient(GenericClientEntity genericClientEntity) {
		clientRepository.save(genericClientEntity);
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
}
