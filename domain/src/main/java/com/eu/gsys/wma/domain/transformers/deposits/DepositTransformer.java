package com.eu.gsys.wma.domain.transformers.deposits;

import com.eu.gsys.wma.domain.model.deposits.ClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositTransformer<T> {

	@Autowired
	private ClientTransformer clientTransformer;

	// TODO refactoring is needed here

	public GenericDepositForEntities fromModel(T t) {
		GenericDepositForEntities genericDepositForEntities;

		if (t instanceof ClientDeposit) {
			genericDepositForEntities = new IndividualClientDepositEntity();
		} else {
			genericDepositForEntities = new CompanyClientDepositEntity();
		}

		GenericDeposit genericDeposit = (GenericDeposit) t;

		genericDepositForEntities.setBranQty(genericDeposit.getBranQty());
		genericDepositForEntities.setClient(clientTransformer.fromModel(genericDeposit.getGenericClient()));
		genericDepositForEntities.setFlourQty(genericDeposit.getFlourQty());
		genericDepositForEntities.setId(genericDeposit.getId());
		genericDepositForEntities.setOperationType(genericDeposit.getOperationType().getCode());
		genericDepositForEntities.setTicketId(genericDeposit.getTicketId());
		genericDepositForEntities.setTimestamp(genericDeposit.getDate());
		genericDepositForEntities.setWheatQty(genericDeposit.getWheatQty());

		return genericDepositForEntities;
	}

	public GenericDeposit toModel(GenericDepositForEntities genericDepositForEntities) {
		GenericDeposit genericDeposit = new GenericDeposit();

		genericDeposit.setBranQty(genericDepositForEntities.getBranQty());
		genericDeposit.setGenericClient(clientTransformer.toModel(genericDepositForEntities.getClient()));
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