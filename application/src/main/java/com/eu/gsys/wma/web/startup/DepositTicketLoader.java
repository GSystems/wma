package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.services.DepositTicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DepositTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DepositTicketService depositTicketService;
	private Logger log = Logger.getLogger(DepositTicketLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DepositTicket depositTicket0 = new DepositTicket();
		Client client0 = new Client();

		client0.setAddress("str Louis");
		client0.setClientId("1890210226682");
		client0.setClientName("Lesseter Gregory");

		depositTicket0.setClient(client0);
		depositTicket0.setTicketId(100L);
		depositTicket0.setWheatQtyForDeposit(2000.0);
		depositTicket0.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket0);

		log.info("Saved DepositTicket - id: " + depositTicket0.getTicketId());

		DepositTicket depositTicket1 = new DepositTicket();
		Client client1 = new Client();

		client1.setAddress("str Alca");
		client1.setClientName("SC Example SRL");
		client1.setClientId("RO21423");

		depositTicket1.setClient(client1);
		depositTicket1.setTicketId(101L);
		depositTicket1.setWheatQtyForDeposit(1000.0);
		depositTicket1.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(depositTicket1);

		log.info("Saved DepositTicket - id: " + depositTicket1.getTicketId());
	}
}
