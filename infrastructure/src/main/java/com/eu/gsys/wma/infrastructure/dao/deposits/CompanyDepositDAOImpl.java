package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CompanyDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDepositDAOImpl implements CompanyDepositDAO {

	@Autowired
	private CompanyDepositRepository companyDepositRepository;

	@Override
	public List<CompanyDepositEntity> listAllCompanyDeposits() {
		return (List<CompanyDepositEntity>) companyDepositRepository.findAll();
	}

	@Override
	public CompanyDepositEntity getCompanyDepositById(Integer id) {
		return companyDepositRepository.findById(id).get();
	}

	@Override
	public void saveCompanyDeposit(CompanyDepositEntity companyDepositEntity) {
		companyDepositRepository.save(companyDepositEntity);
	}

	@Override
	public void deleteCompanyDeposit(Integer id) {
		companyDepositRepository.deleteById(id);
	}
}
