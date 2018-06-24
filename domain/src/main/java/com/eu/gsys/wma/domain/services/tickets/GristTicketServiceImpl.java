package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.domain.transformers.GristTicketTransformer;
import com.eu.gsys.wma.infrastructure.dao.tickets.GristTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GristTicketServiceImpl implements GristTicketService {

	private final GristTicketTransformer gristTicketTransformer;
	private final GristTicketDAO gristTicketDAO;

	@Autowired
	public GristTicketServiceImpl(GristTicketTransformer gristTicketTransformer, GristTicketDAO gristTicketDAO) {
		this.gristTicketTransformer = gristTicketTransformer;
		this.gristTicketDAO = gristTicketDAO;
	}

	@Override
	public List<GristTicket> listAllGristTickets() {
		List<GristTicket> gristTicketList = new ArrayList<>();
		List<GristTicketEntity> gristTicketEntities = gristTicketDAO.listAllGristTickets();

		for (GristTicketEntity gristTicketEntity : gristTicketEntities) {
			gristTicketList.add(gristTicketTransformer.toModel(gristTicketEntity));
		}

		return gristTicketList;
	}

	@Override
	public GristTicket getGristTicketById(Integer id) {
		return gristTicketTransformer.toModel(gristTicketDAO.getGristTicketById(id));
	}

	@Override
	public void saveGristTicket(GristTicket gristTicket) {
		gristTicketDAO.saveGristTicket(gristTicketTransformer.fromModel(gristTicket));
	}

	@Override
	public void deleteGristTicket(GristTicket gristTicket) {
		gristTicketDAO.deleteGristTicket(gristTicketTransformer.fromModel(gristTicket));
	}
}
