package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;

import java.util.List;

public interface TransactionDAO {

	List<TransactionEntity> listAllTransactions();

	TransactionEntity getTransactionById(Integer id);

	void saveTransaction(TransactionEntity client);

	void deleteTransaction(Integer id);
}
