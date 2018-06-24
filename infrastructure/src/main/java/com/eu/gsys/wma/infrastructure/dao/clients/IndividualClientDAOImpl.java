package com.eu.gsys.wma.infrastructure.dao.clients;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.clients.IndividualClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("individualClientDAO")
public class IndividualClientDAOImpl implements ClientDAO {

	private final IndividualClientRepository individualClientRepository;

	@Autowired
	public IndividualClientDAOImpl(@Qualifier("individualClientRepository") IndividualClientRepository individualClientRepository) {
		this.individualClientRepository = individualClientRepository;
	}

	@Override
	public List<IndividualClientEntity> listAllClients() {
		return (List<IndividualClientEntity>) individualClientRepository.findAll();
	}

	@Override
	public IndividualClientEntity getClientById(Integer id) {
		return individualClientRepository.findById(id).get();
	}

	@Override
	public void saveClient(Object o) {
		IndividualClientEntity companyClientEntity = (IndividualClientEntity) o;
		individualClientRepository.save(companyClientEntity);
	}

	@Override
	public void deleteClient(Integer id) {
		individualClientRepository.deleteById(id);
	}
}
