package com.eu.gsys.wma.infrastructure.entities.clients;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "individual_clients")
public class IndividualClientEntity extends GenericClientEntity {

	private String firstName;
	private String lastName;
}
