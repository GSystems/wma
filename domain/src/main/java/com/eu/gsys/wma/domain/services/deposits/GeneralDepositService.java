package com.eu.gsys.wma.domain.services.deposits;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;

public interface GeneralDepositService {

	Iterable<GeneralDeposit> listAllRecords();

	GeneralDeposit getRecordById(Long id);

	void saveRecord(GeneralDeposit generalDeposit);

	void deleteRecord(Long id);

	GeneralDeposit getMostRecentRecord();
}
