package com.eu.gsys.wma.domain.model;

import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GeneralDeposit implements Cloneable {

	private Long id;
	private LocalDate date;
	private OperationTypeEnum operationType;
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

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO log message
		}

		return null;
	}
}
