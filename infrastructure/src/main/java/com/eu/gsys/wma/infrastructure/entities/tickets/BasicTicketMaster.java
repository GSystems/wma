package com.eu.gsys.wma.infrastructure.entities.tickets;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class BasicTicketMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long ticketId;

	@ManyToOne
	private ClientEntity client;
	private LocalDate timestamp;
}
