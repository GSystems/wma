package com.eu.gsys.wma.infrastructure.dao.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;

import java.util.List;

public interface DepositTicketDAO {

	List<DepositTicketEntity> listAllDepositTickets();

	DepositTicketEntity getDepositTicketById(Integer id);

	DepositTicketEntity saveDepositTicket(DepositTicketEntity gristTicket);

	void deleteDepositTicket(Integer id);
}
