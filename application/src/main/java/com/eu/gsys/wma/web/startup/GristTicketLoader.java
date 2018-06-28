package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.domain.services.clients.ClientService;
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

		GenericClient genericClient0 = (GenericClient) clientService.findById(1L);
		gristTicket0.setClient(genericClient0);
		gristTicket0.setWheatQtyBrought(100.0);
		gristTicket0.setTicketNumber(10L);
		gristTicket0.setDate(LocalDate.now());
		gristTicketService.addNew(gristTicket0);

		System.out.println("Saved GristTicket - id: " + gristTicket0.getTicketNumber());

		GristTicket gristTicket1 = new GristTicket();

		GenericClient genericClient1 = (GenericClient) clientService.findById(2L);
		gristTicket1.setClient(genericClient1);
		gristTicket1.setWheatQtyBrought(1000.0);
		gristTicket1.setTicketNumber(11L);
		gristTicket1.setDate(LocalDate.now());
		gristTicketService.addNew(gristTicket1);

		System.out.println("Saved GristTicket - id: " + gristTicket1.getTicketNumber());
	}
}
