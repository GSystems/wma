package com.eu.gsys.wma.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Data
@Entity
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
