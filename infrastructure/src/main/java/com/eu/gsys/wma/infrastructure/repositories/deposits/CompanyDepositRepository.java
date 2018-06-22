package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyDepositEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDepositRepository extends CrudRepository<CompanyDepositEntity, Integer> {
}
