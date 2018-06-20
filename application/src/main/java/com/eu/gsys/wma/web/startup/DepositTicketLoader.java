package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.services.ClientService;
import com.eu.gsys.wma.domain.services.DepositTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DepositTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DepositTicketService depositTicketService;

	@Autowired
	private ClientService clientService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DepositTicket depositTicket0 = new DepositTicket();

		Client client0 = clientService.getClientById(1);
		depositTicket0.setClient(client0);
		depositTicket0.setTicketId(100L);
		depositTicket0.setWheatQtyForDeposit(2000.0);
		depositTicket0.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket0);

		System.out.println("Saved DepositTicket - id: " + depositTicket0.getTicketId());

		DepositTicket depositTicket1 = new DepositTicket();

		Client client1 = clientService.getClientById(2);
		depositTicket1.setClient(client1);
		depositTicket1.setTicketId(101L);
		depositTicket1.setWheatQtyForDeposit(1000.0);
		depositTicket1.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket1);

		System.out.println("Saved DepositTicket - id: " + depositTicket1.getTicketId());
	}
}
