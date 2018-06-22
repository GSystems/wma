package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class GenericDepositForEntities {

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
	private ClientEntity client;
}
