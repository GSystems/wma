package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.models.clients.CompanyClient;
import com.eu.gsys.wma.domain.models.clients.GenericClient;
import com.eu.gsys.wma.domain.models.clients.IndividualClient;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements BaseTransformer<GenericClientEntity, GenericClient> {

	// TODO refactor this code

	@Override
	public GenericClientEntity fromModel(GenericClient client) {

		if (client instanceof IndividualClient) {
			IndividualClient individualClient = (IndividualClient) client;
			IndividualClientEntity clientEntity = new IndividualClientEntity();

			mapCommonFieldsForEntity(individualClient, clientEntity);

			clientEntity.setFirstName(individualClient.getFirstName());
			clientEntity.setLastName(individualClient.getLastName());
			clientEntity.setPersonalId(individualClient.getPersonalId());

			return clientEntity;

		} else {
			CompanyClient companyClient = (CompanyClient) client;
			CompanyClientEntity clientEntity = new CompanyClientEntity();

			mapCommonFieldsForEntity(companyClient, clientEntity);

			clientEntity.setCompanyId(companyClient.getCompanyId());
			clientEntity.setCompanyName(companyClient.getCompanyName());

			return clientEntity;
		}
	}

	private void mapCommonFieldsForEntity(GenericClient client, GenericClientEntity clientEntity) {
		clientEntity.setAddress(client.getAddress());
		clientEntity.setId(client.getId());
		clientEntity.setJoinDate(client.getJoinDate());
	}

	@Override
	public GenericClient toModel(GenericClientEntity genericClientEntity) {

		if (genericClientEntity instanceof IndividualClientEntity) {
			IndividualClientEntity clientEntity = (IndividualClientEntity) genericClientEntity;
			IndividualClient client = new IndividualClient();

			mapCommonFieldsForModel(client, clientEntity);

			client.setFirstName(clientEntity.getFirstName());
			client.setLastName(clientEntity.getLastName());
			client.setPersonalId(clientEntity.getPersonalId());

			return client;

		} else {
			CompanyClientEntity clientEntity = (CompanyClientEntity) genericClientEntity;
			CompanyClient client = new CompanyClient();

			mapCommonFieldsForModel(client, clientEntity);

			client.setCompanyId(clientEntity.getCompanyId());
			client.setCompanyName(clientEntity.getCompanyName());

			return client;
		}
	}

	private void mapCommonFieldsForModel(GenericClient client, GenericClientEntity clientEntity) {
		client.setAddress(clientEntity.getAddress());
		client.setId(clientEntity.getId());
		client.setJoinDate(clientEntity.getJoinDate());
	}
}
