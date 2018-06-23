package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.users.GenericClient;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.infrastructure.dao.ClientDAO;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientTransformer clientTransformer;
	private final ClientDAO clientDAO;

	@Autowired
	public ClientServiceImpl(ClientTransformer clientTransformer, ClientDAO clientDAO) {
		this.clientTransformer = clientTransformer;
		this.clientDAO = clientDAO;
	}

	@Override
	public Iterable<GenericClient> listAllClients() {
		List<GenericClient> genericClientList = new ArrayList<>();
		List<GenericClientEntity> clientEntities = (List<GenericClientEntity>) clientDAO.listAllClients();

		for (GenericClientEntity genericClientEntity : clientEntities) {
			genericClientList.add(clientTransformer.toModel(genericClientEntity));
		}

		return genericClientList;
	}

	@Override
	public GenericClient getClientById(Integer id) {
		return clientTransformer.toModel(clientDAO.getClientById(id));
	}

	@Override
	public void saveClientTicket(GenericClient genericClient) {
		clientDAO.saveClient(clientTransformer.fromModel(genericClient));
	}

	@Override
	public void deleteClientTicket(Integer id) {
		clientDAO.deleteClient(id);
	}
}
