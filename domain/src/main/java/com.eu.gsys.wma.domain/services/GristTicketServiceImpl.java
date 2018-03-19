package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.transformers.GristTicketTransformer;
import com.eu.gsys.wma.infrastructure.repositories.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GristTicketServiceImpl implements GristTicketService {

	private GristTicketRepository gristTicketRepository;

	@Autowired
	public void setGristTicketRepository(GristTicketRepository gristTicketRepository) {
		this.gristTicketRepository = gristTicketRepository;
	}

	@Override
	public Iterable<GristTicket> listAllGristTickets() {
		return GristTicketTransformer.fromGristTicketToEntityList(gristTicketRepository.findAll());
	}

	@Override
	public GristTicket getGristTicketsById(Integer id) {
		return GristTicketTransformer.toGristTicketFromEntity(gristTicketRepository.findById(id).get());
	}

	@Override
	public GristTicket saveGristTicket(GristTicket gristTicket) {
		return GristTicketTransformer.toGristTicketFromEntity(
				gristTicketRepository.save(GristTicketTransformer.fromGristTicketToEntity(gristTicket)));
	}

	@Override
	public void deleteGristTicket(Integer id) {
		gristTicketRepository.deleteById(id);
	}
}
