package com.eu.gsys.wma.infrastructure.entities.clients;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@MappedSuperclass
public class GenericClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String address;
	private LocalDate joinDate;

	private Double wheatQty = Double.valueOf(0);
	private Double flourQty = Double.valueOf(0);
	private Double branQty = Double.valueOf(0);

	@OneToMany
	private List<DepositTicketEntity> depositTicketEntities;

	@OneToMany
	private List<GristTicketEntity> gristTicketEntities;
}
