package com.eu.gsys.wma.infrastructure.entities.clients;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CompanyClientEntity extends GenericClientEntity {

	private String companyName;
	private String companyId;
}
