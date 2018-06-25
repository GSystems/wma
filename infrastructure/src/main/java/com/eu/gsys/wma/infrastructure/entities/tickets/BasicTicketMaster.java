package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class BasicTicketMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long ticketId;
	private LocalDate date;
}
