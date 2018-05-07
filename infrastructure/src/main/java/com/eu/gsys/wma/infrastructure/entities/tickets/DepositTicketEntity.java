package com.eu.gsys.wma.infrastructure.entities.tickets;

import javax.persistence.Entity;

@Entity
public class DepositTicketEntity extends BasicTicketMaster {

	private Double wheatQtyForDeposit;

	public Double getWheatQtyForDeposit() {
		return wheatQtyForDeposit;
	}

	public void setWheatQtyForDeposit(Double wheatQtyForDeposit) {
		this.wheatQtyForDeposit = wheatQtyForDeposit;
	}
}
