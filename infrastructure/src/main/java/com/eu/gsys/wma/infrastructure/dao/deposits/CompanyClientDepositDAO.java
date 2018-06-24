package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;

import java.util.List;

public interface CompanyClientDepositDAO {

	List<CompanyClientDepositEntity> listAllCompanyDeposits();

	CompanyClientDepositEntity getCompanyDepositById(Integer id);

	void saveDeposit(CompanyClientDepositEntity companyClientDepositEntity);

	void deleteCompanyDeposit(Integer id);

	CompanyClientDepositEntity getDepositByClient(GenericClientEntity genericClientEntity);
}
