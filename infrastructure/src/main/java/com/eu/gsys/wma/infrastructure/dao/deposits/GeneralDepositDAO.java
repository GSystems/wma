package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;

import java.util.List;

public interface GeneralDepositDAO {

	List<GeneralDepositEntity> listAllRecords();

	GeneralDepositEntity getRecordById(Integer id);

	void saveRecord(GeneralDepositEntity client);

	void deleteRecord(Integer id);

	GeneralDepositEntity getMostRecentRecord();
}
