package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Transaction;

public interface TransactionService {

	Iterable<Transaction> listAllTransactions();

	Transaction getTransactionById(Integer id);

	void saveTransaction(Transaction transaction);

	void deleteTransaction(Integer id);
}
