package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class WithdrawTicket extends GenericTicket {

	private Double branQty = 0d;
	private Double flourQty = 0d;
	private Double wheatQty = 0d;

	@NotNull
	private Long referenceTicketNumber;
}
