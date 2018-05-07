package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.services.GristTicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GristTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private GristTicketService gristTicketService;
	private Logger log = Logger.getLogger(GristTicketLoader.class);

	@Autowired
	public void setGristTicketService(GristTicketService gristTicketService) {
		this.gristTicketService = gristTicketService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		GristTicket gristTicket0 = new GristTicket();

		gristTicket0.setAddress("str Palas");
		gristTicket0.setWheatQtyBrought(100.0);
		gristTicket0.setTicketId(10L);
		gristTicket0.setClientId("1890210226682");
		gristTicket0.setClientName("Lesseter Gregory");
		gristTicket0.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket0);

		log.info("Saved GristTicket - id: " + gristTicket0.getTicketId());

		GristTicket gristTicket1 = new GristTicket();

		gristTicket1.setAddress("str Tataraso");
		gristTicket1.setWheatQtyBrought(1000.0);
		gristTicket1.setTicketId(11L);
		gristTicket1.setClientId("2780210226682");
		gristTicket1.setClientName("McLow Marry");
		gristTicket1.setDate(LocalDate.now());
		gristTicketService.saveGristTicket(gristTicket1);

		log.info("Saved GristTicket - id: " + gristTicket1.getTicketId());
	}
}
