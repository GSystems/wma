package com.eu.gsys.wma.domain.model.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GristTicket extends GenericTicket {

	private Double wheatQtyBrought;
	private Double tollWheatQty = 0d;    // uium
	private Double wheatQtyForGrist= 0d;
	private Double flourQtyForClient= 0d;
	private Double branQtyForClient= 0d; // tarate
	private Double otherCorpusQty= 0d;    //corpuri straine
	private Double manufacturingLossesQty= 0d;    // pierderi fabricatie
}

