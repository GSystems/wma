package com.eu.gsys.wma.infrastructure.entities.deposits;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class DepositMaster {

	@Id
	private Integer id;
	private Double totalWheatQty;
	private Double totalFlourQty;
	private Double totalBranQty;
	private LocalDate timestamp;
}
