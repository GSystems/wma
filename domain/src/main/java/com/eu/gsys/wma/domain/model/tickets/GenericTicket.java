package com.eu.gsys.wma.domain.model.tickets;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericTicket {

	private Long id;
	private Long ticketNumber;
	private LocalDate date;
	private GenericClient client;
	private OperationTypeEnum operationType;
	private String comment;
}
