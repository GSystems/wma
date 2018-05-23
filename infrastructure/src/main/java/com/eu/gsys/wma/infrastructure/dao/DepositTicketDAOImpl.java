package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepositTicketDAOImpl implements DepositTicketDAO {

	@Autowired
	private DepositTicketRepository depositTicketRepository;

	@Override
	public Iterable<DepositTicketEntity> listAllDepositTickets() {
		List<DepositTicketEntity> depositTicketList = new ArrayList<>();

		for (DepositTicketEntity depositTicketEntity : depositTicketRepository.findAll()) {
			depositTicketList.add(depositTicketEntity);
		}

		return depositTicketList;
	}

	@Override
	public DepositTicketEntity getDepositTicketsById(Integer id) {
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
