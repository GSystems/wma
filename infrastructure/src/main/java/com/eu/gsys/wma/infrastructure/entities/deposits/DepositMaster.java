package com.eu.gsys.wma.infrastructure.entities.deposits;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class DepositMaster {

    @Id
    private Integer id;
    private Double totalWheatBalance;
    private LocalDate timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalWheatBalance() {
        return totalWheatBalance;
    }

    public void setTotalWheatBalance(Double totalWheatBalance) {
        this.totalWheatBalance = totalWheatBalance;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
