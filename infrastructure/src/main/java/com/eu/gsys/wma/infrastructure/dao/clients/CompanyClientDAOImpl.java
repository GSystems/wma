package com.eu.gsys.wma.infrastructure.dao.clients;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.clients.CompanyClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("companyClientDAO")
public class CompanyClientDAOImpl implements ClientDAO {

	private final CompanyClientRepository companyClientRepository;

	@Autowired

	public CompanyClientDAOImpl(CompanyClientRepository companyClientRepository) {
		this.companyClientRepository = companyClientRepository;
	}

	@Override
	public List<CompanyClientEntity> listAllClients() {
		return (List<CompanyClientEntity>) companyClientRepository.findAll();
	}

	@Override
	public CompanyClientEntity getClientById(Integer id) {
		return companyClientRepository.findById(id).get();
	}

	@Override
	public void saveClient(Object o) {
		CompanyClientEntity companyClientEntity = (CompanyClientEntity) o;
		companyClientRepository.save(companyClientEntity);
	}

	@Override
	public void deleteClient(Integer id) {
		companyClientRepository.deleteById(id);
	}
}
