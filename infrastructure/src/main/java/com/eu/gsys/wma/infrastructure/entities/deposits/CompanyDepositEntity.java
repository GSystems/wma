package com.eu.gsys.wma.infrastructure.entities.deposits;

import javax.persistence.Entity;

@Entity
public class CompanyDepositEntity extends DepositMaster {

    private Double tollWheatBalance;
    private Double flourBalance;
    private Double branWheatBalance;

    public Double getTollWheatBalance() {
        return tollWheatBalance;
    }

    public void setTollWheatBalance(Double tollWheatBalance) {
        this.tollWheatBalance = tollWheatBalance;
    }

    public Double getFlourBalance() {
        return flourBalance;
    }

    public void setFlourBalance(Double flourBalance) {
        this.flourBalance = flourBalance;
    }

    public Double getBranWheatBalance() {
        return branWheatBalance;
    }

    public void setBranWheatBalance(Double branWheatBalance) {
        this.branWheatBalance = branWheatBalance;
    }
}
