package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
import com.eu.gsys.wma.infrastructure.repositories.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private TicketTransformer transformer;
	private DepositTicketRepository depositTicketRepository;

	@Autowired
	public void setTransformer(TicketTransformer transformer) {
		this.transformer = transformer;
	}

	@Autowired
	public void setDepositTicketRepository(DepositTicketRepository depositTicketRepository) {
		this.depositTicketRepository = depositTicketRepository;
	}

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		return transformer.toDepositTicketFromEntityList(depositTicketRepository.findAll());
	}

	@Override
	public DepositTicket getDepositTicketsById(Integer id) {
		return transformer.toDepositTicketFromEntity(depositTicketRepository.findById(id).get());
	}

	@Override
	public DepositTicket saveDepositTicket(DepositTicket depositTicket) {
		return transformer.toDepositTicketFromEntity(
				depositTicketRepository.save(transformer.fromDepositTicketToEntity(depositTicket)));
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketRepository.deleteById(id);
	}
}
