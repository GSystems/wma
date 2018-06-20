package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketTransformer {

	public GristTicketEntity fromGristTicketToEntity(GristTicket gristTicket) {
		GristTicketEntity gristTicketEntity = new GristTicketEntity();

//		gristTicketEntity.setAddress(gristTicket.getAddress());
		gristTicketEntity.setBranQtyForClient(gristTicket.getBranQtyForClient());
//		gristTicketEntity.setClientId(gristTicket.getClientId());
//		gristTicketEntity.setFirstName(gristTicket.getFirstName());
		gristTicketEntity.setFlourQtyForClient(gristTicket.getFlourQtyForClient());
		gristTicketEntity.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		gristTicketEntity.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		gristTicketEntity.setReferenceId(gristTicket.getReferenceId());
		gristTicketEntity.setTicketId(gristTicket.getTicketId());
		gristTicketEntity.setTollWheatQty(gristTicket.getTollWheatQty());
		gristTicketEntity.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		gristTicketEntity.setWheatQtyForGrist(gristTicket.getWheatQtyForGrist());
		gristTicketEntity.setTimestamp(gristTicket.getDate());

		return gristTicketEntity;
	}

	public GristTicket toGristTicketFromEntity(GristTicketEntity gristTicketEntity) {
		GristTicket gristTicket = new GristTicket();

//		gristTicket.setAddress(gristTicketEntity.getAddress());
		gristTicket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
//		gristTicket.setClientId(gristTicketEntity.getClientId());
		gristTicket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
		gristTicket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
		gristTicket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
		gristTicket.setReferenceId(gristTicketEntity.getReferenceId());
		gristTicket.setTicketId(gristTicketEntity.getTicketId());
		gristTicket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
		gristTicket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
		gristTicket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());
		gristTicket.setDate(gristTicketEntity.getTimestamp());

		return gristTicket;
	}

	public List<DepositTicket> toDepositTicketFromEntityList(Iterable<DepositTicketEntity> depositTicketEntities) {
		List<DepositTicket> depositTickets = new ArrayList<>();

		for (DepositTicketEntity depositTicketEntity : depositTicketEntities) {
			depositTickets.add(toDepositTicketFromEntity(depositTicketEntity));
		}

		return depositTickets;
	}

	public DepositTicket toDepositTicketFromEntity(DepositTicketEntity depositTicketEntity) {
		DepositTicket depositTicket = new DepositTicket();

		depositTicket.setWheatQtyForDeposit(depositTicketEntity.getWheatQtyForDeposit());
//		depositTicket.setAddress(depositTicketEntity.getAddress());
//		depositTicket.setClientId(depositTicketEntity.getClientId());
//		depositTicket.setFirstName(depositTicketEntity.getFirstName());
		depositTicket.setDate(depositTicketEntity.getTimestamp());
		depositTicket.setTicketId(depositTicketEntity.getTicketId());

		return depositTicket;
	}

	public DepositTicketEntity fromDepositTicketToEntity(DepositTicket depositTicket) {
		DepositTicketEntity depositTicketEntity = new DepositTicketEntity();

		depositTicketEntity.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());
//		depositTicketEntity.setAddress(depositTicket.getAddress());
//		depositTicketEntity.setClientId(depositTicket.getClientId());
//		depositTicketEntity.setFirstName(depositTicket.getFirstName());
		depositTicketEntity.setTimestamp(depositTicket.getDate());
		depositTicketEntity.setTicketId(depositTicket.getTicketId());

		return depositTicketEntity;
	}

	public Iterable<GristTicket> fromGristTicketToEntityList(Iterable<GristTicketEntity> gristTicketEntities) {
		List<GristTicket> gristTickets = new ArrayList<>();

		for (GristTicketEntity gristTicketEntity : gristTicketEntities) {
			gristTickets.add(toGristTicketFromEntity(gristTicketEntity));
		}

		return gristTickets;
	}
}
