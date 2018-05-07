package com.eu.gsys.wma.infrastructure.entities.deposits;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import com.eu.gsys.wma.infrastructure.entities.operations.OperationEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class ClientDepositEntity extends DepositMaster {


    @ManyToOne
    private ClientEntity client;

    private Double totalWheatBalance;
    private List<OperationEntity> operations;

}
