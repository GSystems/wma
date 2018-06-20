package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.infrastructure.dao.ClientDAO;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientTransformer clientTransformer;

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public Iterable<Client> listAllClients() {
		List<Client> clientList = new ArrayList<>();
		List<ClientEntity> clientEntities = (List<ClientEntity>) clientDAO.listAllClients();

		for (ClientEntity clientEntity : clientEntities) {
			clientList.add(clientTransformer.toModel(clientEntity));
		}

		return clientList;
	}

	@Override
	public Client getClientById(Integer id) {
		return clientTransformer.toModel(clientDAO.getClientById(id));
	}

	@Override
	public void saveClientTicket(Client client) {
		clientDAO.saveClient(clientTransformer.fromModel(client));
	}

	@Override
	public void deleteClientTicket(Integer id) {
		clientDAO.deleteClient(id);
	}
}
