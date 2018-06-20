package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.GeneralDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.GeneralDepositService;
import com.eu.gsys.wma.domain.transformers.tickets.DepositTicketTransformer;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.dao.tickets.DepositTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.eu.gsys.wma.domain.util.ErrorMessages.INCONSISTENT_OPERATION;
import static com.eu.gsys.wma.domain.util.GeneralConstants.DELETE_DEPOSIT_TICKET;
import static com.eu.gsys.wma.domain.util.GeneralConstants.NEW_DEPOSIT_TICKET;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	@Autowired
	private DepositTicketTransformer depositTicketTransformer;

	@Autowired
	private GeneralDepositService generalDepositService;

	@Autowired
	private DepositTicketDAO depositTicketDAO;

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		List<DepositTicket> depositTicketList = new ArrayList<>();
		List<DepositTicketEntity> depositTicketEntities = depositTicketDAO.listAllDepositTickets();

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
		Double newTotalWheatQtyForClientsInGeneralDeposit = null;
		Double newWheatQtyForClientDeposit = null;

		GeneralDeposit generalDeposit = generalDepositService.getMostRecentRecord();

		try {
			newTotalWheatQtyForClientsInGeneralDeposit = calculateNewValueForClientDeposit(depositTicket);
			newWheatQtyForClientDeposit = calculateNewValueForGeneralDeposit(depositTicket);
		} catch (WmaException e) {
			e.printStackTrace();
		}

		depositTicket.getClient().setWheatQty(newWheatQtyForClientDeposit);


		depositTicketDAO.saveDepositTicket(depositTicketTransformer.fromModel(depositTicket));
	}

	@Override
	public void deleteDepositTicket(Integer id) {
		depositTicketDAO.deleteDepositTicket(id);
	}

	private void updateGeneralDeposit(DepositTicket depositTicket) {

	}

	private Double calculateNewValueForGeneralDeposit(DepositTicket depositTicket) throws WmaException {
		// TODO make consistent checks - what if the wheatQty was already consumed

		String operationType = depositTicket.getOperationTypeEnum().getTicketName();
		Double oldTotalWheatQtyOfClients = generalDepositService.getMostRecentRecord().getWheatQtyOfClients();
		Double wheatQtyForDeposit = depositTicket.getWheatQtyForDeposit();

		return calculateNewValueForByOperationType(operationType, oldTotalWheatQtyOfClients, wheatQtyForDeposit);
	}

	private Double calculateNewValueForClientDeposit(DepositTicket depositTicket) throws WmaException {
		// TODO make consistent checks - what if the wheatQty was already consumed

		String operationType = depositTicket.getOperationTypeEnum().getTicketName();
		Double oldWheatQty = depositTicket.getClient().getWheatQty();
		Double wheatQtyForDeposit = depositTicket.getWheatQtyForDeposit();

		return calculateNewValueForByOperationType(operationType, oldWheatQty, wheatQtyForDeposit);
	}

	private Double calculateNewValueForByOperationType(String operationType, Double oldValue, Double valueForDeposit)
			throws WmaException {

		Double newValue;

		if (NEW_DEPOSIT_TICKET.equalsIgnoreCase(operationType)) {
			newValue = oldValue + valueForDeposit;
		} else if (DELETE_DEPOSIT_TICKET.equalsIgnoreCase(operationType) && oldValue.compareTo(valueForDeposit) >= 0) {
			newValue = oldValue - valueForDeposit;
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}

		return newValue;
	}
}
