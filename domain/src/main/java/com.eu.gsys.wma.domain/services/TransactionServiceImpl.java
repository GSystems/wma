package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Transaction;
import com.eu.gsys.wma.domain.transformers.TransactionTransformer;
import com.eu.gsys.wma.infrastructure.dao.TransactionDAO;
import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;

	@Autowired
	private TransactionTransformer transactionTransformer;

	@Override
	public Iterable<Transaction> listAllTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionEntities = transactionDAO.listAllTransactions();

		for (TransactionEntity transactionEntity : transactionEntities) {
			transactions.add(transactionTransformer.toModel(transactionEntity));
		}

		return transactions;
	}

	@Override
	public Transaction getTransactionById(Integer id) {
		return transactionTransformer.toModel(transactionDAO.getTransactionById(id));
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionDAO.saveTransaction(transactionTransformer.fromModel(transaction));
	}

	@Override
	public void deleteTransaction(Integer id) {
		transactionDAO.deleteTransaction(id);
	}
}
