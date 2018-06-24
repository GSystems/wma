package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.clients.CompanyClient;
import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GristTicketTransformer implements BaseTransformer<GristTicketEntity, GristTicket> {

	@Autowired
	GenericClientTransformer genericClientTransformer;

	// TODO refactor this code

	@Override
	public GristTicketEntity fromModel(GristTicket gristTicket) {
		GristTicketEntity gristTicketEntity = new GristTicketEntity();

		gristTicketEntity.setBranQtyForClient(gristTicket.getBranQtyForClient());
		gristTicketEntity.setFlourQtyForClient(gristTicket.getFlourQtyForClient());
		gristTicketEntity.setManufacturingLossesQty(gristTicket.getManufacturingLossesQty());
		gristTicketEntity.setOtherCorpusQty(gristTicket.getOtherCorpusQty());
		gristTicketEntity.setReferenceId(gristTicket.getReferenceId());
		gristTicketEntity.setTicketId(gristTicket.getTicketId());
		gristTicketEntity.setTollWheatQty(gristTicket.getTollWheatQty());
		gristTicketEntity.setWheatQtyBrought(gristTicket.getWheatQtyBrought());
		gristTicketEntity.setWheatQtyForGrist(gristTicket.getWheatQtyForGrist());
		gristTicketEntity.setDate(gristTicket.getDate());

		if (gristTicket.getGenericClient() instanceof IndividualClient) {
			IndividualClient individualClient = (IndividualClient) gristTicket.getGenericClient();
			IndividualClientEntity individualClientEntity = (IndividualClientEntity) genericClientTransformer.fromModel(individualClient);

			gristTicketEntity.setIndividualClientEntity(individualClientEntity);
		} else {
			CompanyClient companyClient = (CompanyClient) gristTicket.getGenericClient();
			CompanyClientEntity companyClientEntity = (CompanyClientEntity) genericClientTransformer.fromModel(companyClient);

			gristTicketEntity.setCompanyClientEntity(companyClientEntity);
		}

		return gristTicketEntity;
	}

	@Override
	public GristTicket toModel(GristTicketEntity gristTicketEntity) {
		GristTicket gristTicket = new GristTicket();
		GenericClient genericClient;

		if (gristTicketEntity.getIndividualClientEntity() != null) {
			genericClient = genericClientTransformer.toModel(gristTicketEntity.getIndividualClientEntity());
		} else {
			genericClient = genericClientTransformer.toModel(gristTicketEntity.getCompanyClientEntity());
		}

		gristTicket.setGenericClient(genericClient);
		gristTicket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
		gristTicket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
		gristTicket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
		gristTicket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
		gristTicket.setReferenceId(gristTicketEntity.getReferenceId());
		gristTicket.setTicketId(gristTicketEntity.getTicketId());
		gristTicket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
		gristTicket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
		gristTicket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());
		gristTicket.setDate(gristTicketEntity.getDate());

		return gristTicket;
	}
}