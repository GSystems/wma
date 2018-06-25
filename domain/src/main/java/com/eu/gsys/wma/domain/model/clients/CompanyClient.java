package com.eu.gsys.wma.domain.model.clients;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyClient extends GenericClient {

	private String companyName;
	private String companyId;
}
