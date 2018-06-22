package com.eu.gsys.wma.infrastructure.entities;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String companyName;
	private Long clientId;
	private String companyId;
	private String address;
	private LocalDate joinDate;

	private Double wheatQty;
	private Double flourQty;
	private Double branQty;

	List<DepositTicketEntity> depositTicketEntities;
	List<GristTicketEntity> gristTicketEntities;
}
