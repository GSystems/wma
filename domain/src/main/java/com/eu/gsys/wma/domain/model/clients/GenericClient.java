package com.eu.gsys.wma.domain.model.clients;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericClient {

	private Integer id;
	private String address;
	private LocalDate joinDate;
}
