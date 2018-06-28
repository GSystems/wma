package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.Transaction;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionTransformer implements BaseTransformer<TransactionEntity, Transaction> {

	@Override
	public Transaction toModel(TransactionEntity transactionEntity) {
		Transaction transaction = new Transaction();

		transaction.setBranQtyOfClients(transactionEntity.getBranQtyOfClients());
		transaction.setBranQtyOfCompany(transactionEntity.getBranQtyOfCompany());
		transaction.setFlourQtyOfClients(transactionEntity.getFlourQtyOfClients());
		transaction.setFlourQtyOfCompany(transactionEntity.getFlourQtyOfCompany());
		transaction.setId(transactionEntity.getId());
		transaction.setOperationType(OperationTypeEnum.getTicketTypeByCode(transactionEntity.getOperationType()));
		transaction.setTicketNumber(transactionEntity.getTicketNumber());
		transaction.setDate(transactionEntity.getDate());
		transaction.setTotalBranQty(transactionEntity.getTotalBranQty());
		transaction.setTotalFlourQty(transactionEntity.getTotalFlourQty());
		transaction.setTotalWheatQty(transactionEntity.getTotalWheatQty());
		transaction.setWheatQtyOfClients(transactionEntity.getWheatQtyOfClients());
		transaction.setWheatQtyOfCompany(transactionEntity.getWheatQtyOfCompany());

		return transaction;
	}

	@Override
	public TransactionEntity fromModel(Transaction transaction) {
		TransactionEntity transactionEntity = new TransactionEntity();

		transactionEntity.setBranQtyOfClients(transaction.getBranQtyOfClients());
		transactionEntity.setBranQtyOfCompany(transaction.getBranQtyOfCompany());
		transactionEntity.setFlourQtyOfClients(transaction.getFlourQtyOfClients());
		transactionEntity.setFlourQtyOfCompany(transaction.getFlourQtyOfCompany());
		transactionEntity.setId(transaction.getId());
		transactionEntity.setOperationType(transaction.getOperationType().getCode());
		transactionEntity.setTicketNumber(transaction.getTicketNumber());
		transactionEntity.setDate(transaction.getDate());
		transactionEntity.setTotalBranQty(transaction.getTotalBranQty());
		transactionEntity.setTotalFlourQty(transaction.getTotalFlourQty());
		transactionEntity.setTotalWheatQty(transaction.getTotalWheatQty());
		transactionEntity.setWheatQtyOfClients(transaction.getWheatQtyOfClients());
		transactionEntity.setWheatQtyOfCompany(transaction.getWheatQtyOfCompany());

		return transactionEntity;
	}
}
