package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.clients.ClientService;
import com.eu.gsys.wma.domain.services.tickets.DepositTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DepositTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final DepositTicketService depositTicketService;

	private final ClientService clientService;

	@Autowired
	public DepositTicketLoader(DepositTicketService depositTicketService,
			ClientService clientService) {

		this.depositTicketService = depositTicketService;
		this.clientService = clientService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DepositTicket depositTicket0 = new DepositTicket();

		GenericClient genericClient0 = (GenericClient) clientService.getClientById(1);
		depositTicket0.setClient(genericClient0);
		depositTicket0.setTicketId(1L);
		depositTicket0.setWheatQtyForDeposit(2000.0);
		depositTicket0.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket0);

		System.out.println("Saved DepositTicket - id: " + depositTicket0.getTicketId());

		DepositTicket depositTicket1 = new DepositTicket();

		GenericClient genericClient1 = (GenericClient) clientService.getClientById(2);
		depositTicket1.setClient(genericClient1);
		depositTicket1.setTicketId(10L);
		depositTicket1.setWheatQtyForDeposit(1000.0);
		depositTicket1.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket1);

		System.out.println("Saved DepositTicket - id: " + depositTicket1.getTicketId());

		DepositTicket depositTicket2 = new DepositTicket();

		GenericClient genericClient2 = (GenericClient) clientService.getClientById(2);
		depositTicket2.setClient(genericClient2);
		depositTicket2.setTicketId(12L);
		depositTicket2.setWheatQtyForDeposit(555.0);
		depositTicket2.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket2);

		System.out.println("Saved DepositTicket - id: " + depositTicket2.getTicketId());

		DepositTicket depositTicket3 = new DepositTicket();

		GenericClient genericClient3 = (GenericClient) clientService.getClientById(1);
		depositTicket3.setClient(genericClient3);
		depositTicket3.setTicketId(9L);
		depositTicket3.setWheatQtyForDeposit(1200.0);
		depositTicket3.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket3);

		System.out.println("Saved DepositTicket - id: " + depositTicket3.getTicketId());

	}
}
