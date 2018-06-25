package com.eu.gsys.wma.infrastructure.entities.clients;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "company_clients")
public class CompanyClientEntity extends GenericClientEntity {

	@NotNull
	private String companyName;

	@NotNull
	private String companyId;
}
