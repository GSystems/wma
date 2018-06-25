package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "grist_tickets")
public class GristTicketEntity extends GenericTicketForEntities {

	@NotNull
	private Double wheatQtyBrought = 0d;
	private Double tollWheatQty = 0d;    // uium
	private Double wheatQtyForGrist = 0d;
	private Double flourQtyForClient = 0d;
	private Double branQtyForClient = 0d; // tarate
	private Double otherCorpusQty = 0d;    //corpuri straine
	private Double manufacturingLossesQty = 0d;    // pierderi fabricatie
}
