package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class GristTicketEntity extends BasicTicketMaster {

	private Integer referenceId;
	private Double wheatQtyBrought;
	private Double tollWheatQty;    // uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;    //corpuri straine
	private Double manufacturingLossesQty;    // pierderi fabricatie
}
