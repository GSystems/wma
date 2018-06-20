package com.eu.gsys.wma.domain.model;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

	private Double wheatQty;
	private Double flourQty;
	private Double branQty;

	List<DepositTicket> depositTickets;
	List<GristTicket> gristTickets;
}
