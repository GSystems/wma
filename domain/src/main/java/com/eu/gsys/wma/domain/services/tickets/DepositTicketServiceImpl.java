package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.CompanyClientDeposit;
import com.eu.gsys.wma.domain.model.deposits.GeneralDeposit;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.deposits.GeneralDepositService;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.DepositTransformer;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
import com.eu.gsys.wma.domain.util.ErrorMessages;
import com.eu.gsys.wma.domain.util.OperationTypeEnum;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.entities.clients.CompanyClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.CompanyClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.GenericDepositForEntities;
import com.eu.gsys.wma.infrastructure.entities.deposits.IndividualClientDepositEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.DepositTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.CompanyDepositRepository;
import com.eu.gsys.wma.infrastructure.repositories.deposits.IndividualDepositRepository;
import com.eu.gsys.wma.infrastructure.repositories.tickets.DepositTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private final TicketTransformer ticketTransformer;
	private final ClientTransformer clientTransformer;
	private final CompanyDepositRepository companyDepositRepository;
	private final DepositTicketRepository depositTicketRepository;
	private final DepositTransformer depositTransformer;
	private final IndividualDepositRepository individualDepositRepository;
	private final GeneralDepositService generalDepositService;

	@Autowired
	public DepositTicketServiceImpl(TicketTransformer ticketTransformer,
			CompanyDepositRepository companyDepositRepository, IndividualDepositRepository individualDepositRepository,
			GeneralDepositService generalDepositService, DepositTicketRepository depositTicketRepository,
			ClientTransformer clientTransformer, DepositTransformer depositTransformer) {

		this.ticketTransformer = ticketTransformer;
		this.companyDepositRepository = companyDepositRepository;
		this.individualDepositRepository = individualDepositRepository;
		this.generalDepositService = generalDepositService;
		this.depositTicketRepository = depositTicketRepository;
		this.clientTransformer = clientTransformer;
		this.depositTransformer = depositTransformer;
	}

	@Override
	public Iterable<DepositTicket> listAllDepositTickets() {
		List<DepositTicket> depositTicketList = new ArrayList<>();
		List<DepositTicketEntity> depositTicketEntities = (List<DepositTicketEntity>) depositTicketRepository.findAll();

		for (DepositTicketEntity depositTicketEntity : depositTicketEntities) {
			depositTicketList.add((DepositTicket) ticketTransformer.toModel(depositTicketEntity));
		}

		return depositTicketList;
	}

	@Override
	public DepositTicket getDepositTicketById(Integer id) {
		return (DepositTicket) ticketTransformer.toModel(depositTicketRepository.findById(id).get());
	}

	@Override
	public void saveDepositTicket(DepositTicket depositTicket) {
		depositTicket.setOperationTypeEnum(OperationTypeEnum.ADD_DEPOSIT_TICKET);

		calculateAndUpdateNewClientDeposit(depositTicket);
		calculateAndUpdateGeneralDeposit(depositTicket);

		depositTicketRepository.save((DepositTicketEntity) ticketTransformer.fromModel(depositTicket));
	}

	@Override
	public void deleteDepositTicket(DepositTicket depositTicket) throws WmaException {
		depositTicket.setOperationTypeEnum(OperationTypeEnum.REMOVE_DEPOSIT_TICKET);

		if (!depositTicket.getConsumedFlag()) {
			depositTicketRepository.delete((DepositTicketEntity) ticketTransformer.fromModel(depositTicket));
		} else {
			throw new WmaException(ErrorMessages.INCONSISTENT_OPERATION);
		}
	}

	// TODO refactor this code

	private void calculateAndUpdateNewClientDeposit(DepositTicket depositTicket) {
		GenericClientEntity genericClientEntity = clientTransformer.fromModel(depositTicket.getClient());

		if (genericClientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(depositTicket, genericClientEntity);
		} else {
			updateDepositForCompanyClient(depositTicket, genericClientEntity);
		}
	}

	private void updateDepositForIndividualClient(DepositTicket depositTicket,
			GenericClientEntity genericClientEntity) {

		GenericDepositForEntities oldIndividualClientDepositEntity = individualDepositRepository
				.getDepositByIndividualClientEntity((IndividualClientEntity) genericClientEntity);

		GenericDeposit genericDepositForSave;

		if (oldIndividualClientDepositEntity == null) {
			genericDepositForSave = mapDepositFieldForNewClient(depositTicket, new IndividualClientDeposit());
		} else {
			genericDepositForSave = mapFieldsFromOldDeposit(depositTicket, oldIndividualClientDepositEntity);
		}

		IndividualClientDepositEntity individualClientDepositEntity =
				(IndividualClientDepositEntity) depositTransformer.fromModel(genericDepositForSave);

		individualDepositRepository.save(individualClientDepositEntity);
	}

	private void updateDepositForCompanyClient(DepositTicket depositTicket, GenericClientEntity clientEntity) {
		GenericDepositForEntities oldDepositEntity =
				companyDepositRepository.getDepositByCompanyClientEntity((CompanyClientEntity) clientEntity);

		GenericDeposit genericDepositForSave;

		if (oldDepositEntity == null) {
			genericDepositForSave = mapDepositFieldForNewClient(depositTicket, new CompanyClientDeposit());
		} else {
			genericDepositForSave = mapFieldsFromOldDeposit(depositTicket, oldDepositEntity);
		}

		CompanyClientDepositEntity companyClientDepositEntity =
				(CompanyClientDepositEntity) depositTransformer.fromModel(genericDepositForSave);

		companyDepositRepository.save(companyClientDepositEntity);
	}

	// TODO refactor this code

	private GenericDeposit mapFieldsFromOldDeposit(DepositTicket depositTicket,
			GenericDepositForEntities oldIndividualClientDeposit) {

		GenericDeposit genericDeposit = depositTransformer.toModel(oldIndividualClientDeposit);
		Double newWheatQtyForSave = genericDeposit.getWheatQty() + depositTicket.getWheatQtyForDeposit();

		GenericDeposit genericDepositForSave;

		if (oldIndividualClientDeposit instanceof IndividualClientDepositEntity) {
			genericDepositForSave = (IndividualClientDeposit) genericDeposit.clone();
		} else {
			genericDepositForSave = (CompanyClientDeposit) genericDeposit.clone();
		}

		genericDepositForSave.setId(null);
		genericDepositForSave.setTicketId(depositTicket.getTicketId());
		genericDepositForSave.setWheatQty(newWheatQtyForSave);

		return genericDepositForSave;
	}

	private GenericDeposit mapDepositFieldForNewClient(DepositTicket depositTicket, GenericDeposit genericDeposit) {

		GenericDeposit newGenericDepositForSave = genericDeposit;

		newGenericDepositForSave.setBranQty(0d);
		newGenericDepositForSave.setGenericClient(depositTicket.getClient());
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
		newGeneralDepositForSave.setId(null);
		newGeneralDepositForSave.setTotalWheatQty(newTotalWheatQtyForSave);
		newGeneralDepositForSave.setWheatQtyOfClients(newTotalWheatQtyOfClients);
		newGeneralDepositForSave.setTicketId(depositTicket.getTicketId());
		newGeneralDepositForSave.setOperationType(depositTicket.getOperationTypeEnum());
		newGeneralDepositForSave.setDate(depositTicket.getDate());

		generalDepositService.saveRecord(newGeneralDepositForSave);
	}
}
