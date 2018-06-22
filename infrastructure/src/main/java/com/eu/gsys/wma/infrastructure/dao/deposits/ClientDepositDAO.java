package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.ClientDepositEntity;

import java.util.List;

public interface ClientDepositDAO {

	List<ClientDepositEntity> listAllClientsDeposits();

	ClientDepositEntity getClientDepositById(Integer id);

	void saveClientDeposit(ClientDepositEntity clientDepositEntity);

	void deleteClientDeposit(Integer id);
}
