package com.eu.gsys.wma.domain.transformers.deposits;

import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDepositTransformer implements BaseTransformer<GenericDepositForEntities, GenericDeposit> {

	@Autowired
	private DepositTransformer depositTransformer;

	@Override
	public GenericDepositForEntities fromModel(GenericDeposit genericDeposit) {
		return depositTransformer.fromModel(genericDeposit);
	}

	@Override
	public GenericDeposit toModel(GenericDepositForEntities genericDepositForEntities) {
		return depositTransformer.toModel(genericDepositForEntities);
	}
}
