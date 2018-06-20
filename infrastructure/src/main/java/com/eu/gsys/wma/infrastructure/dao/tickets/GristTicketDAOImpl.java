package com.eu.gsys.wma.infrastructure.dao.tickets;

import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GristTicketDAOImpl implements GristTicketDAO {

	@Autowired
	private GristTicketRepository gristTicketRepository;

	@Override
	public List<GristTicketEntity> listAllGristTickets() {
		return (List<GristTicketEntity>) gristTicketRepository.findAll();
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
