package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.GenericTicket;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GenericTicketForEntities;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
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
		} else {
			return fromGristTicketToEntity(ticket);
		}
	}

	@Override
	public GenericTicket toModel(GenericTicketForEntities ticketEntity) {

		if (ticketEntity instanceof DepositTicketEntity) {
			return toDepositTicketFromEntity(ticketEntity);
		} else {
			return toGristTicketFromEntity(ticketEntity);
		}
	}

	private GenericTicketForEntities fromDepositTicketToEntity(GenericTicket ticket) {
		DepositTicket depositTicket = (DepositTicket) ticket;
		DepositTicketEntity ticketEntity = new DepositTicketEntity();

		mapCommonFieldsForEntity(ticket, ticketEntity);

		if (depositTicket.getConsumedFlag()) {
			ticketEntity.setConsumedFlag(1);
		}

		ticketEntity.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());

		return ticketEntity;
	}

	private GenericTicketForEntities fromGristTicketToEntity(GenericTicket ticket) {
		GristTicket gristTicket = (GristTicket) ticket;
		GristTicketEntity ticketEntity = new GristTicketEntity();

		mapCommonFieldsForEntity(ticket, ticketEntity);

		ticketEntity.setBranQtyForClient(gristTicket.getBranQtyForClient());
		ticketEntity.setFlourQtyForClient(gristTicket.getFlourQtyForClient());
		ticketEntity.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		ticketEntity.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		ticketEntity.setTollWheatQty(gristTicket.getTollWheatQty());
		ticketEntity.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		ticketEntity.setWheatQtyForGrist(gristTicket.getWheatQtyForGrist());

		return ticketEntity;
	}

	private void mapCommonFieldsForEntity(GenericTicket ticket, GenericTicketForEntities ticketEntity) {

		ticketEntity.setDate(ticket.getDate());
		ticketEntity.setId(ticket.getId());
		ticketEntity.setTicketId(ticket.getTicketId());

		GenericClientEntity clientEntity = clientTransformer.fromModel(ticket.getClient());

		if (clientEntity instanceof IndividualClientEntity) {
			ticketEntity.setIndividualClientEntity((IndividualClientEntity) clientEntity);
		} else {
			ticketEntity.setCompanyClientEntity((CompanyClientEntity) clientEntity);
		}
	}

	private GenericTicket toDepositTicketFromEntity(GenericTicketForEntities ticketEntity) {
		DepositTicketEntity depositTicketEntity = (DepositTicketEntity) ticketEntity;
		DepositTicket ticket = new DepositTicket();

		mapCommonFieldsForModel(ticket, depositTicketEntity);

		if (depositTicketEntity.getConsumedFlag() == 1) {
			ticket.setConsumedFlag(true);
		}

		ticket.setWheatQtyForDeposit(depositTicketEntity.getWheatQtyForDeposit());

		return ticket;
	}

	private GenericTicket toGristTicketFromEntity(GenericTicketForEntities ticketEntity) {
		GristTicketEntity gristTicketEntity = (GristTicketEntity) ticketEntity;
		GristTicket ticket = new GristTicket();

		mapCommonFieldsForModel(ticket, gristTicketEntity);

		ticket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
		ticket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
		ticket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
		ticket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
		ticket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
		ticket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
		ticket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());

		return ticket;
	}

	private void mapCommonFieldsForModel(GenericTicket ticket, GenericTicketForEntities ticketEntity) {
		GenericClientEntity genericClientEntity;

		if (ticketEntity.getIndividualClientEntity() != null) {
			genericClientEntity = ticketEntity.getIndividualClientEntity();
		} else {
			genericClientEntity = ticketEntity.getCompanyClientEntity();
		}

		ticket.setClient(clientTransformer.toModel(genericClientEntity));
		ticket.setDate(ticketEntity.getDate());
		ticket.setId(ticketEntity.getId());
		ticket.setOperationTypeEnum(OperationTypeEnum.getTicketTypeByCode(ticketEntity.getOperationType()));
		ticket.setTicketId(ticketEntity.getTicketId());
	}
}
