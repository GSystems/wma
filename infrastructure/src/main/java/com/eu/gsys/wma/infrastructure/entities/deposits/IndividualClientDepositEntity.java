package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

import static com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "individual_clients_deposits")
@NamedQueries({
		@NamedQuery(name = GET_DEPOSIT_BY_CLIENT, query = GET_DEPOSIT_BY_CLIENT_QRY),
		@NamedQuery(name = GET_DEPOSIT_BY_TICKET_NUMBER, query = GET_DEPOSIT_BY_TICKET_NUMBER_QRY)
})
public class IndividualClientDepositEntity extends GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_DEPOSIT_BY_CLIENT =
			"IndividualClientDepositEntity.findDepositByClientEntity";
	protected static final String GET_DEPOSIT_BY_CLIENT_QRY =
			"SELECT ic FROM IndividualClientDepositEntity ic WHERE (ic.clientEntity = ?1 AND ic.id = (SELECT MAX(ice.id) FROM IndividualClientDepositEntity ice))";

	public static final String GET_DEPOSIT_BY_TICKET_NUMBER = "IndividualClientDepositEntity.findDepositByTicketNumber";
	protected static final String GET_DEPOSIT_BY_TICKET_NUMBER_QRY =
			"SELECT ic FROM IndividualClientDepositEntity ic WHERE (ic.ticketNumber =?1 AND ic.id = (SELECT MAX(ice.id) FROM IndividualClientDepositEntity ice))";

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private IndividualClientEntity clientEntity;
}