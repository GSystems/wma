package com.eu.gsys.wma.domain.services.clients;

import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.transformers.GenericClientTransformer;
import com.eu.gsys.wma.infrastructure.dao.clients.ClientDAO;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("individualClientService")
public class IndividualClientServiceImpl implements ClientService {

	private final GenericClientTransformer genericClientTransformer;
	private final ClientDAO clientDAO;

	@Autowired
	public IndividualClientServiceImpl(GenericClientTransformer genericClientTransformer,
	                                   @Qualifier("individualClientDAO") ClientDAO clientDAO) {

		this.genericClientTransformer = genericClientTransformer;
		this.clientDAO = clientDAO;
	}

	@Override
	public List<IndividualClient> listAllClients() {
		List<IndividualClient> companyClients = new ArrayList<>();
		List<IndividualClientEntity> clientEntities = clientDAO.listAllClients();

		for (IndividualClientEntity individualClientEntity : clientEntities) {
			companyClients.add((IndividualClient) genericClientTransformer.toModel(individualClientEntity));
		}

		return companyClients;
	}

	@Override
	public IndividualClient getClientById(Integer id) {
		IndividualClientEntity individualClientEntity = (IndividualClientEntity) clientDAO.getClientById(id);
		return (IndividualClient) genericClientTransformer.toModel(individualClientEntity);
	}

	@Override
	public void saveClientTicket(Object o) {
		IndividualClient individualClient = (IndividualClient) o;
		clientDAO.saveClient(genericClientTransformer.fromModel(individualClient));
	}

	@Override
	public void deleteClientTicket(Integer id) {
		clientDAO.deleteClient(id);
	}
}
