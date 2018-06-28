package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.Transaction;
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
	public List<Transaction> listAll() {
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
			transaction = initValuesForNewTransaction();
		} else {
			transaction = transactionTransformer.toModel(transactionEntity);
		}

		return transaction;
	}

	private Transaction initValuesForNewTransaction() {
		Transaction transaction = new Transaction();

		transaction.setBranQtyOfClients(0d);
		transaction.setBranQtyOfCompany(0d);

		transaction.setFlourQtyOfClients(0d);
		transaction.setFlourQtyOfCompany(0d);

		transaction.setTotalBranQty(0d);
		transaction.setTotalFlourQty(0d);
		transaction.setTotalWheatQty(0d);

		transaction.setWheatQtyOfClients(0d);
		transaction.setWheatQtyOfCompany(0d);

		return transaction;
	}
}
