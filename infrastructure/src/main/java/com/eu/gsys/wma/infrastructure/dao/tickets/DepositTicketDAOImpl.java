package com.eu.gsys.wma.infrastructure.dao.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.tickets.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepositTicketDAOImpl implements DepositTicketDAO {

	@Autowired
	private DepositTicketRepository depositTicketRepository;

	@Override
	public List<DepositTicketEntity> listAllDepositTickets() {
		return (List<DepositTicketEntity>) depositTicketRepository.findAll();
	}

	@Override
	public DepositTicketEntity getDepositTicketById(Integer id) {
		return depositTicketRepository.findById(id).get();
	}

	@Override
	public void saveDepositTicket(DepositTicketEntity depositTicket) {
		depositTicketRepository.save(depositTicket);
	}

	@Override
	public void deleteDepositTicket(DepositTicketEntity depositTicketEntity) {
		depositTicketRepository.delete(depositTicketEntity);
	}
}
