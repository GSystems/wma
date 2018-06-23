package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.transformers.deposits.GeneralDepositTransformer;
import com.eu.gsys.wma.infrastructure.dao.deposits.GeneralDepositDAO;
import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralDepositServiceImpl implements GeneralDepositService {

	private final GeneralDepositDAO generalDepositDAO;
	private final GeneralDepositTransformer generalDepositTransformer;

	@Autowired
	public GeneralDepositServiceImpl(GeneralDepositDAO generalDepositDAO, GeneralDepositTransformer generalDepositTransformer) {
		this.generalDepositDAO = generalDepositDAO;
		this.generalDepositTransformer = generalDepositTransformer;
	}

	@Override
	public Iterable<GeneralDeposit> listAllRecords() {
		List<GeneralDeposit> generalDeposits = new ArrayList<>();
		List<GeneralDepositEntity> transactionEntities = generalDepositDAO.listAllRecords();

		for (GeneralDepositEntity generalDepositEntity : transactionEntities) {
			generalDeposits.add(generalDepositTransformer.toModel(generalDepositEntity));
		}

		return generalDeposits;
	}

	@Override
	public GeneralDeposit getRecordById(Integer id) {
		return generalDepositTransformer.toModel(generalDepositDAO.getRecordById(id));
	}

	@Override
	public void saveRecord(GeneralDeposit generalDeposit) {
		generalDepositDAO.saveRecord(this.generalDepositTransformer.fromModel(generalDeposit));
	}

	@Override
	public void deleteRecord(Integer id) {
		generalDepositDAO.deleteRecord(id);
	}

	@Override
	public GeneralDeposit getMostRecentRecord() {
		GeneralDeposit generalDeposit;
		GeneralDepositEntity generalDepositEntity = generalDepositDAO.getMostRecentRecord();

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
