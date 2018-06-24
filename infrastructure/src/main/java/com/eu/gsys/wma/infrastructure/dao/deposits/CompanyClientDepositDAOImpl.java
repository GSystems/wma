package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CompanyDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyClientDepositDAOImpl implements CompanyClientDepositDAO {

	private final CompanyDepositRepository companyDepositRepository;

	@Autowired
	public CompanyClientDepositDAOImpl(CompanyDepositRepository companyDepositRepository) {
		this.companyDepositRepository = companyDepositRepository;
	}

	@Override
	public List<CompanyClientDepositEntity> listAllCompanyDeposits() {
		return (List<CompanyClientDepositEntity>) companyDepositRepository.findAll();
	}

	@Override
	public CompanyClientDepositEntity getCompanyDepositById(Integer id) {
		return companyDepositRepository.findById(id).get();
	}

	@Override
	public void saveDeposit(CompanyClientDepositEntity companyClientDepositEntity) {
		companyDepositRepository.save(companyClientDepositEntity);
	}

	@Override
	public void deleteCompanyDeposit(Integer id) {
		companyDepositRepository.deleteById(id);
	}

	@Override
	public CompanyClientDepositEntity getDepositByClient(GenericClientEntity genericClientEntity) {
		return companyDepositRepository.getDepositByCompanyClientEntity((CompanyClientEntity) genericClientEntity);
	}
}
