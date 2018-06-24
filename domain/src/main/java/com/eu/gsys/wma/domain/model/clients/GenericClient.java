package com.eu.gsys.wma.domain.model.clients;

import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GenericClient {

	private Integer id;
	private String address;
	private LocalDate joinDate;
	private Double wheatQty;
	private Double flourQty;
	private Double branQty;

//	private List<DepositTicket> depositTickets;
//	private List<GristTicket> gristTickets;
}
