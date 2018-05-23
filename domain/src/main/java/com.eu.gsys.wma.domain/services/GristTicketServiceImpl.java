package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.transformers.GristTicketTransformer;
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
	private GristTicketRepository gristTicketRepository;

	@Override
	public Iterable<GristTicket> listAllGristTickets() {
		List<GristTicket> gristTicketList = new ArrayList<>();

		for (GristTicketEntity gristTicketEntity : gristTicketRepository.findAll()) {
			gristTicketList.add(gristTicketTransformer.toModel(gristTicketEntity));
		}

		return gristTicketList;
	}

	@Override
	public GristTicket getGristTicketsById(Integer id) {
		return gristTicketTransformer.toModel(gristTicketRepository.findById(id).get());
	}

	@Override
	public GristTicket saveGristTicket(GristTicket gristTicket) {
		return gristTicketTransformer.toModel(
				gristTicketRepository.save(gristTicketTransformer.fromModel(gristTicket)));
	}

	@Override
	public void deleteGristTicket(Integer id) {
		gristTicketRepository.deleteById(id);
	}
}
