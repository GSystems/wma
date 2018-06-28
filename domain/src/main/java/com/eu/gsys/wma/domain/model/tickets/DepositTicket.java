package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepositTicket extends GenericTicket {

	private Double wheatQty;
	private Boolean consumedFlag = false;
}
