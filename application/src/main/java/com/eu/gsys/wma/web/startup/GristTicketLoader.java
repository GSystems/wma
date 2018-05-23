package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.services.GristTicketService;
import com.eu.gsys.wma.infrastructure.dao.GristTicketDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GristTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private GristTicketService gristTicketService;
	private Logger log = Logger.getLogger(GristTicketLoader.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		GristTicket gristTicket0 = new GristTicket();
		Client client0 = new Client();

		client0.setAddress("str Palas");
		client0.setClientId("1890210226682");
		client0.setClientName("Lesseter Gregory");

		gristTicket0.setClient(client0);
		gristTicket0.setWheatQtyBrought(100.0);
		gristTicket0.setTicketId(10L);
		gristTicket0.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket0);

		log.info("Saved GristTicket - id: " + gristTicket0.getTicketId());

		GristTicket gristTicket1 = new GristTicket();
		Client client1 = new Client();

		client1.setAddress("str Tataraso");
		client1.setClientId("2780210226682");
		client1.setClientName("McLow Marry");

		gristTicket1.setClient(client1);
		gristTicket1.setWheatQtyBrought(1000.0);
		gristTicket1.setTicketId(11L);
		gristTicket1.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket1);

		log.info("Saved GristTicket - id: " + gristTicket1.getTicketId());
	}
}
