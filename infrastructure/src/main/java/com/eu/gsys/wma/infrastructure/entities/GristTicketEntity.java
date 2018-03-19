package com.eu.gsys.wma.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class GristTicketEntity {

	@Id
	private Long ticketId;
	private LocalDate date;
	private String clientName;
	private String clientId;    // CNP
	private String companyclientUIC;    // CUI
	private String address;
	private Integer referenceId;
	private Double wheatQtyBrought;
	private Double tollWheatQty;    // uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;    //corpuri straine
	private Double manufacturingLossesQty;    // pierderi fabricatie

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCompanyClientUIC() {
		return companyclientUIC;
	}

	public void setCompanyClientUIC(String companyclientUIC) {
		this.companyclientUIC = companyclientUIC;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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
