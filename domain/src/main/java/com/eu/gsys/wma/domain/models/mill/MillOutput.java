package com.eu.gsys.wma.domain.models.mill;

import lombok.Data;

@Data
public class MillOutput {

	private double branQty;
	private double flourQty;
	private double manufacturingLossesQty;
	private double otherCorpusQty;
	private double tollWheatQty;    // uium
}
