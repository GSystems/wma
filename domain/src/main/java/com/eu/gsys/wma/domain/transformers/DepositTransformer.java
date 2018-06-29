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

			mapCommonFieldsForEntity(genericDeposit, depositEntity);

			IndividualClientEntity individualClientEntity = (IndividualClientEntity) clientTransformer
					.fromModel(deposit.getClient());

			depositEntity.setClientEntity(individualClientEntity);

			return depositEntity;
		} else {

			CompanyClientDeposit deposit = (CompanyClientDeposit) genericDeposit;
			CompanyClientDepositEntity depositEntity = new CompanyClientDepositEntity();

			mapCommonFieldsForEntity(genericDeposit, depositEntity);

			CompanyClientEntity companyClientEntity =
					(CompanyClientEntity) clientTransformer.fromModel(deposit.getClient());

			depositEntity.setClientEntity(companyClientEntity);

			return depositEntity;
		}
	}

	private void mapCommonFieldsForEntity(GenericDeposit deposit, GenericDepositForEntities depositEntity) {
		depositEntity.setBranQty(deposit.getBranQtyTaken());
		depositEntity.setFlourQty(deposit.getFlourQtyTaken());
		depositEntity.setId(deposit.getId());
		depositEntity.setOperationType(deposit.getOperationType().getCode());
		depositEntity.setTicketNumber(deposit.getTicketNumber());
		depositEntity.setDate(deposit.getDate());
		depositEntity.setWheatQty(deposit.getCurrentWheatQty());
	}

	@Override
	public GenericDeposit toModel(GenericDepositForEntities depositEntity) {
		GenericDeposit deposit;
		GenericClientEntity genericClientEntity;

		if (depositEntity instanceof IndividualClientDepositEntity) {
			deposit = new IndividualClientDeposit();
			genericClientEntity =
					((IndividualClientDepositEntity) depositEntity).getClientEntity();
		} else {
			deposit = new CompanyClientDeposit();
			genericClientEntity = ((CompanyClientDepositEntity) depositEntity).getClientEntity();
		}

		deposit.setBranQtyTaken(depositEntity.getBranQty());
		deposit.setFlourQtyTaken(depositEntity.getFlourQty());
		deposit.setClient(clientTransformer.toModel(genericClientEntity));
		deposit.setId(depositEntity.getId());
		deposit
				.setOperationType(OperationTypeEnum.getTicketTypeByCode(depositEntity.getOperationType()));
		deposit.setTicketNumber(depositEntity.getTicketNumber());
		deposit.setDate(depositEntity.getDate());
		deposit.setCurrentWheatQty(depositEntity.getWheatQty());

		return deposit;
	}
}