package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.clients.ClientService;
import com.eu.gsys.wma.domain.services.tickets.DepositTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DepositTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final DepositTicketService depositTicketService;

	private final ClientService individualClientService;
	private final ClientService companyClientService;

	@Autowired
	public DepositTicketLoader(DepositTicketService depositTicketService,
	                           @Qualifier("individualClientService") ClientService individualClientService,
	                           @Qualifier("companyClientService") ClientService companyClientService) {
		this.depositTicketService = depositTicketService;
		this.individualClientService = individualClientService;
		this.companyClientService = companyClientService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DepositTicket depositTicket0 = new DepositTicket();

		GenericClient genericClient0 = (GenericClient) individualClientService.getClientById(1);
		depositTicket0.setGenericClient(genericClient0);
		depositTicket0.setTicketId(1L);
		depositTicket0.setWheatQtyForDeposit(2000.0);
		depositTicket0.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket0);

		System.out.println("Saved DepositTicket - id: " + depositTicket0.getTicketId());

		DepositTicket depositTicket1 = new DepositTicket();

		GenericClient genericClient1 = (GenericClient) companyClientService.getClientById(2);
		depositTicket1.setGenericClient(genericClient1);
		depositTicket1.setTicketId(1L);
		depositTicket1.setWheatQtyForDeposit(1000.0);
		depositTicket1.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket1);

		System.out.println("Saved DepositTicket - id: " + depositTicket1.getTicketId());
	}
}
