package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDepositRepository extends CrudRepository<CompanyClientDepositEntity, Integer> {

	CompanyClientDepositEntity getDepositByClientEntity(CompanyClientEntity companyClientEntity);
}
