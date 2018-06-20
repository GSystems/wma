package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class ClientDepositEntity extends DepositMaster {

	@ManyToOne
	private ClientEntity client;

}
