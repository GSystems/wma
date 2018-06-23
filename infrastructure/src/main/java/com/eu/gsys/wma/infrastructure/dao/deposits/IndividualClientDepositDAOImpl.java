package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.ClientDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndividualClientDepositDAOImpl implements IndividualClientDepositDAO {

	@Autowired
	private ClientDepositRepository clientDepositRepository;

	@Override
	public List<IndividualClientDepositEntity> listAllClientsDeposits() {
		return (List<IndividualClientDepositEntity>) clientDepositRepository.findAll();
	}

	@Override
	public IndividualClientDepositEntity getClientDepositById(Integer id) {
		return clientDepositRepository.findById(id).get();
	}

	@Override
	public void saveClientDeposit(IndividualClientDepositEntity individualClientDepositEntity) {
		clientDepositRepository.save(individualClientDepositEntity);
	}

	@Override
	public void deleteClientDeposit(Integer id) {
		clientDepositRepository.deleteById(id);
	}

	@Override
	public IndividualClientDepositEntity getClientDepositByClient(GenericClientEntity client) {
//		TypedQuery<IndividualClientDepositEntity> query = getEntityManager().createNamedQuery(
//				IndividualClientDepositEntity.GET_CLIENT_DEPOSIT_BY_CLIENT, IndividualClientDepositEntity.class);

//		query.setParameter(1, genericClient);

		return null;
	}
}
