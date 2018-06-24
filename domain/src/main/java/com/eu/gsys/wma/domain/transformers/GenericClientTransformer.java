package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.clients.CompanyClient;
import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import org.springframework.stereotype.Component;

@Component
public class GenericClientTransformer implements BaseTransformer<GenericClientEntity, GenericClient> {

	// TODO refactor this code

	@Override
	public GenericClientEntity fromModel(GenericClient genericClient) {

		if (genericClient instanceof IndividualClient) {
			IndividualClient individualClient = (IndividualClient) genericClient;
			IndividualClientEntity individualClientEntity = new IndividualClientEntity();

			individualClientEntity.setFirstName(individualClient.getFirstName());
			individualClientEntity.setLastName(individualClient.getLastName());

			individualClientEntity.setAddress(individualClient.getAddress());
			individualClientEntity.setId(individualClient.getId());
			individualClientEntity.setJoinDate(individualClient.getJoinDate());

			return individualClientEntity;

		} else {
			CompanyClient companyClient = (CompanyClient) genericClient;
			CompanyClientEntity companyClientEntity = new CompanyClientEntity();

			companyClientEntity.setCompanyId(companyClient.getCompanyId());
			companyClientEntity.setCompanyName(companyClient.getCompanyName());

			companyClientEntity.setAddress(companyClient.getAddress());
			companyClientEntity.setId(companyClient.getId());
			companyClientEntity.setJoinDate(companyClient.getJoinDate());

			return companyClientEntity;
		}
	}

	@Override
	public GenericClient toModel(GenericClientEntity genericClientEntity) {

		if (genericClientEntity instanceof IndividualClientEntity) {
			IndividualClientEntity individualClientEntity = (IndividualClientEntity) genericClientEntity;
			IndividualClient individualClient = new IndividualClient();

			individualClient.setFirstName(individualClientEntity.getFirstName());
			individualClient.setLastName(individualClientEntity.getLastName());

			individualClient.setAddress(individualClientEntity.getAddress());
			individualClient.setId(individualClientEntity.getId());
			individualClient.setJoinDate(individualClientEntity.getJoinDate());

			return individualClient;

		} else {
			CompanyClientEntity companyClientEntity = (CompanyClientEntity) genericClientEntity;
			CompanyClient companyClient = new CompanyClient();

			companyClient.setCompanyId(companyClientEntity.getCompanyId());
			companyClient.setCompanyName(companyClientEntity.getCompanyName());

			companyClient.setAddress(companyClientEntity.getAddress());
			companyClient.setId(companyClientEntity.getId());
			companyClient.setJoinDate(companyClientEntity.getJoinDate());

			return companyClient;
		}
	}
}
