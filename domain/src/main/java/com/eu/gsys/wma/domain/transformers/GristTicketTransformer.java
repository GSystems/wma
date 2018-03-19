package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.infrastructure.entities.GristTicketEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GristTicketTransformer {

	public static GristTicketEntity fromGristTicketToEntity(GristTicket gristTicket) {
		GristTicketEntity gristTicketEntity = new GristTicketEntity();

		gristTicketEntity.setAddress(gristTicket.getAddress());
		gristTicketEntity.setBranQtyForClient(gristTicket.getBranQtyForClient());
		gristTicketEntity.setClientId(gristTicket.getClientId());
		gristTicketEntity.setClientName(gristTicket.getClientName());
		gristTicketEntity.setCompanyClientUIC(gristTicket.getCompanyClientUIC());
		gristTicketEntity.setFlourQtyForClient(gristTicket.getFlourQtyForClient());
		gristTicketEntity.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		gristTicketEntity.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		gristTicketEntity.setReferenceId(gristTicket.getReferenceId());
		gristTicketEntity.setTicketId(gristTicket.getTicketId());
		gristTicketEntity.setTollWheatQty(gristTicket.getTollWheatQty());
		gristTicketEntity.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		gristTicketEntity.setWheatQtyForGrist(gristTicket.getWheatQtyForGrist());
		gristTicketEntity.setDate(gristTicket.getDate());

		return gristTicketEntity;
	}

	public static GristTicket toGristTicketFromEntity(GristTicketEntity gristTicketEntity) {
		GristTicket gristTicket = new GristTicket();

		gristTicket.setAddress(gristTicketEntity.getAddress());
		gristTicket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
		gristTicket.setClientId(gristTicketEntity.getClientId());
		gristTicket.setCompanyClientUIC(gristTicketEntity.getCompanyClientUIC());
		gristTicket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
		gristTicket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
		gristTicket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
		gristTicket.setReferenceId(gristTicketEntity.getReferenceId());
		gristTicket.setTicketId(gristTicketEntity.getTicketId());
		gristTicket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
		gristTicket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
		gristTicket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());
		gristTicket.setDate(gristTicketEntity.getDate());

		return gristTicket;
	}

	public static Iterable<GristTicket> fromGristTicketToEntityList(Iterable<GristTicketEntity> gristTicketEntities) {
		List<GristTicket> gristTickets = new ArrayList<>();

		for (GristTicketEntity gristTicketEntity : gristTicketEntities) {
			gristTickets.add(toGristTicketFromEntity(gristTicketEntity));
		}

		return gristTickets;
	}
}
