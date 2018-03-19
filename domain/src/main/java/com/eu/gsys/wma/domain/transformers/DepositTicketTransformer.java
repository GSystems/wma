package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.infrastructure.entities.DepositTicketEntity;

import java.util.ArrayList;
import java.util.List;

public class DepositTicketTransformer {

	public static List<DepositTicket> toDepositTicketFromEntityList(Iterable<DepositTicketEntity> depositTicketEntities) {
		List<DepositTicket> depositTickets = new ArrayList<>();

		for (DepositTicketEntity depositTicketEntity : depositTicketEntities) {
			depositTickets.add(toDepositTicketFromEntity(depositTicketEntity));
		}

		return depositTickets;
	}

	public static DepositTicket toDepositTicketFromEntity(DepositTicketEntity depositTicketEntity) {
		DepositTicket depositTicket = new DepositTicket();

		depositTicket.setWheatQtyForDeposit(depositTicketEntity.getWheatQtyForDeposit());
		depositTicket.setAddress(depositTicketEntity.getAddress());
		depositTicket.setClientId(depositTicketEntity.getClientId());
		depositTicket.setClientName(depositTicketEntity.getClientName());
		depositTicket.setCompanyClientUIC(depositTicketEntity.getCompanyClientUIC());
		depositTicket.setDate(depositTicketEntity.getDate());
		depositTicket.setTicketId(depositTicketEntity.getTicketId());

		return depositTicket;
	}

	public static DepositTicketEntity fromDepositTicketToEntity(DepositTicket depositTicket) {
		DepositTicketEntity depositTicketEntity = new DepositTicketEntity();

		depositTicketEntity.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());
		depositTicketEntity.setAddress(depositTicket.getAddress());
		depositTicketEntity.setClientId(depositTicket.getClientId());
		depositTicketEntity.setClientName(depositTicket.getClientName());
		depositTicketEntity.setCompanyClientUIC(depositTicket.getCompanyClientUIC());
		depositTicketEntity.setDate(depositTicket.getDate());
		depositTicketEntity.setTicketId(depositTicket.getTicketId());

		return depositTicketEntity;
	}
}
