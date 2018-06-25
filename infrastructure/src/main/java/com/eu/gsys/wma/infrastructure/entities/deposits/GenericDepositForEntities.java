package com.eu.gsys.wma.infrastructure.entities.deposits;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Double wheatQty;
	private Double flourQty;
	private Double branQty;

	@NotNull
	private Integer operationType;

	@NotNull
	private Long ticketId;

	@NotNull
	private LocalDate date;
}
