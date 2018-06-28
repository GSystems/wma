package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;

@Data
public class WithdrawTicket extends GenericTicket {

	private Double branQty = 0d;
	private Double flourQty = 0d;
	private Double wheatQty = 0d;
}
