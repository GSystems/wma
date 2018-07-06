package com.eu.gsys.wma.domain.model;

import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {

	private Long id;
	private LocalDate date;
	private OperationTypeEnum operationType;
	private Long ticketNumber;

	private Double totalWheatQty = 0d;
	private Double wheatQtyOfCompany = 0d;
	private Double wheatQtyOfClients = 0d;

	private Double flourQtyOfCompany = 0d;
	private Double branQtyOfCompany = 0d;
}
