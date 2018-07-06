package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.clients.ClientService;
import com.eu.gsys.wma.domain.services.tickets.DepositTicketService;
import com.eu.gsys.wma.domain.util.WmaException;
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

	public int getOrder() {
		return 2;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		addDepositTickets();
		removeDepositTicket();
	}

	private void addDepositTickets() {
		DepositTicket depositTicket0 = new DepositTicket();

		GenericClient genericClient0 = clientService.findById(1L);
		depositTicket0.setClient(genericClient0);
		depositTicket0.setTicketNumber(1L);
		depositTicket0.setWheatQty(2000.0);
		depositTicket0.setDate(LocalDate.now());

		DepositTicket depositTicket1 = new DepositTicket();

		GenericClient genericClient1 = clientService.findById(2L);
		depositTicket1.setClient(genericClient1);
		depositTicket1.setTicketNumber(10L);
		depositTicket1.setWheatQty(1000.0);
		depositTicket1.setDate(LocalDate.now());

		DepositTicket depositTicket2 = new DepositTicket();

		GenericClient genericClient2 = clientService.findById(2L);
		depositTicket2.setClient(genericClient2);
		depositTicket2.setTicketNumber(12L);
		depositTicket2.setWheatQty(555.0);
		depositTicket2.setDate(LocalDate.now());

		DepositTicket depositTicket3 = new DepositTicket();

		GenericClient genericClient3 = clientService.findById(1L);
		depositTicket3.setClient(genericClient3);
		depositTicket3.setTicketNumber(9L);
		depositTicket3.setWheatQty(1200.0);
		depositTicket3.setDate(LocalDate.now());

		try {
			depositTicketService.addNew(depositTicket0);
			depositTicketService.addNew(depositTicket1);
			depositTicketService.addNew(depositTicket2);
			depositTicketService.addNew(depositTicket3);
		} catch (WmaException e) {
			e.printStackTrace();
		}
	}

	private void removeDepositTicket() {
		DepositTicket depositTicket = depositTicketService.findByTicketNumber(1L);
		depositTicket.setComment("Wrong qty");

		try {
			depositTicketService.deleteByDepositTicket(depositTicket);
		} catch (WmaException e) {
			e.printStackTrace();
		}
	}
}
