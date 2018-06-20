package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ClientService clientService;

	public int getOrder() {
		return 1;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		Client client0 = new Client();

		client0.setAddress("str Louis");
		client0.setClientId(1890210226682L);
		client0.setFirstName("Lesseter");
		client0.setLastName("Gregory");
		client0.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(client0);

		Client client1 = new Client();

		client1.setAddress("str Alca");
		client1.setCompanyName("SC Example SRL");
		client1.setCompanyId("RO21423");
		client0.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(client1);
	}
}
