package com.eu.gsys.wma.infrastructure.entities.tickets;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "grist_tickets")
public class GristTicketEntity extends BasicTicketMaster {

	private Double wheatQtyBrought = 0d;
	private Double tollWheatQty = 0d;    // uium
	private Double wheatQtyForGrist = 0d;
	private Double flourQtyForClient = 0d;
	private Double branQtyForClient = 0d; // tarate
	private Double otherCorpusQty = 0d;    //corpuri straine
	private Double manufacturingLossesQty = 0d;    // pierderi fabricatie

	@ManyToOne
	@JoinColumn(name = "individual_client_id")
	private IndividualClientEntity individualClientEntity;

	@ManyToOne
	@JoinColumn(name = "company_client_id")
	private CompanyClientEntity companyClientEntity;
}
