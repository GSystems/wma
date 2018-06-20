package com.eu.gsys.wma.infrastructure.dao;

import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GristTicketDAOImpl implements GristTicketDAO {

	@Autowired
	private GristTicketRepository gristTicketRepository;

	@Override
	public Iterable<GristTicketEntity> listAllGristTickets() {
		return gristTicketRepository.findAll();
	}

	@Override
	public GristTicketEntity getGristTicketById(Integer id) {
		return gristTicketRepository.findById(id).get();
	}

	@Override
	public GristTicketEntity saveGristTicket(GristTicketEntity gristTicket) {
		return gristTicketRepository.save(gristTicket);
	}

	@Override
	public void deleteGristTicket(Integer id) {
		gristTicketRepository.deleteById(id);
	}
}
