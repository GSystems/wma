package com.eu.gsys.wma.domain.models.clients;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GenericClient {

	private Long id;
	private String address;
	private LocalDate joinDate;
}
