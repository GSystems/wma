package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.DepositTransformer;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CompanyDepositRepository;
import com.eu.gsys.wma.infrastructure.repositories.deposits.IndividualDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDepositServiceImpl implements ClientDepositService {

	private final ClientTransformer clientTransformer;
	private final CompanyDepositRepository companyDepositRepository;
	private final DepositTransformer depositTransformer;
	private final IndividualDepositRepository individualDepositRepository;

	@Autowired
	public ClientDepositServiceImpl(ClientTransformer clientTransformer,
			CompanyDepositRepository companyDepositRepository, DepositTransformer depositTransformer,
			IndividualDepositRepository individualDepositRepository) {
		this.clientTransformer = clientTransformer;
		this.companyDepositRepository = companyDepositRepository;
		this.depositTransformer = depositTransformer;
		this.individualDepositRepository = individualDepositRepository;
	}

	@Override
	public void save(GenericDeposit deposit) {

		// TODO refactor this code

		if (deposit instanceof IndividualClientDeposit) {
			individualDepositRepository.save((IndividualClientDepositEntity) depositTransformer.fromModel(deposit));
		} else {
			companyDepositRepository.save((CompanyClientDepositEntity) depositTransformer.fromModel(deposit));
		}
	}

	@Override
	public GenericDeposit findByClient(GenericClient client) {

		// TODO refactor this code

		if (client instanceof IndividualClient) {
			IndividualClientDepositEntity depositEntity =
					individualDepositRepository.findDepositByClientEntity((IndividualClientEntity) clientTransformer.fromModel(client));

			return depositTransformer.toModel(depositEntity);

		} else {
			CompanyClientDepositEntity depositEntity =
					companyDepositRepository.getDepositByClientEntity((CompanyClientEntity) clientTransformer.fromModel(client));

			return depositTransformer.toModel(depositEntity);
		}
	}

	@Override
	public GenericDeposit findByTicketNumber(Long ticketNumber) {

		// TODO see company scenario

		GenericDepositForEntities depositEntity = individualDepositRepository.findDepositByTicketNumber(ticketNumber);

		return depositTransformer.toModel(depositEntity);
	}
}
