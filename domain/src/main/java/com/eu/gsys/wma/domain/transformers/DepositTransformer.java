package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.deposits.CompanyClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositTransformer implements BaseTransformer<GenericDepositForEntities, GenericDeposit> {

	private ClientTransformer clientTransformer;

	@Autowired
	public DepositTransformer(ClientTransformer genericDepositForEntities) {
		this.clientTransformer = genericDepositForEntities;
	}

	// TODO refactor this code

	@Override
	public GenericDepositForEntities fromModel(GenericDeposit genericDeposit) {
		if (genericDeposit instanceof IndividualClientDeposit) {
			IndividualClientDeposit deposit = (IndividualClientDeposit) genericDeposit;
			IndividualClientDepositEntity depositEntity = new IndividualClientDepositEntity();

			depositEntity.setBranQty(genericDeposit.getBranQty());
			depositEntity.setFlourQty(genericDeposit.getFlourQty());
			depositEntity.setId(genericDeposit.getId());
			depositEntity.setOperationType(genericDeposit.getOperationType().getCode());
			depositEntity.setTicketId(genericDeposit.getTicketId());
			depositEntity.setDate(genericDeposit.getDate());
			depositEntity.setWheatQty(genericDeposit.getWheatQty());

			IndividualClientEntity individualClientEntity = (IndividualClientEntity) clientTransformer
					.fromModel(deposit.getGenericClient());

			depositEntity.setIndividualClientEntity(individualClientEntity);

			return depositEntity;
		} else {

			CompanyClientDeposit deposit = (CompanyClientDeposit) genericDeposit;
			CompanyClientDepositEntity depositEntity = new CompanyClientDepositEntity();

			depositEntity.setBranQty(deposit.getBranQty());
			depositEntity.setFlourQty(deposit.getFlourQty());
			depositEntity.setId(deposit.getId());
			depositEntity.setOperationType(deposit.getOperationType().getCode());
			depositEntity.setTicketId(deposit.getTicketId());
			depositEntity.setDate(deposit.getDate());
			depositEntity.setWheatQty(deposit.getWheatQty());

			CompanyClientEntity companyClientEntity =
					(CompanyClientEntity) clientTransformer.fromModel(deposit.getGenericClient());

			depositEntity.setClientEntity(companyClientEntity);

			return depositEntity;
		}
	}

	@Override
	public GenericDeposit toModel(GenericDepositForEntities depositEntity) {
		GenericDeposit deposit;
		GenericClientEntity genericClientEntity;

		if (depositEntity instanceof IndividualClientDepositEntity) {
			deposit = new IndividualClientDeposit();
			genericClientEntity =
					((IndividualClientDepositEntity) depositEntity).getIndividualClientEntity();
		} else {
			deposit = new CompanyClientDeposit();
			genericClientEntity = ((CompanyClientDepositEntity) depositEntity).getClientEntity();
		}
		deposit.setBranQty(depositEntity.getBranQty());
		deposit.setFlourQty(depositEntity.getFlourQty());
		deposit.setGenericClient(clientTransformer.toModel(genericClientEntity));
		deposit.setId(depositEntity.getId());
		deposit
				.setOperationType(OperationTypeEnum.getTicketTypeByCode(depositEntity.getOperationType()));
		deposit.setTicketId(depositEntity.getTicketId());
		deposit.setDate(depositEntity.getDate());
		deposit.setWheatQty(depositEntity.getWheatQty());

		return deposit;
	}
}