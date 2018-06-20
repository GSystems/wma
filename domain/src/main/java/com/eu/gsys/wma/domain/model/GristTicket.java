package com.eu.gsys.wma.domain.model;

import lombok.Data;

@Data
public class GristTicket extends BasicTicket {

	private Integer referenceId;
	private Double wheatQtyBrought;
	private Double tollWheatQty;    // uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;    //corpuri straine
	private Double manufacturingLossesQty;    // pierderi fabricatie
}
