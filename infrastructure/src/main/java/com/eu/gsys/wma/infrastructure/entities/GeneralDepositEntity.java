package com.eu.gsys.wma.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@Entity
@NamedQueries({
		@NamedQuery(name = GeneralDepositEntity.GET_MOST_RECENT_RECORD, query = GeneralDepositEntity.GET_MOST_RECENT_RECORD_QRY_MYSQL)
})
public class GeneralDepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_MOST_RECENT_RECORD = "GeneralDepositEntity.getMostRecentRecord";
	public static final String GET_MOST_RECENT_RECORD_QRY_MYSQL =
			"SELECT r FROM GeneralDepositEntity r ORDER BY ID DESC LIMIT 1";
	public static final String GET_MOST_RECENT_RECORD_QRY_SQL =
			"SELECT TOP 1 r FROM GeneralDepositEntity r ORDER BY ID DESC";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalTime time;
	private Integer ticketType;
	private Long ticketId;

	private Double totalWheatQty;
	private Double wheatQtyOfCompany;
	private Double wheatQtyOfClients;

	private Double totalFlourQty;
	private Double flourQtyOfCompany;
	private Double flourQtyOfClients;

	private Double totalBranQty;
	private Double branQtyOfCompany;
	private Double branQtyOfClients;
}
