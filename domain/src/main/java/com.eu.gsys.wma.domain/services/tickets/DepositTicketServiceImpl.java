package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.GeneralDeposit;
import com.eu.gsys.wma.domain.model.deposits.ClientDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.GeneralDepositService;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.deposits.ClientDepositTransformer;
import com.eu.gsys.wma.domain.transformers.tickets.DepositTicketTransformer;
import com.eu.gsys.wma.domain.util.ErrorMessages;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.dao.deposits.ClientDepositDAO;
import com.eu.gsys.wma.infrastructure.dao.tickets.DepositTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
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
	private ClientDepositDAO clientDepositDAO;

	@Autowired
	private GeneralDepositService generalDepositService;

	@Autowired
	private DepositTicketDAO depositTicketDAO;

	@Autowired
	private ClientDepositTransformer clientDepositTransformer;

	@Autowired
	private ClientTransformer clientTransformer;

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
		depositTicket.setOperationTypeEnum(OperationTypeEnum.ADD_DEPOSIT_TICKET);

		calculateAndUpdateNewClientDeposit(depositTicket);
		calculateAndUpdateGeneralDeposit(depositTicket);

		depositTicketDAO.saveDepositTicket(depositTicketTransformer.fromModel(depositTicket));
	}

	@Override
	public void deleteDepositTicket(DepositTicket depositTicket) throws WmaException {
		depositTicket.setOperationTypeEnum(OperationTypeEnum.REMOVE_DEPOSIT_TICKET);

		if (!depositTicket.getConsumedFlag()) {
			depositTicketDAO.deleteDepositTicket(depositTicketTransformer.fromModel(depositTicket));
		} else {
			throw new WmaException(ErrorMessages.INCONSISTENT_OPERATION);
		}
	}

	private void calculateAndUpdateNewClientDeposit(DepositTicket depositTicket) {
		ClientEntity clientEntity = clientTransformer.fromModel(depositTicket.getClient());
		ClientDeposit oldClientDeposit = clientDepositTransformer.toModel(
				clientDepositDAO.getClientDepositByClient(clientEntity));

		Double oldWheatQty = oldClientDeposit.getWheatQty();
		Double newWheatQtyForSave = oldWheatQty + depositTicket.getWheatQtyForDeposit();

		ClientDeposit newClientDepositForSave = (ClientDeposit) oldClientDeposit.clone();
		newClientDepositForSave.setWheatQty(newWheatQtyForSave);

		clientDepositDAO.saveClientDeposit(clientDepositTransformer.fromModel(newClientDepositForSave));
	}

	private void calculateAndUpdateGeneralDeposit(DepositTicket depositTicket) {
		GeneralDeposit lastGeneralDeposit = generalDepositService.getMostRecentRecord();

		Double oldTotalWheatQty = lastGeneralDeposit.getTotalWheatQty();
		Double oldTotalWheatQtyOfClients = lastGeneralDeposit.getWheatQtyOfClients();

		Double wheatQtyForSave = depositTicket.getWheatQtyForDeposit();
		Double newTotalWheatQtyForSave = oldTotalWheatQty + wheatQtyForSave;
		Double newTotalWheatQtyOfClients = oldTotalWheatQtyOfClients + wheatQtyForSave;

		GeneralDeposit newGeneralDepositForSave = (GeneralDeposit) lastGeneralDeposit.clone();
		newGeneralDepositForSave.setTotalWheatQty(newTotalWheatQtyForSave);
		newGeneralDepositForSave.setWheatQtyOfClients(newTotalWheatQtyOfClients);
		newGeneralDepositForSave.setTicketId(depositTicket.getTicketId());
		newGeneralDepositForSave.setOperationType(depositTicket.getOperationTypeEnum());
		newGeneralDepositForSave.setDate(depositTicket.getDate());

		generalDepositService.saveRecord(newGeneralDepositForSave);
	}
}
