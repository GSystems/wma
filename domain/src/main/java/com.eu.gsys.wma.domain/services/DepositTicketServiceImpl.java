package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.transformers.DepositTicketTransformer;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	@Autowired
	private DepositTicketTransformer depositTicketTransformer;

	@Autowired
	private DepositTicketRepository depositTicketRepository;

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		List<DepositTicket> depositTicketList = new ArrayList<>();

		for (DepositTicketEntity depositTicketEntity : depositTicketRepository.findAll()) {
			depositTicketList.add(depositTicketTransformer.toModel(depositTicketEntity));
		}

		return depositTicketList;
	}

	@Override
	public DepositTicket getDepositTicketsById(Integer id) {
		return depositTicketTransformer.toModel(depositTicketRepository.findById(id).get());
	}

	@Override
	public DepositTicket saveDepositTicket(DepositTicket depositTicket) {
		return depositTicketTransformer.toModel(
				depositTicketRepository.save(depositTicketTransformer.fromModel(depositTicket)));
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketRepository.deleteById(id);
	}
}
