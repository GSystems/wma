package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.IndividualDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndividualClientDepositDAOImpl implements IndividualClientDepositDAO {

	private final IndividualDepositRepository customIndividualClientRepository;

	@Autowired
	public IndividualClientDepositDAOImpl(
			@Qualifier("individualDepositRepository") IndividualDepositRepository customIndividualClientRepository) {
		this.customIndividualClientRepository = customIndividualClientRepository;
	}

	@Override
	public List<IndividualClientDepositEntity> listAllClientsDeposits() {
		return (List<IndividualClientDepositEntity>) customIndividualClientRepository.findAll();
	}

	@Override
	public IndividualClientDepositEntity getClientDepositById(Integer id) {
		return customIndividualClientRepository.findById(id).get();
	}

	@Override
	public void saveDeposit(IndividualClientDepositEntity individualClientDepositEntity) {
		customIndividualClientRepository.save(individualClientDepositEntity);
	}

	@Override
	public void deleteClientDeposit(Integer id) {
		customIndividualClientRepository.deleteById(id);
	}

	@Override
	public IndividualClientDepositEntity getDepositByClient(GenericClientEntity genericClientEntity) {
		return customIndividualClientRepository.getDepositByIndividualClientEntity((IndividualClientEntity) genericClientEntity);
	}
}
