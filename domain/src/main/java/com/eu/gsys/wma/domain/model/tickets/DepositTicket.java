package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepositTicket extends GenericTicket {

	private Double wheatQtyForDeposit;
	private Boolean consumedFlag = false;
}
