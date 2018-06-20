package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import com.eu.gsys.wma.infrastructure.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<TransactionEntity> listAllTransactions() {
		return (List<TransactionEntity>) transactionRepository.findAll();
	}

	@Override
	public TransactionEntity getTransactionById(Integer id) {
		return transactionRepository.findById(id).get();
	}

	@Override
	public void saveTransaction(TransactionEntity transactionEntity) {
		transactionRepository.save(transactionEntity);
	}

	@Override
	public void deleteTransaction(Integer id) {
		transactionRepository.deleteById(id);
	}
}
