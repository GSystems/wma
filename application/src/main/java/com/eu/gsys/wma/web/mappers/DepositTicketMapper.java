package com.eu.gsys.wma.web.mappers;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.web.model.DepositTicketModel;

public class DepositTicketMapper {

	public static DepositTicket toDepositTicketFromModel(DepositTicketModel depositTicketModel) {
		DepositTicket depositTicket = new DepositTicket();

		depositTicket.setTicketId(depositTicketModel.getTicketId());
		depositTicket.setDate(depositTicketModel.getDate());
		depositTicket.setCompanyClientUIC(depositTicketModel.getCompanyClientUIC());
		depositTicket.setClientName(depositTicketModel.getClientName());
		depositTicket.setClientId(depositTicketModel.getClientId());
		depositTicket.setAddress(depositTicketModel.getAddress());
		depositTicket.setWheatQtyForDeposit(depositTicketModel.getWheatQtyForDeposit());

		return depositTicket;
	}

	public static DepositTicketModel fromDepositTicketToModel(DepositTicket depositTicket) {
		DepositTicketModel depositTicketModel = new DepositTicketModel();

		depositTicketModel.setTicketId(depositTicket.getTicketId());
		depositTicketModel.setDate(depositTicket.getDate());
		depositTicketModel.setCompanyClientUIC(depositTicket.getCompanyClientUIC());
		depositTicketModel.setClientName(depositTicket.getClientName());
		depositTicket.setClientId(depositTicket.getClientId());
		depositTicketModel.setAddress(depositTicket.getAddress());
		depositTicketModel.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());

		return depositTicketModel;
	}
}
