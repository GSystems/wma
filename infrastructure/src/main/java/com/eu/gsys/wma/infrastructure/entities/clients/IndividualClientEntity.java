package com.eu.gsys.wma.infrastructure.entities.clients;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "individual_clients")
public class IndividualClientEntity extends GenericClientEntity {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String personalId;
}
