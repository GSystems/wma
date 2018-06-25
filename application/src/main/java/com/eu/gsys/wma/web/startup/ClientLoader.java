package com.eu.gsys.wma.web.startup;

import com.eu.gsys.wma.domain.model.clients.CompanyClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.services.clients.ClientService;
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
		IndividualClient individualClient = new IndividualClient();

		individualClient.setAddress("str Louis");
		individualClient.setFirstName("Lesseter");
		individualClient.setLastName("Gregory");
		individualClient.setPersonalId("1761234123123");
		individualClient.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(individualClient);

		CompanyClient companyClient = new CompanyClient();

		companyClient.setAddress("str Alca");
		companyClient.setCompanyName("SC Example SRL");
		companyClient.setCompanyId("RO21423");
		companyClient.setJoinDate(LocalDate.now());

		clientService.saveClientTicket(companyClient);
	}
}
