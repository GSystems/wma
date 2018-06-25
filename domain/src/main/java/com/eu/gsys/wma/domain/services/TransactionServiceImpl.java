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
	public List<Transaction> listAllTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionEntities = transactionRepository.findAll();

		for (TransactionEntity transactionEntity : transactionEntities) {
			transactions.add(transactionTransformer.toModel(transactionEntity));
		}

		return transactions;
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return transactionTransformer.toModel(transactionRepository.findById(id).get());
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transactionTransformer.fromModel(transaction));
	}

	@Override
	public void deleteTransaction(Long id) {
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

		transaction.setBranQtyOfClients(Double.valueOf(0));
		transaction.setBranQtyOfCompany(Double.valueOf(0));

		transaction.setFlourQtyOfClients(Double.valueOf(0));
		transaction.setFlourQtyOfCompany(Double.valueOf(0));

		transaction.setTotalBranQty(Double.valueOf(0));
		transaction.setTotalFlourQty(Double.valueOf(0));
		transaction.setTotalWheatQty(Double.valueOf(0));

		transaction.setWheatQtyOfClients(Double.valueOf(0));
		transaction.setWheatQtyOfCompany(Double.valueOf(0));

		return transaction;
	}
}
