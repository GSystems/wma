package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements BaseTransformer<ClientEntity, Client> {

	@Override
	public ClientEntity fromModel(Client client) {
		ClientEntity clientEntity = new ClientEntity();

		clientEntity.setAddress(client.getAddress());
		clientEntity.setCompanyId(client.getCompanyId());
		clientEntity.setCompanyName(client.getCompanyName());
		clientEntity.setClientId(client.getClientId());
		clientEntity.setFirstName(client.getFirstName());
		clientEntity.setLastName(client.getLastName());
		clientEntity.setId(client.getId());
		clientEntity.setJoinDate(client.getJoinDate());

		return clientEntity;
	}

	@Override
	public Client toModel(ClientEntity clientEntity) {
		Client client = new Client();

		client.setAddress(clientEntity.getAddress());
		client.setClientId(clientEntity.getClientId());
		client.setCompanyId(clientEntity.getCompanyId());
		client.setCompanyName(clientEntity.getCompanyName());
		client.setFirstName(clientEntity.getFirstName());
		client.setLastName(clientEntity.getLastName());
		client.setJoinDate(clientEntity.getJoinDate());
		client.setId(clientEntity.getId());

		if (clientEntity.getCompanyId() != null) {
			client.setIsCompany(true);
		}

		return client;
	}
}
