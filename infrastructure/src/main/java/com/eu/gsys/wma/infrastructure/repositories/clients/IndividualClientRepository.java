package com.eu.gsys.wma.infrastructure.repositories.clients;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

@Qualifier("individualClientRepository")
public interface IndividualClientRepository extends CrudRepository<IndividualClientEntity, Integer> {
}
