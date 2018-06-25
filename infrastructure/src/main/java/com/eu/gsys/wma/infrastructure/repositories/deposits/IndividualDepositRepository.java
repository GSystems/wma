package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.data.repository.CrudRepository;

public interface IndividualDepositRepository extends CrudRepository<IndividualClientDepositEntity, Integer> {

	IndividualClientDepositEntity getDepositByClientEntity(IndividualClientEntity clientEntity);
}
