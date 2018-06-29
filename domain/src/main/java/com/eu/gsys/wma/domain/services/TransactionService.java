package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Transaction;

import java.util.List;

public interface TransactionService {

	List<Transaction> findAll();

	Transaction findById(Long id);

	void save(Transaction transaction);

	void deleteById(Long id);

	Transaction getMostRecentTransaction();
}
