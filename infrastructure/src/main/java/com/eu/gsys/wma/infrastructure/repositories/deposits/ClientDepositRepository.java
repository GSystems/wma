package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientDepositRepository extends CrudRepository<IndividualClientDepositEntity, Integer> {
}
