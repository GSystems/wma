package com.eu.gsys.wma.infrastructure.entities.deposits;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "general_deposits")
@NamedQueries({
		@NamedQuery(name = GeneralDepositEntity.GET_MOST_RECENT_RECORD, query = GeneralDepositEntity.GET_MOST_RECENT_RECORD_QRY_MYSQL) })
public class GeneralDepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_MOST_RECENT_RECORD = "GeneralDepositEntity.getMostRecentRecord";
	protected static final String GET_MOST_RECENT_RECORD_QRY_MYSQL =
			"SELECT g FROM GeneralDepositEntity g WHERE g.id = (SELECT MAX(gd.id) FROM GeneralDepositEntity gd)";
	protected static final String GET_MOST_RECENT_RECORD_QRY_SQL =
			"SELECT TOP 1 r FROM GeneralDepositEntity r ORDER BY ID DESC";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private LocalDate date;

	@NotNull
	private Integer operationType;

	@NotNull
	private Long ticketId;

	@NotNull
	private Double totalWheatQty;

	@NotNull
	private Double wheatQtyOfCompany;

	@NotNull
	private Double wheatQtyOfClients;

	@NotNull
	private Double totalFlourQty;

	@NotNull
	private Double flourQtyOfCompany;

	@NotNull
	private Double flourQtyOfClients;

	@NotNull
	private Double totalBranQty;

	@NotNull
	private Double branQtyOfCompany;

	@NotNull
	private Double branQtyOfClients;
}
