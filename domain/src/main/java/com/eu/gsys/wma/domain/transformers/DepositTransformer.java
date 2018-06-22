package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositTransformer implements BaseTransformer<GenericDepositForEntities, GenericDeposit> {

	@Autowired
	private ClientTransformer clientTransformer;

	@Override
	public GenericDepositForEntities fromModel(GenericDeposit genericDeposit) {
		GenericDepositForEntities genericDepositForEntities = new GenericDepositForEntities();

		genericDepositForEntities.setBranQty(genericDeposit.getBranQty());
		genericDepositForEntities.setClient(clientTransformer.fromModel(genericDeposit.getClient()));
		genericDepositForEntities.setFlourQty(genericDeposit.getFlourQty());
		genericDepositForEntities.setId(genericDeposit.getId());
		genericDepositForEntities.setOperationType(genericDeposit.getOperationType().getCode());
		genericDepositForEntities.setTicketId(genericDeposit.getTicketId());
		genericDepositForEntities.setTimestamp(genericDeposit.getTimestamp());
		genericDepositForEntities.setWheatQty(genericDeposit.getWheatQty());

		return genericDepositForEntities;
	}

	@Override
	public GenericDeposit toModel(GenericDepositForEntities genericDepositForEntities) {
		GenericDeposit genericDeposit = new GenericDeposit();

		genericDeposit.setBranQty(genericDepositForEntities.getBranQty());
		genericDeposit.setClient(clientTransformer.toModel(genericDepositForEntities.getClient()));
		genericDeposit.setFlourQty(genericDepositForEntities.getFlourQty());
		genericDeposit.setId(genericDepositForEntities.getId());
		genericDeposit
				.setOperationType(OperationTypeEnum.getTicketTypeByCode(genericDepositForEntities.getOperationType()));
		genericDeposit.setTicketId(genericDepositForEntities.getTicketId());
		genericDeposit.setTimestamp(genericDepositForEntities.getTimestamp());
		genericDeposit.setWheatQty(genericDepositForEntities.getWheatQty());

		return genericDeposit;
	}
}
