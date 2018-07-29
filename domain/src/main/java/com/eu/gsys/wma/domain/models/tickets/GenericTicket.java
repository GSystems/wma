package com.eu.gsys.wma.domain.models.tickets;

import com.eu.gsys.wma.domain.models.clients.GenericClient;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

import static com.eu.gsys.wma.domain.util.GeneralConstants.EMPTY_STRING;

@Data
public class GenericTicket {

	private Long id;
	private Long ticketNumber;
	private LocalDate date;
	private GenericClient client;
	private OperationTypeEnum operationType;
	private String comment = EMPTY_STRING;
}
