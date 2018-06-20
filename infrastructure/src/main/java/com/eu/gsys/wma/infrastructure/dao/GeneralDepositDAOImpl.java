package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.GeneralDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.GeneralDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class GeneralDepositDAOImpl extends BaseDAOBean<GeneralDepositEntity, Long> implements GeneralDepositDAO {

	@Autowired
	private GeneralDepositRepository generalDepositRepository;

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
		TypedQuery<GeneralDepositEntity> query = getEntityManager().createNamedQuery(
				GeneralDepositEntity.GET_MOST_RECENT_RECORD, GeneralDepositEntity.class
		);

		return query.getSingleResult();
	}
}
