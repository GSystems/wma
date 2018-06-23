package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.users.GenericClient;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements BaseTransformer<GenericClientEntity, GenericClient> {

	@Override
	public GenericClientEntity fromModel(GenericClient genericClient) {
		GenericClientEntity genericClientEntity = new GenericClientEntity();

		genericClientEntity.setAddress(genericClient.getAddress());
		genericClientEntity.setCompanyId(genericClient.getCompanyId());
		genericClientEntity.setCompanyName(genericClient.getCompanyName());
		genericClientEntity.setFirstName(genericClient.getFirstName());
		genericClientEntity.setLastName(genericClient.getLastName());
		genericClientEntity.setId(genericClient.getId());
		genericClientEntity.setJoinDate(genericClient.getJoinDate());

		return genericClientEntity;
	}

	@Override
	public GenericClient toModel(GenericClientEntity genericClientEntity) {
		GenericClient genericClient = new GenericClient();

		genericClient.setAddress(genericClientEntity.getAddress());
		genericClient.setCompanyId(genericClientEntity.getCompanyId());
		genericClient.setCompanyName(genericClientEntity.getCompanyName());
		genericClient.setFirstName(genericClientEntity.getFirstName());
		genericClient.setLastName(genericClientEntity.getLastName());
		genericClient.setJoinDate(genericClientEntity.getJoinDate());
		genericClient.setId(genericClientEntity.getId());

		if (genericClientEntity.getCompanyId() != null) {
			genericClient.setIsCompany(true);
		}

		return genericClient;
	}
}
