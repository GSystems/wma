package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.users.GenericClient;
import com.eu.gsys.wma.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final ClientService clientService;

	@Autowired
	public ClientLoader(ClientService clientService) {
		this.clientService = clientService;
	}

	public int getOrder() {
		return 1;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		GenericClient genericClient0 = new GenericClient();

		genericClient0.setAddress("str Louis");
		genericClient0.setFirstName("Lesseter");
		genericClient0.setLastName("Gregory");
		genericClient0.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(genericClient0);

		GenericClient genericClient1 = new GenericClient();

		genericClient1.setAddress("str Alca");
		genericClient1.setCompanyName("SC Example SRL");
		genericClient1.setCompanyId("RO21423");
		genericClient0.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(genericClient1);
	}
}
