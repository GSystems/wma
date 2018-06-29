package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.clients.GenericClient;
import com.eu.gsys.wma.domain.model.clients.IndividualClient;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.DepositTransformer;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CompanyDepositRepository;
import com.eu.gsys.wma.infrastructure.repositories.deposits.IndividualDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {

	private final ClientTransformer clientTransformer;
	private final CompanyDepositRepository companyDepositRepository;
	private final DepositTransformer depositTransformer;
	private final IndividualDepositRepository individualDepositRepository;

	@Autowired
	public DepositServiceImpl(ClientTransformer clientTransformer,
			CompanyDepositRepository companyDepositRepository, DepositTransformer depositTransformer,
			IndividualDepositRepository individualDepositRepository) {
		this.clientTransformer = clientTransformer;
		this.companyDepositRepository = companyDepositRepository;
		this.depositTransformer = depositTransformer;
		this.individualDepositRepository = individualDepositRepository;
	}


	@Override
	public GenericDeposit findByClient(GenericClient client) {

		// TODO refactor this code

		if (client instanceof IndividualClient) {
			IndividualClientDepositEntity depositEntity =
					individualDepositRepository.getDepositByClient((IndividualClientEntity) clientTransformer.fromModel(client));

			return depositTransformer.toModel(depositEntity);

		} else {
			CompanyClientDepositEntity depositEntity =
					companyDepositRepository.getDepositByClientEntity((CompanyClientEntity) clientTransformer.fromModel(client));

			return depositTransformer.toModel(depositEntity);
		}
	}
}
