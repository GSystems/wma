package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepositTicketDAOImpl implements DepositTicketDAO {

	@Autowired
	private DepositTicketRepository depositTicketRepository;

	@Override
	public Iterable<DepositTicketEntity> listAllDepositTickets() {
		return depositTicketRepository.findAll();
	}

	@Override
	public DepositTicketEntity getDepositTicketById(Integer id) {
		return depositTicketRepository.findById(id).get();
	}

	@Override
	public DepositTicketEntity saveDepositTicket(DepositTicketEntity depositTicket) {
		return depositTicketRepository.save(depositTicket);
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketRepository.deleteById(id);
	}
}
