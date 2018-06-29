package com.eu.gsys.wma.domain.model.deposits;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericDeposit implements Cloneable {

	private Long id;
	private Double currentWheatQty = 0d;
	private Double flourQtyTaken = 0d;
	private Double branQtyTaken = 0d;
	private OperationTypeEnum operationType;
	private Long ticketNumber;
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
