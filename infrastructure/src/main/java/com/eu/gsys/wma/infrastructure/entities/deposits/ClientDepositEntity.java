package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ClientDepositEntity extends DepositMaster {


    @ManyToOne
    private ClientEntity client;

    private Double totalWheatBalance;
}
