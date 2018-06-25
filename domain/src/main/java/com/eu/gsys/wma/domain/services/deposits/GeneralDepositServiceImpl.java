package com.eu.gsys.wma.domain.services.deposits;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.transformers.GeneralDepositTransformer;
import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.GeneralDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralDepositServiceImpl implements GeneralDepositService {

	private final GeneralDepositRepository generalDepositRepository;
	private final GeneralDepositTransformer generalDepositTransformer;

	@Autowired
	public GeneralDepositServiceImpl(GeneralDepositRepository generalDepositRepository,
			GeneralDepositTransformer generalDepositTransformer) {

		this.generalDepositRepository = generalDepositRepository;
		this.generalDepositTransformer = generalDepositTransformer;
	}

	@Override
	public Iterable<GeneralDeposit> listAllRecords() {
		List<GeneralDeposit> generalDeposits = new ArrayList<>();
		List<GeneralDepositEntity> transactionEntities = generalDepositRepository.findAll();

		for (GeneralDepositEntity generalDepositEntity : transactionEntities) {
			generalDeposits.add(generalDepositTransformer.toModel(generalDepositEntity));
		}

		return generalDeposits;
	}

	@Override
	public GeneralDeposit getRecordById(Long id) {
		return generalDepositTransformer.toModel(generalDepositRepository.findById(id).get());
	}

	@Override
	public void saveRecord(GeneralDeposit generalDeposit) {
		generalDepositRepository.save(generalDepositTransformer.fromModel(generalDeposit));
	}

	@Override
	public void deleteRecord(Long id) {
		generalDepositRepository.deleteById(id);
	}

	@Override
	public GeneralDeposit getMostRecentRecord() {
		GeneralDeposit generalDeposit;
		GeneralDepositEntity generalDepositEntity = generalDepositRepository.getMostRecentRecord();

		if (generalDepositEntity == null) {
			generalDeposit = initValuesForNewGeneralDeposit();
		} else {
			generalDeposit = generalDepositTransformer.toModel(generalDepositEntity);
		}

		return generalDeposit;
	}

	private GeneralDeposit initValuesForNewGeneralDeposit() {
		GeneralDeposit generalDeposit = new GeneralDeposit();

		generalDeposit.setBranQtyOfClients(Double.valueOf(0));
		generalDeposit.setBranQtyOfCompany(Double.valueOf(0));

		generalDeposit.setFlourQtyOfClients(Double.valueOf(0));
		generalDeposit.setFlourQtyOfCompany(Double.valueOf(0));

		generalDeposit.setTotalBranQty(Double.valueOf(0));
		generalDeposit.setTotalFlourQty(Double.valueOf(0));
		generalDeposit.setTotalWheatQty(Double.valueOf(0));

		generalDeposit.setWheatQtyOfClients(Double.valueOf(0));
		generalDeposit.setWheatQtyOfCompany(Double.valueOf(0));

		return generalDeposit;
	}
}
