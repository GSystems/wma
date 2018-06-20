package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GristTicketTransformer implements BaseTransformer<GristTicketEntity, GristTicket> {

    @Autowired
    ClientTransformer clientTransformer;

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
        gristTicketEntity.setTimestamp(gristTicket.getDate());
        gristTicketEntity.setClient(clientTransformer.fromModel(gristTicket.getClient()));

        return gristTicketEntity;
    }

    @Override
    public GristTicket toModel(GristTicketEntity gristTicketEntity) {
        GristTicket gristTicket = new GristTicket();

        gristTicket.setBranQtyForClient(gristTicketEntity.getBranQtyForClient());
        gristTicket.setClient(clientTransformer.toModel(gristTicketEntity.getClient()));
        gristTicket.setFlourQtyForClient(gristTicketEntity.getFlourQtyForClient());
        gristTicket.setManufacturingLossesQty(gristTicketEntity.getManufacturingLossesQty());
        gristTicket.setOtherCorpusQty(gristTicketEntity.getOtherCorpusQty());
        gristTicket.setReferenceId(gristTicketEntity.getReferenceId());
        gristTicket.setTicketId(gristTicketEntity.getTicketId());
        gristTicket.setTollWheatQty(gristTicketEntity.getTollWheatQty());
        gristTicket.setWheatQtyBrought(gristTicketEntity.getWheatQtyBrought());
        gristTicket.setWheatQtyForGrist(gristTicketEntity.getWheatQtyForGrist());
        gristTicket.setDate(gristTicketEntity.getTimestamp());

        return gristTicket;
    }
}