package com.eu.gsys.wma.web.mappers;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.web.model.GristTicketModel;

public class GristTicketMapper {

	public static GristTicket toGristTicketFromModel(GristTicketModel gristTicketModel) {
		GristTicket gristTicket = new GristTicket();

		gristTicket.setReferenceId(gristTicketModel.getReferenceId());
		gristTicket.setOtherCorpusQty(gristTicketModel.getOtherCorpusQty());
		gristTicket.setManufacturingLossesQty(gristTicketModel.getManufacturingLossesQty());
		gristTicket.setFlourQtyForClient(gristTicketModel.getManufacturingLossesQty());
		gristTicket.setBranQtyForClient(gristTicketModel.getBranQtyForClient());
		gristTicket.setTollWheatQty(gristTicketModel.getTollWheatQty());
		gristTicket.setWheatQtyBrought(gristTicketModel.getWheatQtyBrought());
		gristTicket.setAddress(gristTicketModel.getAddress());
		gristTicket.setClientId(gristTicketModel.getClientId());
		gristTicket.setClientName(gristTicketModel.getClientName());
		gristTicket.setCompanyClientUIC(gristTicketModel.getCompanyClientUIC());
		gristTicket.setDate(gristTicketModel.getDate());
		gristTicket.setTicketId(gristTicketModel.getTicketId());

		return gristTicket;
	}

	public static GristTicketModel fromGristTicketToModel(GristTicket gristTicket) {
		GristTicketModel gristTicketModel = new GristTicketModel();

		gristTicketModel.setReferenceId(gristTicket.getReferenceId());
		gristTicketModel.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		gristTicketModel.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		gristTicketModel.setFlourQtyForClient(gristTicket.getManufacturingLossesQty());
		gristTicketModel.setBranQtyForClient(gristTicket.getBranQtyForClient());
		gristTicketModel.setTollWheatQty(gristTicket.getTollWheatQty());
		gristTicketModel.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		gristTicketModel.setAddress(gristTicket.getAddress());
		gristTicketModel.setClientId(gristTicket.getClientId());
		gristTicketModel.setClientName(gristTicket.getClientName());
		gristTicketModel.setCompanyClientUIC(gristTicket.getCompanyClientUIC());
		gristTicketModel.setDate(gristTicket.getDate());
		gristTicketModel.setTicketId(gristTicket.getTicketId());

		return gristTicketModel;
	}
}
