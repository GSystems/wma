package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;

public interface GristTicketDAO {

	Iterable<GristTicketEntity> listAllGristTickets();
	GristTicketEntity getGristTicketsById(Integer id);
	GristTicketEntity saveGristTicket(GristTicketEntity gristTicket);
	void deleteGristTicket(Integer id);
}
