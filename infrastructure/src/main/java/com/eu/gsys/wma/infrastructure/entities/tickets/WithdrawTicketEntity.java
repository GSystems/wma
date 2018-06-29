package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class WithdrawTicketEntity extends GenericTicketForEntities {

	private Double branQty;
	private Double flourQty;
	private Double wheatQty;
	private Long referenceTicketNumber;
}
