package com.eu.gsys.wma.infrastructure.repositories;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<GenericClientEntity, Integer> {
}
