package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "company_clients_deposits")
@NamedQueries({
		@NamedQuery(name = CompanyClientDepositEntity.GET_DEPOSIT_BY_CLIENT, query = CompanyClientDepositEntity.GET_DEPOSIT_BY_CLIENT_QRY) })
public class CompanyClientDepositEntity extends GenericDepositForEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_DEPOSIT_BY_CLIENT = "CompanyClientDepositEntity.findDepositByClientEntity";
	protected static final String GET_DEPOSIT_BY_CLIENT_QRY =
			"SELECT cc FROM CompanyClientDepositEntity cc WHERE (cc.clientEntity = ?1 AND cc.id = (SELECT MAX(cce.id) FROM CompanyClientDepositEntity cce))";

	@OneToOne
	private CompanyClientEntity clientEntity;
}
