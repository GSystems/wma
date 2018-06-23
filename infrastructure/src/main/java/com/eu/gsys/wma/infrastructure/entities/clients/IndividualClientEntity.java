package com.eu.gsys.wma.infrastructure.entities.clients;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class IndividualClientEntity extends GenericClientEntity {

	private String firstName;
	private String lastName;
}
