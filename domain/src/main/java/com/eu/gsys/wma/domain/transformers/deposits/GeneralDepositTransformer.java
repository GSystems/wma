package com.eu.gsys.wma.domain.transformers.deposits;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneralDepositTransformer implements BaseTransformer<GeneralDepositEntity, GeneralDeposit> {

	@Override
	public GeneralDeposit toModel(GeneralDepositEntity generalDepositEntity) {
		GeneralDeposit generalDeposit = new GeneralDeposit();

		generalDeposit.setBranQtyOfClients(generalDepositEntity.getBranQtyOfClients());
		generalDeposit.setBranQtyOfCompany(generalDepositEntity.getBranQtyOfCompany());
		generalDeposit.setFlourQtyOfClients(generalDepositEntity.getFlourQtyOfClients());
		generalDeposit.setFlourQtyOfCompany(generalDepositEntity.getFlourQtyOfCompany());
		generalDeposit.setId(generalDepositEntity.getId());
		generalDeposit.setOperationType(OperationTypeEnum.getTicketTypeByCode(generalDepositEntity.getTicketType()));
		generalDeposit.setTicketId(generalDepositEntity.getTicketId());
		generalDeposit.setDate(generalDepositEntity.getDate());
		generalDeposit.setTotalBranQty(generalDepositEntity.getTotalBranQty());
		generalDeposit.setTotalFlourQty(generalDepositEntity.getTotalFlourQty());
		generalDeposit.setTotalWheatQty(generalDepositEntity.getTotalWheatQty());
		generalDeposit.setWheatQtyOfClients(generalDepositEntity.getWheatQtyOfClients());
		generalDeposit.setWheatQtyOfCompany(generalDepositEntity.getWheatQtyOfCompany());

		return generalDeposit;
	}

	@Override
	public GeneralDepositEntity fromModel(GeneralDeposit generalDeposit) {
		GeneralDepositEntity generalDepositEntity = new GeneralDepositEntity();

		generalDepositEntity.setBranQtyOfClients(generalDeposit.getBranQtyOfClients());
		generalDepositEntity.setBranQtyOfCompany(generalDeposit.getBranQtyOfCompany());
		generalDepositEntity.setFlourQtyOfClients(generalDeposit.getFlourQtyOfClients());
		generalDepositEntity.setFlourQtyOfCompany(generalDeposit.getFlourQtyOfCompany());
		generalDepositEntity.setId(generalDeposit.getId());
		generalDepositEntity.setTicketType(generalDeposit.getOperationType().getCode());
		generalDepositEntity.setTicketId(generalDeposit.getTicketId());
		generalDepositEntity.setDate(generalDeposit.getDate());
		generalDepositEntity.setTotalBranQty(generalDeposit.getTotalBranQty());
		generalDepositEntity.setTotalFlourQty(generalDeposit.getTotalFlourQty());
		generalDepositEntity.setTotalWheatQty(generalDeposit.getTotalWheatQty());
		generalDepositEntity.setWheatQtyOfClients(generalDeposit.getWheatQtyOfClients());
		generalDepositEntity.setWheatQtyOfCompany(generalDeposit.getWheatQtyOfCompany());

		return generalDepositEntity;
	}
}
