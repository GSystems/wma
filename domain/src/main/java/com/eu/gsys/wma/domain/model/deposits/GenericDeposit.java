package com.eu.gsys.wma.domain.model.deposits;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericDeposit {

	private Integer id;
	private Double wheatQty;
	private Double flourQty;
	private Double branQty;
	private OperationTypeEnum operationType;
	private Long ticketId;
	private LocalDate timestamp;
	private Client client;
}
