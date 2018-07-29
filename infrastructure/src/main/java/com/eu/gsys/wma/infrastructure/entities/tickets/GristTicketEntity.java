package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "grist_tickets")
public class GristTicketEntity extends GenericTicketForEntities {

	@NotNull
	private Double wheatQtyBrought;
	private Double tollWheatQty;    // uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;    //corpuri straine
	private Double manufacturingLossesQty;    // pierderi fabricatie
}
