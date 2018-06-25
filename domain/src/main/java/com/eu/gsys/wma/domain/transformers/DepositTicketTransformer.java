package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositTicketTransformer implements BaseTransformer<DepositTicketEntity, DepositTicket> {

	private final GenericClientTransformer genericClientTransformer;

	@Autowired
	public DepositTicketTransformer(GenericClientTransformer genericClientTransformer) {
		this.genericClientTransformer = genericClientTransformer;
	}

	// TODO refactor this code

	@Override
	public DepositTicketEntity fromModel(DepositTicket depositTicket) {
		DepositTicketEntity depositTicketEntity = new DepositTicketEntity();

		if (depositTicket.getGenericClient() instanceof IndividualClient) {
			IndividualClientEntity individualClientEntity =
					(IndividualClientEntity) genericClientTransformer.fromModel(depositTicket.getGenericClient());

			depositTicketEntity.setIndividualClientEntity(individualClientEntity);
		} else {
			CompanyClientEntity companyClientEntity =
					(CompanyClientEntity) genericClientTransformer.fromModel(depositTicket.getGenericClient());

			depositTicketEntity.setCompanyClientEntity(companyClientEntity);
		}

		depositTicketEntity.setId(depositTicket.getId());
		depositTicketEntity.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());
		depositTicketEntity.setDate(depositTicket.getDate());
		depositTicketEntity.setTicketId(depositTicket.getTicketId());
		if (depositTicket.getConsumedFlag()) {
			depositTicketEntity.setConsumedFlag(1);
		}

		return depositTicketEntity;
	}

	@Override
	public DepositTicket toModel(DepositTicketEntity depositTicketEntity) {
		DepositTicket depositTicket = new DepositTicket();
		GenericClient genericClient;

		if (depositTicketEntity.getIndividualClientEntity() != null) {
			genericClient = genericClientTransformer.toModel(depositTicketEntity.getIndividualClientEntity());
		} else {
			genericClient = genericClientTransformer.toModel(depositTicketEntity.getCompanyClientEntity());
		}

		depositTicket.setGenericClient(genericClient);
		depositTicket.setId(depositTicketEntity.getId());
		depositTicket.setWheatQtyForDeposit(depositTicketEntity.getWheatQtyForDeposit());
		depositTicket.setDate(depositTicketEntity.getDate());
		depositTicket.setTicketId(depositTicketEntity.getTicketId());
		if (depositTicketEntity.getConsumedFlag() == 1) {
			depositTicket.setConsumedFlag(true);
		}

		return depositTicket;
	}
}
