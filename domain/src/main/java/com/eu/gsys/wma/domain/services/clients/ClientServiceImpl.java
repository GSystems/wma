package com.eu.gsys.wma.domain.services.clients;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.repositories.clients.CompanyClientRepository;
import com.eu.gsys.wma.infrastructure.repositories.clients.IndividualClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

	private final CompanyClientRepository companyClientRepository;
	private final ClientTransformer clientTransformer;
	private final IndividualClientRepository individualClientRepository;

	@Autowired
	public ClientServiceImpl(ClientTransformer clientTransformer,
			CompanyClientRepository companyClientRepository, IndividualClientRepository individualClientRepository) {

		this.companyClientRepository = companyClientRepository;
		this.clientTransformer = clientTransformer;
		this.individualClientRepository = individualClientRepository;
	}

	@Override
	public List<GenericClient> findAll() {
		List<GenericClient> clients = new ArrayList<>();
		List<CompanyClientEntity> companyClientEntities = (List<CompanyClientEntity>) companyClientRepository.findAll();

		for (CompanyClientEntity companyClientEntity : companyClientEntities) {
			clients.add(clientTransformer.toModel(companyClientEntity));
		}

		List<IndividualClientEntity> individualClientEntities =
				(List<IndividualClientEntity>) individualClientRepository.findAll();

		for (IndividualClientEntity individualClientEntity : individualClientEntities) {
			clients.add(clientTransformer.toModel(individualClientEntity));
		}

		return clients;
	}

	@Override
	public GenericClient findById(Long id) {

		GenericClient client;

		if (individualClientRepository.findById(id).isPresent()) {
			IndividualClientEntity individualClientEntity = individualClientRepository.findById(id).get();
			client = clientTransformer.toModel(individualClientEntity);
		} else if (companyClientRepository.findById(id).isPresent()) {
			CompanyClientEntity companyClientEntity = companyClientRepository.findById(id).get();
			client = clientTransformer.toModel(companyClientEntity);
		} else {
			// TODO return o proper message
			return null;
		}

		return client;
	}

	@Override
	public void save(GenericClient client) {

		GenericClientEntity companyClientEntity = clientTransformer.fromModel(client);

		if (companyClientEntity instanceof IndividualClientEntity) {
			individualClientRepository.save((IndividualClientEntity) companyClientEntity);
		} else {
			companyClientRepository.save((CompanyClientEntity) companyClientEntity);
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			individualClientRepository.deleteById(id);
		} catch (NoSuchElementException e) {
			companyClientRepository.deleteById(id);
		}
	}
}
