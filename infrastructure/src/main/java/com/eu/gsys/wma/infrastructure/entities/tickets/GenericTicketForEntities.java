package com.eu.gsys.wma.infrastructure.entities.tickets;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class GenericTicketForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Long ticketId;

	@NotNull
	private LocalDate date;

	@NotNull
	private int operationType;

	@ManyToOne
	@JoinColumn(name = "individual_client_id")
	private IndividualClientEntity individualClientEntity;

	@ManyToOne
	@JoinColumn(name = "company_client_id")
	private CompanyClientEntity companyClientEntity;
}
