package com.eu.gsys.wma.infrastructure.entities.deposits;

import javax.persistence.Entity;

@Entity
public class CompanyDepositEntity extends DepositMaster {

    private Double profitWheatQty;
    private Double profitFlourQty;
    private Double profitBranQty;

    public Double getProfitWheatQty() {
        return profitWheatQty;
    }

    public void setProfitWheatQty(Double profitWheatQty) {
        this.profitWheatQty = profitWheatQty;
    }

    public Double getProfitFlourQty() {
        return profitFlourQty;
    }

    public void setProfitFlourQty(Double profitFlourQty) {
        this.profitFlourQty = profitFlourQty;
    }

    public Double getProfitBranQty() {
        return profitBranQty;
    }

    public void setProfitBranQty(Double profitBranQty) {
        this.profitBranQty = profitBranQty;
    }
}
