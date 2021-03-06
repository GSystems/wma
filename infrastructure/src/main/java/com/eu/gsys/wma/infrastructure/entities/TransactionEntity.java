package com.eu.gsys.wma.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
@NamedQueries({
		@NamedQuery(name = TransactionEntity.GET_MOST_RECENT_TRANSACTION, query = TransactionEntity.GET_MOST_RECENT_TRANSACTION_QRY_MYSQL) })
public class TransactionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_MOST_RECENT_TRANSACTION = "TransactionEntity.getMostRecentTransaction";
	protected static final String GET_MOST_RECENT_TRANSACTION_QRY_MYSQL =
			"SELECT t FROM TransactionEntity t WHERE t.id = (SELECT MAX(te.id) FROM TransactionEntity te)";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private LocalDate date;

	@NotNull
	private Integer operationType;

	@NotNull
	private Long ticketNumber;

	@NotNull
	private Double totalWheatQty;

	@NotNull
	private Double wheatQtyOfCompany;

	@NotNull
	private Double wheatQtyOfClients;

	@NotNull
	private Double flourQtyOfCompany;

	@NotNull
	private Double branQtyOfCompany;
}