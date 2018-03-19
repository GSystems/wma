package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.transformers.DepositTicketTransformer;
import com.eu.gsys.wma.infrastructure.repositories.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private DepositTicketRepository depositTicketRepository;

	@Autowired
	public void setDepositTicketRepository(DepositTicketRepository depositTicketRepository) {
		this.depositTicketRepository = depositTicketRepository;
	}

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		return DepositTicketTransformer.toDepositTicketFromEntityList(depositTicketRepository.findAll());
	}

	@Override
	public DepositTicket getDepositTicketsById(Integer id) {
		return DepositTicketTransformer.toDepositTicketFromEntity(depositTicketRepository.findById(id).get());
	}

	@Override
	public DepositTicket saveDepositTicket(DepositTicket depositTicket) {
		return DepositTicketTransformer.toDepositTicketFromEntity(
				depositTicketRepository.save(DepositTicketTransformer.fromDepositTicketToEntity(depositTicket)));
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketRepository.deleteById(id);
	}
}
