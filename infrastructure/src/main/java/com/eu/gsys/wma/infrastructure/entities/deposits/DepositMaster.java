package com.eu.gsys.wma.infrastructure.entities.deposits;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class DepositMaster {

    @Id
    private Integer id;
    private Double totalWheatQty;
    private Double totalFlourQty;
    private Double totalBranQty;
    private LocalDate timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalWheatQty() {
        return totalWheatQty;
    }

    public void setTotalWheatQty(Double totalWheatQty) {
        this.totalWheatQty = totalWheatQty;
    }

    public Double getTotalFlourQty() {
        return totalFlourQty;
    }

    public void setTotalFlourQty(Double totalFlourQty) {
        this.totalFlourQty = totalFlourQty;
    }

    public Double getTotalBranQty() {
        return totalBranQty;
    }

    public void setTotalBranQty(Double totalBranQty) {
        this.totalBranQty = totalBranQty;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
