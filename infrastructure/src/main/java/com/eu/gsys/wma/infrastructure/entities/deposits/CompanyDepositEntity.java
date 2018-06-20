package com.eu.gsys.wma.infrastructure.entities.deposits;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CompanyDepositEntity extends DepositMaster {

	private Double profitWheatQty;
	private Double profitFlourQty;
	private Double profitBranQty;
}
