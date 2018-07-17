package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.models.tickets.DepositTicket;
import com.eu.gsys.wma.domain.models.tickets.GenericTicket;
import com.eu.gsys.wma.domain.models.tickets.GristTicket;
import com.eu.gsys.wma.domain.models.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GenericTicketForEntities;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.WithdrawTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketTransformer implements BaseTransformer<GenericTicketForEntities, GenericTicket> {

	private final ClientTransformer clientTransformer;

	@Autowired
	public TicketTransformer(ClientTransformer clientTransformer) {
		this.clientTransformer = clientTransformer;
	}

	@Override
	public GenericTicketForEntities fromModel(GenericTicket ticket) {

		if (ticket instanceof DepositTicket) {
			return fromDepositTicketToEntity(ticket);
		} else if (ticket instanceof GristTicket){
			return fromGristTicketToEntity(ticket);
		} else {
			return fromWithdrawTicketToEntity(ticket);
		}
	}

	@Override
	public GenericTicket toModel(GenericTicketForEntities ticketEntity) {

		if (ticketEntity instanceof DepositTicketEntity) {
			return toDepositTicketFromEntity(ticketEntity);
		} else if (ticketEntity instanceof GristTicketEntity){
			return toGristTicketFromEntity(ticketEntity);
		} else {
			return toWithdrawTicketFromEntity(ticketEntity);
		}
	}

	private GenericTicketForEntities fromDepositTicketToEntity(GenericTicket ticket) {
		DepositTicket depositTicket = (DepositTicket) ticket;
		DepositTicketEntity ticketEntity = new DepositTicketEntity();

		mapCommonFieldsForEntity(ticket, ticketEntity);

		if (depositTicket.getConsumedFlag()) {
			ticketEntity.setConsumedFlag(1);
		}

		ticketEntity.setWheatQty(depositTicket.getWheatQty());

		return ticketEntity;
	}

	private GenericTicketForEntities fromGristTicketToEntity(GenericTicket ticket) {
		GristTicket gristTicket = (GristTicket) ticket;
		GristTicketEntity ticketEntity = new GristTicketEntity();

		mapCommonFieldsForEntity(ticket, ticketEntity);

		ticketEntity.setBranQtyForClient(gristTicket.getBranQtyForClient());
		ticketEntity.setFlourQtyForClient(gristTicket.getFlourQtyForClient());
		ticketEntity.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		ticketEntity.setOperationType(gristTicket.getOperationType().getCode());
		ticketEntity.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		ticketEntity.setTollWheatQty(gristTicket.getTollWheatQty());
		ticketEntity.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		ticketEntity.setWheatQtyForGrist(gristTicket.getWheatQtyForGrist());

		return ticketEntity;
	}

	private GenericTicketForEntities fromWithdrawTicketToEntity(GenericTicket ticket) {
		WithdrawTicket withdrawTicket = (WithdrawTicket) ticket;
		WithdrawTicketEntity ticketEntity = new WithdrawTicketEntity();

		mapCommonFieldsForEntity(ticket, ticketEntity);
		ticketEntity.setBranQtyWithdrawn(withdrawTicket.getBranQtyWithdrawn());
		ticketEntity.setFlourQtyWithdrawn(withdrawTicket.getFlourQtyWithdrawn());
		ticketEntity.setReferenceTicketNumber(withdrawTicket.getReferenceTicketNumber());
		ticketEntity.setWheatQtyWithdrawn(withdrawTicket.getWheatQtyWithdrawn());
		ticketEntity.setManufacturingLossesQty(withdrawTicket.getManufacturingLossesQty());
		ticketEntity.setOtherCorpusQty(withdrawTicket.getOtherCorpusQty());
		ticketEntity.setTollWheatQty(withdrawTicket.getTollWheatQty());

		return ticketEntity;
	}

	private void mapCommonFieldsForEntity(GenericTicket ticket, GenericTicketForEntities ticketEntity) {

		ticketEntity.setComment(ticket.getComment());
		ticketEntity.setDate(ticket.getDate());
		ticketEntity.setId(ticket.getId());
		ticketEntity.setTicketNumber(ticket.getTicketNumber());
		ticketEntity.setOperationType(ticket.getOperationType().getCode());

		GenericClientEntity clientEntity = clientTransformer.fromModel(ticket.getClient());

		if (clientEntity instanceof IndividualClientEntity) {
			ticketEntity.setIndividualClientEntity((IndividualClientEntity) clientEntity);
		} else {
			ticketEntity.setCompanyClientEntity((CompanyClientEntity) clientEntity);
		}
	}

	private GenericTicket toDepositTicketFromEntity(GenericTicketForEntities depositEntity) {
		DepositTicketEntity depositTicketEntity = (DepositTicketEntity) depositEntity;
		DepositTicket deposit = new DepositTicket();

		mapCommonFieldsForModel(deposit, depositTicketEntity);

		if (depositTicketEntity.getConsumedFlag() == 1) {
			deposit.setConsumedFlag(true);
		}

		deposit.setWheatQty(depositTicketEntity.getWheatQty());

		return deposit;
	}

	private GenericTicket toGristTicketFromEntity(GenericTicketForEntities ticketEntity) {
		GristTicketEntity gristTicketEntity = (GristTicketEntity) ticketEntity;
		GristTicket ticket = new GristTicket();

		mapCommonFieldsForModel(ticket, gristTicketEntity);

		ticket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
		ticket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
		ticket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
		ticket.setOperationType(OperationTypeEnum.getTicketTypeByCode(gristTicketEntity.getOperationType()));
		ticket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
		ticket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
		ticket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
		ticket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());

		return ticket;
	}

	private GenericTicket toWithdrawTicketFromEntity(GenericTicketForEntities ticketEntity) {
		WithdrawTicketEntity withdrawTicketEntity = (WithdrawTicketEntity) ticketEntity;
		WithdrawTicket ticket = new WithdrawTicket();

		mapCommonFieldsForModel(ticket, withdrawTicketEntity);

		ticket.setBranQtyWithdrawn(withdrawTicketEntity.getBranQtyWithdrawn());
		ticket.setFlourQtyWithdrawn(withdrawTicketEntity.getFlourQtyWithdrawn());
		ticket.setReferenceTicketNumber(withdrawTicketEntity.getReferenceTicketNumber());
		ticket.setWheatQtyWithdrawn(withdrawTicketEntity.getWheatQtyWithdrawn());
		ticket.setManufacturingLossesQty(withdrawTicketEntity.getManufacturingLossesQty());
		ticket.setOtherCorpusQty(withdrawTicketEntity.getOtherCorpusQty());
		ticket.setTollWheatQty(withdrawTicketEntity.getTollWheatQty());

		return ticket;
	}

	private void mapCommonFieldsForModel(GenericTicket ticket, GenericTicketForEntities ticketEntity) {
		GenericClientEntity genericClientEntity;

		if (ticketEntity.getIndividualClientEntity() != null) {
			genericClientEntity = ticketEntity.getIndividualClientEntity();
		} else {
			genericClientEntity = ticketEntity.getCompanyClientEntity();
		}

		ticket.setComment(ticketEntity.getComment());
		ticket.setClient(clientTransformer.toModel(genericClientEntity));
		ticket.setDate(ticketEntity.getDate());
		ticket.setId(ticketEntity.getId());
		ticket.setOperationType(OperationTypeEnum.getTicketTypeByCode(ticketEntity.getOperationType()));
		ticket.setTicketNumber(ticketEntity.getTicketNumber());
	}
}
