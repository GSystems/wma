package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.models.Transaction;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionTransformer implements BaseTransformer<TransactionEntity, Transaction> {

	@Override
	public Transaction toModel(TransactionEntity transactionEntity) {
		Transaction transaction = new Transaction();

		transaction.setBranQtyOfCompany(transactionEntity.getBranQtyOfCompany());
		transaction.setFlourQtyOfCompany(transactionEntity.getFlourQtyOfCompany());
		transaction.setId(transactionEntity.getId());
		transaction.setOperationType(OperationTypeEnum.getTicketTypeByCode(transactionEntity.getOperationType()));
		transaction.setTicketNumber(transactionEntity.getTicketNumber());
		transaction.setDate(transactionEntity.getDate());
		transaction.setTotalWheatQty(transactionEntity.getTotalWheatQty());
		transaction.setWheatQtyOfClients(transactionEntity.getWheatQtyOfClients());
		transaction.setWheatQtyOfCompany(transactionEntity.getWheatQtyOfCompany());

		return transaction;
	}

	@Override
	public TransactionEntity fromModel(Transaction transaction) {
		TransactionEntity transactionEntity = new TransactionEntity();

		transactionEntity.setBranQtyOfCompany(transaction.getBranQtyOfCompany());
		transactionEntity.setFlourQtyOfCompany(transaction.getFlourQtyOfCompany());
		transactionEntity.setId(transaction.getId());
		transactionEntity.setOperationType(transaction.getOperationType().getCode());
		transactionEntity.setTicketNumber(transaction.getTicketNumber());
		transactionEntity.setDate(transaction.getDate());
		transactionEntity.setTotalWheatQty(transaction.getTotalWheatQty());
		transactionEntity.setWheatQtyOfClients(transaction.getWheatQtyOfClients());
		transactionEntity.setWheatQtyOfCompany(transaction.getWheatQtyOfCompany());

		return transactionEntity;
	}
}
