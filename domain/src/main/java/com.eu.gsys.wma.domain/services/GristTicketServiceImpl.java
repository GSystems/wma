package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.transformers.GristTicketTransformer;
import com.eu.gsys.wma.infrastructure.dao.GristTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GristTicketServiceImpl implements GristTicketService {

	@Autowired
	private GristTicketTransformer gristTicketTransformer;

	@Autowired
	private GristTicketDAO gristTicketDAO;

	@Override
	public Iterable<GristTicket> listAllGristTickets() {
		List<GristTicket> gristTicketList = new ArrayList<>();

		for (GristTicketEntity gristTicketEntity : gristTicketDAO.listAllGristTickets()) {
			gristTicketList.add(gristTicketTransformer.toModel(gristTicketEntity));
		}

		return gristTicketList;
	}

	@Override
	public GristTicket getGristTicketsById(Integer id) {
		return gristTicketTransformer.toModel(gristTicketDAO.getGristTicketsById(id));
	}

	@Override
	public GristTicket saveGristTicket(GristTicket gristTicket) {
		return gristTicketTransformer.toModel(
				gristTicketDAO.saveGristTicket(gristTicketTransformer.fromModel(gristTicket)));
	}

	@Override
	public void deleteGristTicket(Integer id) {
		gristTicketDAO.deleteGristTicket(id);
	}
}
