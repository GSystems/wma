package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.tickets.GristTicket;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.infrastructure.entities.tickets.GristTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.tickets.GristTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GristTicketServiceImpl implements GristTicketService {

	private final TicketTransformer ticketTransformer;
	private final GristTicketRepository gristTicketRepository;

	@Autowired
	public GristTicketServiceImpl(TicketTransformer ticketTransformer, GristTicketRepository gristTicketRepository) {
		this.ticketTransformer = ticketTransformer;
		this.gristTicketRepository = gristTicketRepository;
	}

	@Override
	public List<GristTicket> listAll() {
		List<GristTicket> gristTicketList = new ArrayList<>();
		List<GristTicketEntity> gristTicketEntities = (List<GristTicketEntity>) gristTicketRepository.findAll();

		for (GristTicketEntity gristTicketEntity : gristTicketEntities) {
			gristTicketList.add((GristTicket) ticketTransformer.toModel(gristTicketEntity));
		}

		return gristTicketList;
	}

	@Override
	public GristTicket findById(Long id) {
		if (gristTicketRepository.findById(id).isPresent()) {
			return (GristTicket) ticketTransformer.toModel(gristTicketRepository.findById(id).get());
		}

		return null;
	}

	@Override
	public void save(GristTicket gristTicket) {
		gristTicketRepository.save((GristTicketEntity) ticketTransformer.fromModel(gristTicket));
	}

	@Override
	public void deleteByGristTicket(GristTicket gristTicket) {
		gristTicketRepository.delete((GristTicketEntity) ticketTransformer.fromModel(gristTicket));
	}

	@Override
	public void addNew(GristTicket gristTicket) {
		gristTicket.setOperationType(OperationTypeEnum.ADD_GRIST_TICKET);
		save(gristTicket);
	}
}
