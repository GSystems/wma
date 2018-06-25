package com.eu.gsys.wma.infrastructure.entities.tickets;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "deposit_tickets")
public class DepositTicketEntity extends BasicTicketMaster {

	private Double wheatQtyForDeposit;
	private int consumedFlag = 0;

	@ManyToOne
	@JoinColumn(name = "individual_client_id")
	private IndividualClientEntity individualClientEntity;

	@ManyToOne
	@JoinColumn(name = "company_client_id")
	private CompanyClientEntity companyClientEntity;
}
