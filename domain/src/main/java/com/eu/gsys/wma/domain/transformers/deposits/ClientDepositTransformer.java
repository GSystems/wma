package com.eu.gsys.wma.domain.transformers.deposits;

import com.eu.gsys.wma.domain.model.deposits.ClientDeposit;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.infrastructure.entities.deposits.ClientDepositEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDepositTransformer implements BaseTransformer<ClientDepositEntity, ClientDeposit> {

	@Autowired
	private DepositTransformer depositTransformer;

	@Override
	public ClientDepositEntity fromModel(ClientDeposit clientDeposit) {
		ClientDepositEntity clientDepositEntity = (ClientDepositEntity) depositTransformer.fromModel(clientDeposit);

		return clientDepositEntity;
	}

	@Override
	public ClientDeposit toModel(ClientDepositEntity clientDepositEntity) {
		ClientDeposit clientDeposit = (ClientDeposit) depositTransformer.toModel(clientDepositEntity);

		return clientDeposit;
	}
}
