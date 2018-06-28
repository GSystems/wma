package com.eu.gsys.wma.infrastructure.repositories.clients;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyClientRepository extends CrudRepository<CompanyClientEntity, Long> {
}
