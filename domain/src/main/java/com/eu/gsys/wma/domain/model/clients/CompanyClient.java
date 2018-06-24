package com.eu.gsys.wma.domain.model.clients;

import lombok.Data;

@Data
public class CompanyClient extends GenericClient {

	private String companyName;
	private String companyId;
}
