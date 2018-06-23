package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.users.GenericClient;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.domain.services.ClientService;
import com.eu.gsys.wma.domain.services.tickets.GristTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GristTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final GristTicketService gristTicketService;
	private final ClientService clientService;

	@Autowired
	public GristTicketLoader(GristTicketService gristTicketService, ClientService clientService) {
		this.gristTicketService = gristTicketService;
		this.clientService = clientService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		GristTicket gristTicket0 = new GristTicket();

		GenericClient genericClient0 = clientService.getClientById(1);
		gristTicket0.setGenericClient(genericClient0);
		gristTicket0.setWheatQtyBrought(100.0);
		gristTicket0.setTicketId(10L);
		gristTicket0.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket0);

		System.out.println("Saved GristTicket - id: " + gristTicket0.getTicketId());

		GristTicket gristTicket1 = new GristTicket();

		GenericClient genericClient1 = clientService.getClientById(2);
		gristTicket1.setGenericClient(genericClient1);
		gristTicket1.setWheatQtyBrought(1000.0);
		gristTicket1.setTicketId(11L);
		gristTicket1.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket1);

		System.out.println("Saved GristTicket - id: " + gristTicket1.getTicketId());
	}
}
