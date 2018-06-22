package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.ClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.ClientDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDepositDAOImpl implements ClientDepositDAO {

	@Autowired
	private ClientDepositRepository clientDepositRepository;

	@Override
	public List<ClientDepositEntity> listAllClientsDeposits() {
		return (List<ClientDepositEntity>) clientDepositRepository.findAll();
	}

	@Override
	public ClientDepositEntity getClientDepositById(Integer id) {
		return clientDepositRepository.findById(id).get();
	}

	@Override
	public void saveClientDeposit(ClientDepositEntity clientDepositEntity) {
		clientDepositRepository.save(clientDepositEntity);
	}

	@Override
	public void deleteClientDeposit(Integer id) {
		clientDepositRepository.deleteById(id);
	}
}
