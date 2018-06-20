package com.eu.gsys.wma.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Client {

	private Integer id;
	private String firstName;
	private String lastName;
	private String companyName;
	private Long clientId;
	private String companyId;
	private String address;
	private Boolean isCompany;
	private LocalDate joinDate;
}
