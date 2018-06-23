package com.eu.gsys.wma.infrastructure.entities.deposits;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CompanyClientDepositEntity extends GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;
}
