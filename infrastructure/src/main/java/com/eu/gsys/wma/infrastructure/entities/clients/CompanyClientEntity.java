package com.eu.gsys.wma.infrastructure.entities.clients;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "company_clients")
public class CompanyClientEntity extends GenericClientEntity {

	private String companyName;
	private String companyId;
}
