package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.services.DepositTicketService;
import com.eu.gsys.wma.web.mappers.DepositTicketMapper;
import com.eu.gsys.wma.web.model.DepositTicketModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DepositTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private DepositTicketService depositTicketService;
	private Logger log = Logger.getLogger(DepositTicketLoader.class);

	@Autowired
	public void setDepositTicketService(DepositTicketService depositTicketService) {
		this.depositTicketService = depositTicketService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		DepositTicketModel depositTicket0 = new DepositTicketModel();

		depositTicket0.setAddress("str Palas");
		depositTicket0.setClientId("1890210226682");
		depositTicket0.setClientName("Lesseter Gregory");
		depositTicket0.setTicketId(100L);
		depositTicket0.setWheatQtyForDeposit(2000.0);
		depositTicket0.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(DepositTicketMapper.toDepositTicketFromModel(depositTicket0));

		log.info("Saved DepositTicket - id: " + depositTicket0.getTicketId());

		DepositTicketModel depositTicket1 = new DepositTicketModel();

		depositTicket1.setAddress("str Copou");
		depositTicket1.setClientName("SC Exemplu SRL");
		depositTicket1.setCompanyClientUIC("RO312168");
		depositTicket1.setTicketId(101L);
		depositTicket1.setWheatQtyForDeposit(1000.0);
		depositTicket1.setDate(LocalDate.now());
		depositTicketService.saveDepositTicket(DepositTicketMapper.toDepositTicketFromModel(depositTicket1));

		log.info("Saved DepositTicket - id: " + depositTicket1.getTicketId());
	}
}
