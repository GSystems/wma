package com.eu.gsys.wma.domain.model.mill;

import org.springframework.stereotype.Component;

import static com.eu.gsys.wma.domain.util.PercentageForGrind.*;

@Component
public class Mill {

	public MillOutput grindWheat(Double wheatQty) {
		double branQty = Math.round(wheatQty * BRAN_PERC);
		double flourQty = Math.round(wheatQty * FLOUR_PERC);
		double manufacturingLossesQty = Math.round(wheatQty * MANUFACTURING_LOSSES_PERC);
		double otherCorpusQty = Math.round(wheatQty * OTHER_CORPUS_PERC);
		double tollWheatQty = Math.round(wheatQty * TOLL_WHEAT_PERC);

		MillOutput millOutput = new MillOutput();
		millOutput.setBranQty(branQty);
		millOutput.setFlourQty(flourQty);
		millOutput.setManufacturingLossesQty(manufacturingLossesQty);
		millOutput.setOtherCorpusQty(otherCorpusQty);
		millOutput.setTollWheatQty(tollWheatQty);

		return millOutput;
	}
}
