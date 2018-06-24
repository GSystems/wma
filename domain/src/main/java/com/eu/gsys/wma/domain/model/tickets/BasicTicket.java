package com.eu.gsys.wma.domain.model.tickets;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BasicTicket {

	private Long id;
	private Long ticketId;
	private LocalDate date;
	private GenericClient genericClient;
	private OperationTypeEnum operationTypeEnum;
}
