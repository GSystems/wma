package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.dao.BaseDAO;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.ClientDepositEntity;

import java.util.List;

public interface ClientDepositDAO extends BaseDAO<ClientDepositEntity, Long> {

	List<ClientDepositEntity> listAllClientsDeposits();

	ClientDepositEntity getClientDepositById(Integer id);

	void saveClientDeposit(ClientDepositEntity clientDepositEntity);

	void deleteClientDeposit(Integer id);

	ClientDepositEntity getClientDepositByClient(ClientEntity clientEntity);
}
