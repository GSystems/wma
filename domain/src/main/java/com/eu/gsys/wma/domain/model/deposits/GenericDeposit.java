package com.eu.gsys.wma.domain.model.deposits;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericDeposit implements Cloneable {

	private Integer id;
	private Double wheatQty;
	private Double flourQty;
	private Double branQty;
	private OperationTypeEnum operationType;
	private Long ticketId;
	private LocalDate date;
	private GenericClient client;

	// TODO refactor this code

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO log message
		}

		return null;
	}
}
