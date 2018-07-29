package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "withdraw_tickets")
@EqualsAndHashCode(callSuper = false)
public class WithdrawTicketEntity extends GenericTicketForEntities {

	private Double branQtyWithdrawn;
	private Double flourQtyWithdrawn;
	private Double wheatQtyWithdrawn;
	private double manufacturingLossesQty;
	private double otherCorpusQty;
	private double tollWheatQty;    // uium


	private Long referenceTicketNumber;
}
