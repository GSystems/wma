package com.eu.gsys.wma.domain.transformers.tickets;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.transformers.BaseTransformer;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositTicketTransformer implements BaseTransformer<DepositTicketEntity, DepositTicket> {

    @Autowired
	ClientTransformer clientTransformer;

    @Override
    public DepositTicketEntity fromModel(DepositTicket depositTicket) {
        DepositTicketEntity depositTicketEntity = new DepositTicketEntity();

        depositTicketEntity.setClient(clientTransformer.fromModel(depositTicket.getClient()));

        depositTicketEntity.setWheatQtyForDeposit(depositTicket.getWheatQtyForDeposit());
        depositTicketEntity.setTimestamp(depositTicket.getDate());
        depositTicketEntity.setTicketId(depositTicket.getTicketId());
        depositTicketEntity.setConsumedFlag(depositTicket.getConsumedFlag());

        return depositTicketEntity;
    }

    @Override
    public DepositTicket toModel(DepositTicketEntity depositTicketEntity) {
        DepositTicket depositTicket = new DepositTicket();

        depositTicket.setClient(clientTransformer.toModel(depositTicketEntity.getClient()));
        depositTicket.setWheatQtyForDeposit(depositTicketEntity.getWheatQtyForDeposit());
        depositTicket.setDate(depositTicketEntity.getTimestamp());
        depositTicket.setTicketId(depositTicketEntity.getTicketId());
        depositTicket.setConsumedFlag(depositTicketEntity.getConsumedFlag());

        return depositTicket;
    }
}
