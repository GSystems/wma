package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.GeneralDepositEntity;

public class GeneralDepositTransformer implements BaseTransformer<GeneralDepositEntity, com.eu.gsys.wma.domain.model.GeneralDeposit> {

	@Override
	public com.eu.gsys.wma.domain.model.GeneralDeposit toModel(GeneralDepositEntity generalDepositEntity) {
		com.eu.gsys.wma.domain.model.GeneralDeposit generalDeposit = new com.eu.gsys.wma.domain.model.GeneralDeposit();

		generalDeposit.setBranQtyOfClients(generalDepositEntity.getBranQtyOfClients());
		generalDeposit.setBranQtyOfCompany(generalDepositEntity.getBranQtyOfCompany());
		generalDeposit.setFlourQtyOfClients(generalDepositEntity.getFlourQtyOfClients());
		generalDeposit.setFlourQtyOfCompany(generalDepositEntity.getFlourQtyOfCompany());
		generalDeposit.setId(generalDepositEntity.getId());
		generalDeposit.setTicketType(OperationTypeEnum.getTicketTypeByCode(generalDepositEntity.getTicketType()));
		generalDeposit.setTicketId(generalDepositEntity.getTicketId());
		generalDeposit.setTime(generalDepositEntity.getTime());
		generalDeposit.setTotalBranQty(generalDepositEntity.getTotalBranQty());
		generalDeposit.setTotalFlourQty(generalDepositEntity.getTotalFlourQty());
		generalDeposit.setTotalWheatQty(generalDepositEntity.getTotalWheatQty());
		generalDeposit.setWheatQtyOfClients(generalDepositEntity.getWheatQtyOfClients());
		generalDeposit.setWheatQtyOfCompany(generalDepositEntity.getWheatQtyOfCompany());

		return generalDeposit;
	}

	@Override
	public GeneralDepositEntity fromModel(com.eu.gsys.wma.domain.model.GeneralDeposit generalDeposit) {
		GeneralDepositEntity generalDepositEntity = new GeneralDepositEntity();

		generalDepositEntity.setBranQtyOfClients(generalDeposit.getBranQtyOfClients());
		generalDepositEntity.setBranQtyOfCompany(generalDeposit.getBranQtyOfCompany());
		generalDepositEntity.setFlourQtyOfClients(generalDeposit.getFlourQtyOfClients());
		generalDepositEntity.setFlourQtyOfCompany(generalDeposit.getFlourQtyOfCompany());
		generalDepositEntity.setId(generalDeposit.getId());
		generalDepositEntity.setTicketType(generalDeposit.getTicketType().getCode());
		generalDepositEntity.setTicketId(generalDeposit.getTicketId());
		generalDepositEntity.setTime(generalDeposit.getTime());
		generalDepositEntity.setTotalBranQty(generalDeposit.getTotalBranQty());
		generalDepositEntity.setTotalFlourQty(generalDeposit.getTotalFlourQty());
		generalDepositEntity.setTotalWheatQty(generalDeposit.getTotalWheatQty());
		generalDepositEntity.setWheatQtyOfClients(generalDeposit.getWheatQtyOfClients());
		generalDepositEntity.setWheatQtyOfCompany(generalDeposit.getWheatQtyOfCompany());

		return generalDepositEntity;
	}
}
