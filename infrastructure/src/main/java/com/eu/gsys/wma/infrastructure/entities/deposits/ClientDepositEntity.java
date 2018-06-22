package com.eu.gsys.wma.infrastructure.entities.deposits;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
		@NamedQuery(name = ClientDepositEntity.GET_CLIENT_DEPOSIT_BY_CLIENT, query = ClientDepositEntity.GET_CLIENT_DEPOSIT_BY_CLIENT_QRY)
})
public class ClientDepositEntity extends GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_CLIENT_DEPOSIT_BY_CLIENT = "ClientDepositEntity.getDepositByClient";
	protected static final String GET_CLIENT_DEPOSIT_BY_CLIENT_QRY = "SELECT d FROM ClientDepositEntity d WHERE d.client = :client";
}