package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;

import java.util.List;

public interface IndividualClientDepositDAO {

	List<IndividualClientDepositEntity> listAllClientsDeposits();

	IndividualClientDepositEntity getClientDepositById(Integer id);

	void saveClientDeposit(IndividualClientDepositEntity individualClientDepositEntity);

	void deleteClientDeposit(Integer id);

	IndividualClientDepositEntity getClientDepositByClient(GenericClientEntity genericClientEntity);
}
