package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyDepositEntity;

import java.util.List;

public interface CompanyDepositDAO {

	List<CompanyDepositEntity> listAllCompanyDeposits();

	CompanyDepositEntity getCompanyDepositById(Integer id);

	void saveCompanyDeposit(CompanyDepositEntity companyDepositEntity);

	void deleteCompanyDeposit(Integer id);
}
