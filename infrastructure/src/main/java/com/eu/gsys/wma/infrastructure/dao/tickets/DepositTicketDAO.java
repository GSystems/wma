package com.eu.gsys.wma.infrastructure.dao.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;

import java.util.List;

public interface DepositTicketDAO {

	List<DepositTicketEntity> listAllDepositTickets();

	DepositTicketEntity getDepositTicketById(Integer id);

	void saveDepositTicket(DepositTicketEntity gristTicket);

	void deleteDepositTicket(DepositTicketEntity depositTicketEntity);
}
