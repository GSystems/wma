package com.eu.gsys.wma.domain.model.deposits;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IndividualClientDeposit extends GenericDeposit {

	private double referenceTicketNumber;
}
