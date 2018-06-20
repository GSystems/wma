package com.eu.gsys.wma.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class ClientEntity {

	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	private String companyName;
	private Long clientId;
	private String companyId;
	private String address;
	private LocalDate joinDate;
}
