package com.eu.gsys.wma.domain.model.tickets;

import com.eu.gsys.wma.domain.model.Client;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BasicTicket {

	private Long ticketId;
	private LocalDate date;
	private Client client;
}
