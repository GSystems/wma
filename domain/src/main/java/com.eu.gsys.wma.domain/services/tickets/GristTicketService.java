package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.GristTicket;

public interface GristTicketService {

	Iterable<GristTicket> listAllGristTickets();
	GristTicket getGristTicketById(Integer id);
	void saveGristTicket(GristTicket gristTicket);
	void deleteGristTicket(Integer id);
}
