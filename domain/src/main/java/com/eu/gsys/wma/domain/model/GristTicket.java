package com.eu.gsys.wma.domain.model;

public class GristTicket extends BasicTicket {

	private Integer referenceId;
	private Double wheatQtyBrought;
	private Double tollWheatQty;	// uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;	//corpuri straine
	private Double manufacturingLossesQty;	// pierderi fabricatie

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Double getWheatQtyBrought() {
		return wheatQtyBrought;
	}

	public void setWheatQtyBrought(Double wheatQtyBrought) {
		this.wheatQtyBrought = wheatQtyBrought;
	}

	public Double getTollWheatQty() {
		return tollWheatQty;
	}

	public void setTollWheatQty(Double tollWheatQty) {
		this.tollWheatQty = tollWheatQty;
	}

	public Double getWheatQtyForGrist() {
		return wheatQtyForGrist;
	}

	public void setWheatQtyForGrist(Double wheatQtyForGrist) {
		this.wheatQtyForGrist = wheatQtyForGrist;
	}

	public Double getFlourQtyForClient() {
		return flourQtyForClient;
	}

	public void setFlourQtyForClient(Double flourQtyForClient) {
		this.flourQtyForClient = flourQtyForClient;
	}

	public Double getBranQtyForClient() {
		return branQtyForClient;
	}

	public void setBranQtyForClient(Double branQtyForClient) {
		this.branQtyForClient = branQtyForClient;
	}

	public Double getOtherCorpusQty() {
		return otherCorpusQty;
	}

	public void setOtherCorpusQty(Double otherCorpusQty) {
		this.otherCorpusQty = otherCorpusQty;
	}

	public Double getManufacturingLossesQty() {
		return manufacturingLossesQty;
	}

	public void setManufacturingLossesQty(Double manufacturingLossesQty) {
		this.manufacturingLossesQty = manufacturingLossesQty;
	}
}
