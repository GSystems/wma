package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.model.deposits.ClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.GeneralDepositService;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.deposits.ClientDepositTransformer;
import com.eu.gsys.wma.domain.transformers.tickets.DepositTicketTransformer;
import com.eu.gsys.wma.domain.util.ErrorMessages;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.dao.deposits.IndividualClientDepositDAO;
import com.eu.gsys.wma.infrastructure.dao.tickets.DepositTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private final DepositTicketTransformer depositTicketTransformer;
	private final IndividualClientDepositDAO individualClientDepositDAO;
	private final GeneralDepositService generalDepositService;
	private final DepositTicketDAO depositTicketDAO;
	private final ClientDepositTransformer clientDepositTransformer;
	private final ClientTransformer clientTransformer;

	@Autowired
	public DepositTicketServiceImpl(DepositTicketTransformer depositTicketTransformer, IndividualClientDepositDAO individualClientDepositDAO, GeneralDepositService generalDepositService, DepositTicketDAO depositTicketDAO, ClientDepositTransformer clientDepositTransformer, ClientTransformer clientTransformer) {
		this.depositTicketTransformer = depositTicketTransformer;
		this.individualClientDepositDAO = individualClientDepositDAO;
		this.generalDepositService = generalDepositService;
		this.depositTicketDAO = depositTicketDAO;
		this.clientDepositTransformer = clientDepositTransformer;
		this.clientTransformer = clientTransformer;
	}

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
		GenericClientEntity genericClientEntity = clientTransformer.fromModel(depositTicket.getGenericClient());
		IndividualClientDepositEntity oldIndividualClientDepositEntity = individualClientDepositDAO.getClientDepositByClient(genericClientEntity);
		GenericDeposit genericDepositForSave;

		if (oldIndividualClientDepositEntity == null) {
			genericDepositForSave = mapDepositFieldForNewClient(depositTicket);
		} else {
			GenericDeposit genericDeposit = clientDepositTransformer.toModel(oldIndividualClientDepositEntity);
			Double newWheatQtyForSave = genericDeposit.getWheatQty() + depositTicket.getWheatQtyForDeposit();

			genericDepositForSave = (ClientDeposit) genericDeposit.clone();
			genericDepositForSave.setWheatQty(newWheatQtyForSave);
		}

		IndividualClientDepositEntity individualClientDepositEntity = (IndividualClientDepositEntity) clientDepositTransformer.fromModel(genericDepositForSave);

		individualClientDepositDAO.saveClientDeposit(individualClientDepositEntity);
	}

	private ClientDeposit mapDepositFieldForNewClient(DepositTicket depositTicket) {
		ClientDeposit newClientDepositForSave = new ClientDeposit();

		newClientDepositForSave.setBranQty(Double.valueOf(0));
		newClientDepositForSave.setGenericClient(depositTicket.getGenericClient());
		newClientDepositForSave.setFlourQty(Double.valueOf(0));
		newClientDepositForSave.setOperationType(depositTicket.getOperationTypeEnum());
		newClientDepositForSave.setTicketId(depositTicket.getTicketId());
		newClientDepositForSave.setDate(depositTicket.getDate());
		newClientDepositForSave.setWheatQty(depositTicket.getWheatQtyForDeposit());

		return newClientDepositForSave;
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
