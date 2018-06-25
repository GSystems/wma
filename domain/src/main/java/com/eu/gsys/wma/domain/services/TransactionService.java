package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Transaction;

import java.util.List;

public interface TransactionService {

	List<Transaction> listAllTransactions();

	Transaction getTransactionById(Long id);

	void saveTransaction(Transaction transaction);

	void deleteTransaction(Long id);

	Transaction getMostRecentTransaction();
}
