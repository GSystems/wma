package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;

import java.util.List;

public interface CompanyClientDepositDAO {

	List<CompanyClientDepositEntity> listAllCompanyDeposits();

	CompanyClientDepositEntity getCompanyDepositById(Integer id);

	void saveCompanyDeposit(CompanyClientDepositEntity companyClientDepositEntity);

	void deleteCompanyDeposit(Integer id);
}
