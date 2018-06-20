package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.domain.model.DepositTicket;
import com.eu.gsys.wma.domain.transformers.DepositTicketTransformer;
import com.eu.gsys.wma.infrastructure.dao.DepositTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	@Autowired
	private DepositTicketTransformer depositTicketTransformer;

	@Autowired
	private DepositTicketDAO depositTicketDAO;

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		List<DepositTicket> depositTicketList = new ArrayList<>();
		List<DepositTicketEntity> depositTicketEntities =
				(List<DepositTicketEntity>) depositTicketDAO.listAllDepositTickets();

		for (DepositTicketEntity depositTicketEntity : depositTicketEntities) {
			depositTicketList.add(depositTicketTransformer.toModel(depositTicketEntity));
		}

		return depositTicketList;
	}

	@Override
	public DepositTicket getDepositTicketById(Integer id) {
		return depositTicketTransformer.toModel(depositTicketDAO.getDepositTicketById(id));
	}

	@Override
	public void saveDepositTicket(DepositTicket depositTicket) {
		depositTicketDAO.saveDepositTicket(depositTicketTransformer.fromModel(depositTicket));
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketDAO.deleteDepositTicket(id);
	}
}
