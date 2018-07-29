package com.eu.gsys.wma.domain.services.clients;

import com.eu.gsys.wma.domain.models.clients.GenericClient;

import java.util.List;

public interface ClientService {

	List<GenericClient> findAll();

	GenericClient findById(Long id);

	void save(GenericClient genericClient);

	void deleteById(Long id);
}
