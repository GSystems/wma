package com.eu.gsys.wma.domain.model.users;

import lombok.Data;

@Data
public class CompanyClient extends GenericClient {

	private String companyName;
	private String companyId;
}
