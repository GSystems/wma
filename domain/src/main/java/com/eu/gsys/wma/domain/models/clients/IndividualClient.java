package com.eu.gsys.wma.domain.models.clients;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IndividualClient extends GenericClient {

	private String firstName;
	private String lastName;
	private String personalId;
}
