package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.CompanyClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.deposits.GeneralDepositService;
import com.eu.gsys.wma.domain.transformers.DepositTicketTransformer;
import com.eu.gsys.wma.domain.transformers.GenericClientTransformer;
import com.eu.gsys.wma.domain.transformers.GenericDepositTransformer;
import com.eu.gsys.wma.domain.util.ErrorMessages;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.dao.deposits.CompanyClientDepositDAO;
import com.eu.gsys.wma.infrastructure.dao.deposits.IndividualClientDepositDAO;
import com.eu.gsys.wma.infrastructure.dao.tickets.DepositTicketDAO;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private final DepositTicketTransformer depositTicketTransformer;
	private final CompanyClientDepositDAO companyClientDepositDAO;
	private final IndividualClientDepositDAO individualClientDepositDAO;
	private final GeneralDepositService generalDepositService;
	private final DepositTicketDAO depositTicketDAO;
	private final GenericDepositTransformer genericDepositTransformer;
	private final GenericClientTransformer genericClientTransformer;

	@Autowired
	public DepositTicketServiceImpl(DepositTicketTransformer depositTicketTransformer,
			CompanyClientDepositDAO companyClientDepositDAO, IndividualClientDepositDAO individualClientDepositDAO,
			GeneralDepositService generalDepositService,
			DepositTicketDAO depositTicketDAO,
			GenericDepositTransformer genericDepositTransformer,
			GenericClientTransformer genericClientTransformer) {

		this.depositTicketTransformer = depositTicketTransformer;
		this.companyClientDepositDAO = companyClientDepositDAO;
		this.individualClientDepositDAO = individualClientDepositDAO;
		this.generalDepositService = generalDepositService;
		this.depositTicketDAO = depositTicketDAO;
		this.genericDepositTransformer = genericDepositTransformer;
		this.genericClientTransformer = genericClientTransformer;
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

	// TODO refactor this code

	private void calculateAndUpdateNewClientDeposit(DepositTicket depositTicket) {
		GenericClientEntity genericClientEntity = genericClientTransformer.fromModel(depositTicket.getGenericClient());

		if (genericClientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(depositTicket, genericClientEntity);
		} else {
			updateDepositForCompanyClient(depositTicket, genericClientEntity);
		}
	}

	private void updateDepositForIndividualClient(DepositTicket depositTicket, GenericClientEntity genericClientEntity) {

		GenericDepositForEntities oldIndividualClientDepositEntity =
				individualClientDepositDAO.getDepositByClient(genericClientEntity);
		GenericDeposit genericDepositForSave;

		if (oldIndividualClientDepositEntity == null) {
			genericDepositForSave = mapDepositFieldForNewClient(depositTicket, new IndividualClientDeposit());
		} else {
			genericDepositForSave = mapFieldsFromOldDeposit(depositTicket, oldIndividualClientDepositEntity);
		}

		IndividualClientDepositEntity individualClientDepositEntity = (IndividualClientDepositEntity)
				genericDepositTransformer.fromModel(genericDepositForSave);

		individualClientDepositDAO.saveDeposit(individualClientDepositEntity);
	}

	private void updateDepositForCompanyClient(DepositTicket depositTicket, GenericClientEntity genericClientEntity) {
		GenericDepositForEntities oldIndividualClientDepositEntity =
				companyClientDepositDAO.getDepositByClient(genericClientEntity);
		GenericDeposit genericDepositForSave;

		if (oldIndividualClientDepositEntity == null) {
			genericDepositForSave = mapDepositFieldForNewClient(depositTicket, new CompanyClientDeposit());
		} else {
			genericDepositForSave = mapFieldsFromOldDeposit(depositTicket, oldIndividualClientDepositEntity);
		}

		CompanyClientDepositEntity companyClientDepositEntity = (CompanyClientDepositEntity)
				genericDepositTransformer.fromModel(genericDepositForSave);

		companyClientDepositDAO.saveDeposit(companyClientDepositEntity);
	}

	private GenericDeposit mapFieldsFromOldDeposit(DepositTicket depositTicket,
			GenericDepositForEntities oldIndividualClientDeposit) {

		GenericDeposit genericDeposit = genericDepositTransformer.toModel(oldIndividualClientDeposit);
		Double newWheatQtyForSave = genericDeposit.getWheatQty() + depositTicket.getWheatQtyForDeposit();

		GenericDeposit genericDepositForSave = (IndividualClientDeposit) genericDeposit.clone();
		genericDepositForSave.setWheatQty(newWheatQtyForSave);

		return genericDepositForSave;
	}

	private GenericDeposit mapDepositFieldForNewClient(DepositTicket depositTicket,
			GenericDeposit genericDeposit) {

		GenericDeposit newGenericDepositForSave = genericDeposit;

		newGenericDepositForSave.setBranQty(0d);
		newGenericDepositForSave.setGenericClient(depositTicket.getGenericClient());
		newGenericDepositForSave.setFlourQty(0d);
		newGenericDepositForSave.setOperationType(depositTicket.getOperationTypeEnum());
		newGenericDepositForSave.setTicketId(depositTicket.getTicketId());
		newGenericDepositForSave.setDate(depositTicket.getDate());
		newGenericDepositForSave.setWheatQty(depositTicket.getWheatQtyForDeposit());

		return newGenericDepositForSave;
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
