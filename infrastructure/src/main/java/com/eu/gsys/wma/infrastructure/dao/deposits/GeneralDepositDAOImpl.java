package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.GeneralDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeneralDepositDAOImpl implements GeneralDepositDAO {

	private final GeneralDepositRepository generalDepositRepository;

	@Autowired
	public GeneralDepositDAOImpl(GeneralDepositRepository generalDepositRepository) {
		this.generalDepositRepository = generalDepositRepository;
	}

	@Override
	public List<GeneralDepositEntity> listAllRecords() {
		return generalDepositRepository.findAll();
	}

	@Override
	public GeneralDepositEntity getRecordById(Integer id) {
		return generalDepositRepository.findById(Long.valueOf(id)).get();
	}

	@Override
	public void saveRecord(GeneralDepositEntity generalDepositEntity) {
		generalDepositRepository.save(generalDepositEntity);
	}

	@Override
	public void deleteRecord(Integer id) {
		generalDepositRepository.deleteById(Long.valueOf(id));
	}

	@Override
	public GeneralDepositEntity getMostRecentRecord() {
		return generalDepositRepository.getMostRecentRecord();
	}
}
