package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CustomGeneralDepositRepository;
import com.eu.gsys.wma.infrastructure.repositories.deposits.GeneralDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeneralDepositDAOImpl implements GeneralDepositDAO {

	private final GeneralDepositRepository generalDepositRepository;
	private final CustomGeneralDepositRepository customGeneralDepositRepository;

	@Autowired
	public GeneralDepositDAOImpl(GeneralDepositRepository generalDepositRepository, CustomGeneralDepositRepository customGeneralDepositRepository) {
		this.generalDepositRepository = generalDepositRepository;
		this.customGeneralDepositRepository = customGeneralDepositRepository;
	}

	@Override
	public List<GeneralDepositEntity> listAllRecords() {
		return (List<GeneralDepositEntity>) generalDepositRepository.findAll();
	}

	@Override
	public GeneralDepositEntity getRecordById(Integer id) {
		return generalDepositRepository.findById(id).get();
	}

	@Override
	public void saveRecord(GeneralDepositEntity generalDepositEntity) {
		generalDepositRepository.save(generalDepositEntity);
	}

	@Override
	public void deleteRecord(Integer id) {
		generalDepositRepository.deleteById(id);
	}

	@Override
	public GeneralDepositEntity getMostRecentRecord() {
		return customGeneralDepositRepository.getMostRecentRecord();
	}
}
