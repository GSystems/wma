package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "deposit_tickets")
public class DepositTicketEntity extends GenericTicketForEntities {

	@NotNull
	private Double wheatQtyForDeposit;
	private int consumedFlag = 0;
}
