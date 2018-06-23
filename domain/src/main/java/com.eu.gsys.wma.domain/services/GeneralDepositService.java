package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;

public interface GeneralDepositService {

	Iterable<GeneralDeposit> listAllRecords();

	GeneralDeposit getRecordById(Integer id);

	void saveRecord(GeneralDeposit generalDeposit);

	void deleteRecord(Integer id);

	GeneralDeposit getMostRecentRecord();
}
