package com.eu.gsys.wma.infrastructure.entities.tickets;

import com.eu.gsys.wma.infrastructure.entities.ClientEntity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class BasicTicketMaster {

    @Id
    private Long ticketId;

    @ManyToOne
    private ClientEntity client;

    private LocalDate timestamp;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
