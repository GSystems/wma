package com.eu.gsys.wma.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class DepositTicketEntity extends BasicTicketEntity {

	private Double wheatQtyForDeposit;

	public Double getWheatQtyForDeposit() {
		return wheatQtyForDeposit;
	}

	public void setWheatQtyForDeposit(Double wheatQtyForDeposit) {
		this.wheatQtyForDeposit = wheatQtyForDeposit;
	}
}
