package com.eu.gsys.wma.domain.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Transaction {

	private Long id;
	private LocalTime time;
	private String operationType;
	private Long ticketId;

	private Double totalWheatQty;
	private Double wheatQtyOfCompany;
	private Double wheatQtyOfClients;

	private Double totalFlourQty;
	private Double flourQtyOfCompany;
	private Double flourQtyOfClients;

	private Double totalBranQty;
	private Double branQtyOfCompany;
	private Double branQtyOfClients;
}
