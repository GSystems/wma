package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;

@Data
public class DepositTicket extends GenericTicket {

	private Double wheatQtyForDeposit;
	private Boolean consumedFlag = false;
}
