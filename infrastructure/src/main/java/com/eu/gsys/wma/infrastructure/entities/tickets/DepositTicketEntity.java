package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class DepositTicketEntity extends BasicTicketMaster {

	private Double wheatQtyForDeposit;
}
