package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
import com.eu.gsys.wma.infrastructure.repositories.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

@Service
public class GristTicketServiceImpl implements GristTicketService {

	private TicketTransformer transformer;
	private GristTicketRepository gristTicketRepository;

	@Autowired
	public void setTransformer(TicketTransformer transformer) {
		this.transformer = transformer;
	}

	@Autowired
	public void setGristTicketRepository(GristTicketRepository gristTicketRepository) {
		this.gristTicketRepository = gristTicketRepository;
	}

	@Override
	public Iterable<GristTicket> listAllGristTickets() {
		return transformer.fromGristTicketToEntityList(gristTicketRepository.findAll());
	}

	@Override
	public GristTicket getGristTicketsById(Integer id) {
		return transformer.toGristTicketFromEntity(gristTicketRepository.findById(id).get());
	}

	@Override
	public GristTicket saveGristTicket(GristTicket gristTicket) {
		return transformer.toGristTicketFromEntity(
				gristTicketRepository.save(transformer.fromGristTicketToEntity(gristTicket)));
	}

	@Override
	public void deleteGristTicket(Integer id) {
		gristTicketRepository.deleteById(id);
	}
}
