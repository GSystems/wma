package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.models.Transaction;
import com.eu.gsys.wma.domain.transformers.TransactionTransformer;
import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import com.eu.gsys.wma.infrastructure.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final TransactionTransformer transactionTransformer;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			TransactionTransformer transactionTransformer) {

		this.transactionRepository = transactionRepository;
		this.transactionTransformer = transactionTransformer;
	}

	@Override
	public List<Transaction> findAll() {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionEntities = transactionRepository.findAll();

		for (TransactionEntity transactionEntity : transactionEntities) {
			transactions.add(transactionTransformer.toModel(transactionEntity));
		}

		return transactions;
	}

	@Override
	public Transaction findById(Long id) {
		if (transactionRepository.findById(id).isPresent()) {
			return transactionTransformer.toModel(transactionRepository.findById(id).get());
		}

		return null;
	}

	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transactionTransformer.fromModel(transaction));
	}

	@Override
	public void deleteById(Long id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public Transaction getMostRecentTransaction() {
		Transaction transaction;
		TransactionEntity transactionEntity = transactionRepository.getMostRecentTransaction();

		if (transactionEntity == null) {
			transaction = new Transaction();
		} else {
			transaction = transactionTransformer.toModel(transactionEntity);
		}

		return transaction;
	}
}
