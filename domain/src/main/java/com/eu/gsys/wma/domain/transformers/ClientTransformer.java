package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.clients.CompanyClient;
import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
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

			clientEntity.setFirstName(individualClient.getFirstName());
			clientEntity.setLastName(individualClient.getLastName());

			clientEntity.setAddress(individualClient.getAddress());
			clientEntity.setId(individualClient.getId());
			clientEntity.setJoinDate(individualClient.getJoinDate());

			return clientEntity;

		} else {
			CompanyClient companyClient = (CompanyClient) client;
			CompanyClientEntity clientEntity = new CompanyClientEntity();

			clientEntity.setCompanyId(companyClient.getCompanyId());
			clientEntity.setCompanyName(companyClient.getCompanyName());

			clientEntity.setAddress(companyClient.getAddress());
			clientEntity.setId(companyClient.getId());
			clientEntity.setJoinDate(companyClient.getJoinDate());

			return clientEntity;
		}
	}

	@Override
	public GenericClient toModel(GenericClientEntity genericClientEntity) {

		if (genericClientEntity instanceof IndividualClientEntity) {
			IndividualClientEntity clientEntity = (IndividualClientEntity) genericClientEntity;
			IndividualClient client = new IndividualClient();

			client.setFirstName(clientEntity.getFirstName());
			client.setLastName(clientEntity.getLastName());

			client.setAddress(clientEntity.getAddress());
			client.setId(clientEntity.getId());
			client.setJoinDate(clientEntity.getJoinDate());

			return client;

		} else {
			CompanyClientEntity clientEntity = (CompanyClientEntity) genericClientEntity;
			CompanyClient client = new CompanyClient();

			client.setCompanyId(clientEntity.getCompanyId());
			client.setCompanyName(clientEntity.getCompanyName());

			client.setAddress(clientEntity.getAddress());
			client.setId(clientEntity.getId());
			client.setJoinDate(clientEntity.getJoinDate());

			return client;
		}
	}
}
