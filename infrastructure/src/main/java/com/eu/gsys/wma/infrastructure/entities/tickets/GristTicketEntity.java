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
@Table(name = "grist_tickets")
public class GristTicketEntity extends BasicTicketMaster {

	private Integer referenceId;
	private Double wheatQtyBrought;
	private Double tollWheatQty;    // uium
	private Double wheatQtyForGrist;
	private Double flourQtyForClient;
	private Double branQtyForClient; // tarate
	private Double otherCorpusQty;    //corpuri straine
	private Double manufacturingLossesQty;    // pierderi fabricatie

	@ManyToOne
	@JoinColumn(name = "individual_client_id")
	private IndividualClientEntity individualClientEntity;

	@ManyToOne
	@JoinColumn(name = "company_client_id")
	private CompanyClientEntity companyClientEntity;
}
