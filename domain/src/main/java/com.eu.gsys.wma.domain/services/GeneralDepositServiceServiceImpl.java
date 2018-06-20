package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GeneralDeposit;
import com.eu.gsys.wma.domain.transformers.GeneralDepositTransformer;
import com.eu.gsys.wma.infrastructure.dao.GeneralDepositDAO;
import com.eu.gsys.wma.infrastructure.entities.GeneralDepositEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralDepositServiceServiceImpl implements GeneralDepositService {

	@Autowired
	private GeneralDepositDAO generalDepositDAO;

	@Autowired
	private GeneralDepositTransformer generalDepositTransformer;

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
		return generalDepositTransformer.toModel(generalDepositDAO.getMostRecentRecord());
	}

}
