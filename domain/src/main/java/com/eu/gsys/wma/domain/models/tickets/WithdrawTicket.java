package com.eu.gsys.wma.domain.models.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class WithdrawTicket extends GenericTicket {

	private Double branQtyWithdrawn;
	private Double flourQtyWithdrawn;
	private Double wheatQtyWithdrawn;
	private double manufacturingLossesQty;
	private double otherCorpusQty;
	private double tollWheatQty;    // uium

	@NotNull
	private Long referenceTicketNumber;
}
