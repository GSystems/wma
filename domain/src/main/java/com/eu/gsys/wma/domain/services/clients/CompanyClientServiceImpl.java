package com.eu.gsys.wma.domain.services.clients;

import com.eu.gsys.wma.domain.model.clients.CompanyClient;
import com.eu.gsys.wma.domain.transformers.GenericClientTransformer;
import com.eu.gsys.wma.infrastructure.dao.clients.ClientDAO;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("companyClientService")
public class CompanyClientServiceImpl implements ClientService {

	private final GenericClientTransformer genericClientTransformer;
	private final ClientDAO clientDAO;

	@Autowired
	public CompanyClientServiceImpl(GenericClientTransformer genericClientTransformer,
	                                @Qualifier("companyClientDAO") ClientDAO clientDAO) {

		this.genericClientTransformer = genericClientTransformer;
		this.clientDAO = clientDAO;
	}

	@Override
	public List<CompanyClient> listAllClients() {
		List<CompanyClient> companyClients = new ArrayList<>();
		List<CompanyClientEntity> clientEntities = clientDAO.listAllClients();

		for (CompanyClientEntity companyClientEntity : clientEntities) {
			companyClients.add((CompanyClient) genericClientTransformer.toModel(companyClientEntity));
		}

		return companyClients;
	}

	@Override
	public CompanyClient getClientById(Integer id) {
		CompanyClientEntity companyClientEntity = (CompanyClientEntity) clientDAO.getClientById(id);
		return (CompanyClient) genericClientTransformer.toModel(companyClientEntity);
	}

	@Override
	public void saveClientTicket(Object o) {
		CompanyClient companyClient = (CompanyClient) o;
		clientDAO.saveClient(genericClientTransformer.fromModel(companyClient));
	}

	@Override
	public void deleteClientTicket(Integer id) {
		clientDAO.deleteClient(id);
	}
}
