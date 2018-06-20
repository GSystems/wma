package com.eu.gsys.wma.infrastructure.dao.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;

import java.util.List;

public interface GristTicketDAO {

	List<GristTicketEntity> listAllGristTickets();

	GristTicketEntity getGristTicketById(Integer id);

	GristTicketEntity saveGristTicket(GristTicketEntity gristTicket);

	void deleteGristTicket(Integer id);
}
