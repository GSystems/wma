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
	public List<GenericClient> listAllClients() {
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
	public GenericClient getClientById(Integer id) {
		try {
			IndividualClientEntity individualClientEntity = individualClientRepository.findById(id).get();
			GenericClient genericClient = clientTransformer.toModel(individualClientEntity);

			return genericClient;

		} catch (NoSuchElementException e) {
			CompanyClientEntity companyClientEntity = companyClientRepository.findById(id).get();
			GenericClient genericClient = clientTransformer.toModel(companyClientEntity);

			return genericClient;
		}
	}

	@Override
	public void saveClientTicket(Object o) {

		GenericClientEntity genericClientEntity = clientTransformer.fromModel((GenericClient) o);

		if (genericClientEntity instanceof IndividualClientEntity) {
			individualClientRepository.save((IndividualClientEntity) genericClientEntity);
		} else {
			companyClientRepository.save((CompanyClientEntity) genericClientEntity);
		}
	}

	@Override
	public void deleteClientTicket(Integer id) {
		try {
			individualClientRepository.deleteById(id);
		} catch (NoSuchElementException e) {
			companyClientRepository.deleteById(id);
		}
	}
}
