package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.models.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.services.tickets.WithdrawTicketService;
import com.eu.gsys.wma.domain.util.WmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WithdrawTicketLoader implements ApplicationListener<ContextRefreshedEvent> {

	private WithdrawTicketService withdrawTicketService;

	@Autowired
	public WithdrawTicketLoader(WithdrawTicketService withdrawTicketService) {
		this.withdrawTicketService = withdrawTicketService;
	}

	public int getOrder() {
		return 4;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		WithdrawTicket ticket = new WithdrawTicket();

		ticket.setReferenceTicketNumber(1L);
		ticket.setWheatQtyWithdrawn(133d);
		ticket.setDate(LocalDate.now());
		ticket.setTicketNumber(27L);

		try {
			withdrawTicketService.grindAndWithdrawFromDeposit(ticket, true);
			ticket.setComment("Wrong input");
			withdrawTicketService.removeWithdrawTicket(ticket);
		} catch (WmaException e) {
			e.printStackTrace();
		}
	}
}
