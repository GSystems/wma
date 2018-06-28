package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.GristTicket;

import java.util.List;

public interface GristTicketService {

	List<GristTicket> listAll();

	GristTicket findById(Long id);

	void save(GristTicket gristTicket);

	void deleteByGristTicket(GristTicket gristTicket);

	void addNew(GristTicket gristTicket);
}
