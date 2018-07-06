package com.eu.gsys.wma.infrastructure.entities.tickets;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "deposit_tickets")
@NamedQueries({
		@NamedQuery(name = DepositTicketEntity.GET_TICKET_BY_TICKET_NUMBER, query = DepositTicketEntity.GET_TICKET_BY_TICKET_NUMBER_QRY)
})
public class DepositTicketEntity extends GenericTicketForEntities {

	public static final String GET_TICKET_BY_TICKET_NUMBER = "DepositTicketEntity.getTicketByTicketNumber";
	protected static final String GET_TICKET_BY_TICKET_NUMBER_QRY =
			"SELECT d FROM DepositTicketEntity d WHERE (d.ticketNumber = ?1 AND d.id = (SELECT MAX(dte.id) FROM DepositTicketEntity dte))";

	@NotNull
	private Double wheatQty;
	private int consumedFlag;
}
