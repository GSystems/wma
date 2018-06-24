package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.deposits.CompanyClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.domain.transformers.GenericClientTransformer;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericDepositTransformer implements BaseTransformer<GenericDepositForEntities, GenericDeposit> {

	private GenericClientTransformer genericClientTransformer;

	@Autowired
	public GenericDepositTransformer(GenericClientTransformer genericDepositForEntities) {
		this.genericClientTransformer = genericDepositForEntities;
	}

	// TODO refactor this code

	@Override
	public GenericDepositForEntities fromModel(GenericDeposit genericDeposit) {
		if (genericDeposit instanceof IndividualClientDeposit) {
			IndividualClientDeposit individualClientDeposit = (IndividualClientDeposit) genericDeposit;
			IndividualClientDepositEntity individualClientDepositEntity = new IndividualClientDepositEntity();

			individualClientDepositEntity.setBranQty(genericDeposit.getBranQty());
			individualClientDepositEntity.setFlourQty(genericDeposit.getFlourQty());
			individualClientDepositEntity.setId(genericDeposit.getId());
			individualClientDepositEntity.setOperationType(genericDeposit.getOperationType().getCode());
			individualClientDepositEntity.setTicketId(genericDeposit.getTicketId());
			individualClientDepositEntity.setTimestamp(genericDeposit.getDate());
			individualClientDepositEntity.setWheatQty(genericDeposit.getWheatQty());

			IndividualClientEntity individualClientEntity = (IndividualClientEntity)
					genericClientTransformer.fromModel(individualClientDeposit.getGenericClient());

			individualClientDepositEntity.setIndividualClientEntity(individualClientEntity);

			return individualClientDepositEntity;
		} else {

			CompanyClientDeposit companyClientDeposit = (CompanyClientDeposit) genericDeposit;
			CompanyClientDepositEntity companyClientDepositEntity = new CompanyClientDepositEntity();

			companyClientDepositEntity.setBranQty(genericDeposit.getBranQty());
			companyClientDepositEntity.setFlourQty(genericDeposit.getFlourQty());
			companyClientDepositEntity.setId(genericDeposit.getId());
			companyClientDepositEntity.setOperationType(genericDeposit.getOperationType().getCode());
			companyClientDepositEntity.setTicketId(genericDeposit.getTicketId());
			companyClientDepositEntity.setTimestamp(genericDeposit.getDate());
			companyClientDepositEntity.setWheatQty(genericDeposit.getWheatQty());

			CompanyClientEntity companyClientEntity = (CompanyClientEntity)
					genericClientTransformer.fromModel(companyClientDeposit.getGenericClient());

			companyClientDepositEntity.setCompanyClientEntity(companyClientEntity);

			return companyClientDepositEntity;
		}
	}

	@Override
	public GenericDeposit toModel(GenericDepositForEntities genericDepositForEntities) {
		GenericDeposit genericDeposit = new GenericDeposit();

		genericDeposit.setBranQty(genericDepositForEntities.getBranQty());
		genericDeposit.setFlourQty(genericDepositForEntities.getFlourQty());
		genericDeposit.setId(genericDepositForEntities.getId());
		genericDeposit
				.setOperationType(OperationTypeEnum.getTicketTypeByCode(genericDepositForEntities.getOperationType()));
		genericDeposit.setTicketId(genericDepositForEntities.getTicketId());
		genericDeposit.setDate(genericDepositForEntities.getTimestamp());
		genericDeposit.setWheatQty(genericDepositForEntities.getWheatQty());

		return genericDeposit;
	}
}