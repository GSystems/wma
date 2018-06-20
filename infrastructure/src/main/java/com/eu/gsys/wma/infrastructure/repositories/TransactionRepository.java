package com.eu.gsys.wma.infrastructure.repositories;

import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
