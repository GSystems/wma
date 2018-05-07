package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GristTicket;

public interface GristTicketService {

	Iterable<GristTicket> listAllGristTickets();
	GristTicket getGristTicketsById(Integer id);
	GristTicket saveGristTicket(GristTicket gristTicket);
	void deleteGristTicket(Integer id);
}
