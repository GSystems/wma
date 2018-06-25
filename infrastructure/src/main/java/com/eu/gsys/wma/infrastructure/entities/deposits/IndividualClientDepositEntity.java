package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "individual_clients_deposits")
@NamedQueries({
		@NamedQuery(name = IndividualClientDepositEntity.GET_DEPOSIT_BY_CLIENT, query = IndividualClientDepositEntity.GET_DEPOSIT_BY_CLIENT_QRY) })
public class IndividualClientDepositEntity extends GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_DEPOSIT_BY_CLIENT =
			"IndividualClientDepositEntity.getDepositByIndividualClientEntity";
	protected static final String GET_DEPOSIT_BY_CLIENT_QRY =
			"SELECT ic FROM IndividualClientDepositEntity ic WHERE ic.individualClientEntity = ?1";

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private IndividualClientEntity individualClientEntity;
}