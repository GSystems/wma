package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import lombok.Data;

import javax.persistence.*;
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
	private Integer operationType;
	private Long ticketId;
	private LocalDate timestamp;

	@ManyToOne
	private GenericClientEntity client;
}
