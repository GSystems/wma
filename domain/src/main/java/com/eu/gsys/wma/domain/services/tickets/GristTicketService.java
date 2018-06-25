package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.GristTicket;

import java.util.List;

public interface GristTicketService {

	List<GristTicket> listAllGristTickets();

	GristTicket getGristTicketById(Integer id);

	void saveGristTicket(GristTicket gristTicket);

	void deleteGristTicket(GristTicket gristTicket);

	void addNewGristTicket(GristTicket gristTicket);
}
