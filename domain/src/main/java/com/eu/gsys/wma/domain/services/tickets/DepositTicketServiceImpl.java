package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.models.Transaction;
import com.eu.gsys.wma.domain.models.deposits.CompanyClientDeposit;
import com.eu.gsys.wma.domain.models.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.models.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.models.tickets.DepositTicket;
import com.eu.gsys.wma.domain.services.TransactionService;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.DepositTransformer;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
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

import static com.eu.gsys.wma.domain.util.ErrorMessages.INCONSISTENT_OPERATION;
import static com.eu.gsys.wma.domain.util.GeneralConstants.EMPTY_STRING;

@Service
public class DepositTicketServiceImpl implements DepositTicketService {

	private final ClientTransformer clientTransformer;
	private final CompanyDepositRepository companyDepositRepository;
	private final DepositTicketRepository depositTicketRepository;
	private final DepositTransformer depositTransformer;
	private final IndividualDepositRepository individualDepositRepository;
	private final TicketTransformer ticketTransformer;
	private final TransactionService transactionService;

	@Autowired
	public DepositTicketServiceImpl(TicketTransformer ticketTransformer,
			CompanyDepositRepository companyDepositRepository, IndividualDepositRepository individualDepositRepository,
			TransactionService transactionService, DepositTicketRepository depositTicketRepository,
			ClientTransformer clientTransformer, DepositTransformer depositTransformer) {

		this.ticketTransformer = ticketTransformer;
		this.companyDepositRepository = companyDepositRepository;
		this.individualDepositRepository = individualDepositRepository;
		this.transactionService = transactionService;
		this.depositTicketRepository = depositTicketRepository;
		this.clientTransformer = clientTransformer;
		this.depositTransformer = depositTransformer;
	}

	@Override
	public Iterable<DepositTicket> findAll() {
		List<DepositTicket> depositTickets = new ArrayList<>();
		List<DepositTicketEntity> depositTicketEntities = (List<DepositTicketEntity>) depositTicketRepository.findAll();

		for (DepositTicketEntity depositTicketEntity : depositTicketEntities) {
			depositTickets.add((DepositTicket) ticketTransformer.toModel(depositTicketEntity));
		}

		return depositTickets;
	}

	@Override
	public DepositTicket findById(Long id) {
		if (depositTicketRepository.findById(id).isPresent()) {
			return (DepositTicket) ticketTransformer.toModel(depositTicketRepository.findById(id).get());
		}

		return null;
	}

	@Override
	public void save(DepositTicket depositTicket) {
		depositTicketRepository.save((DepositTicketEntity) ticketTransformer.fromModel(depositTicket));
	}

	@Override
	public void deleteById(Long id) {
		depositTicketRepository.deleteById(id);
	}

	@Override
	public DepositTicket findByTicketNumber(Long ticketNumber) {
		return (DepositTicket) ticketTransformer.toModel(depositTicketRepository.findByTicketNumber(ticketNumber));
	}

	@Override
	public void addNew(DepositTicket depositTicket) throws WmaException {
		depositTicket.setOperationType(OperationTypeEnum.ADD_DEPOSIT_TICKET);

		if (depositTicket.getWheatQty() > 0d) {
			calculateAndUpdateClientDeposit(depositTicket);
			calculateAndUpdateTransactionLedger(depositTicket);
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}

		save(depositTicket);
	}

	@Override
	public void removeDepositTicket(DepositTicket depositTicket) throws WmaException {
		DepositTicket lastDepositTicket = findByTicketNumber(depositTicket.getTicketNumber());
		String comment = depositTicket.getComment();

		if (lastDepositTicket.getConsumedFlag() && EMPTY_STRING.equals(comment)) {
			throw new WmaException(INCONSISTENT_OPERATION);
		} else {
			depositTicket.setOperationType(OperationTypeEnum.REMOVE_DEPOSIT_TICKET);
			depositTicket.setConsumedFlag(true);

			calculateAndUpdateClientDeposit(depositTicket);
			calculateAndUpdateTransactionLedger(depositTicket);

			depositTicket.setId(null);
			depositTicketRepository.save((DepositTicketEntity) ticketTransformer.fromModel(depositTicket));
		}
	}

	private void calculateAndUpdateClientDeposit(DepositTicket depositTicket) throws WmaException {
		GenericClientEntity clientEntity = clientTransformer.fromModel(depositTicket.getClient());

		// TODO refactor this code

		if (clientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(depositTicket, clientEntity);
		} else {
			updateDepositForCompanyClient(depositTicket, clientEntity);
		}
	}

	private void updateDepositForIndividualClient(DepositTicket depositTicket,
			GenericClientEntity clientEntity) throws WmaException {

		GenericDepositForEntities oldIndividualClientDepositEntity = individualDepositRepository
				.findDepositByClientEntity((IndividualClientEntity) clientEntity);

		GenericDeposit depositForSave;

		if (oldIndividualClientDepositEntity == null) {
			depositForSave = mapDepositFieldForNewClient(depositTicket, new IndividualClientDeposit());
		} else {
			depositForSave = mapFieldsFromOldDeposit(oldIndividualClientDepositEntity);
			mapFieldsForAddOrRemoveOperation(depositForSave, depositTicket);
		}

		IndividualClientDepositEntity individualClientDepositEntity =
				(IndividualClientDepositEntity) depositTransformer.fromModel(depositForSave);

		individualDepositRepository.save(individualClientDepositEntity);
	}

	private void mapFieldsForAddOrRemoveOperation(GenericDeposit depositForSave, DepositTicket depositTicket) throws WmaException {
		Double newWheatQtyForSave = calculateNewWheatValueByOperationType(
				depositTicket.getOperationType(), depositForSave.getWheatQty(), depositTicket.getWheatQty());

		depositForSave.setId(null);
		depositForSave.setTicketNumber(depositTicket.getTicketNumber());
		depositForSave.setWheatQty(newWheatQtyForSave);
		depositForSave.setOperationType(depositTicket.getOperationType());
	}

	private void updateDepositForCompanyClient(DepositTicket depositTicket, GenericClientEntity clientEntity) throws WmaException {
		GenericDepositForEntities oldDepositEntity =
				companyDepositRepository.getDepositByClientEntity((CompanyClientEntity) clientEntity);

		GenericDeposit depositForSave;

		if (oldDepositEntity == null) {
			depositForSave = mapDepositFieldForNewClient(depositTicket, new CompanyClientDeposit());
		} else {
			depositForSave = mapFieldsFromOldDeposit(oldDepositEntity);
			mapFieldsForAddOrRemoveOperation(depositForSave, depositTicket);
		}

		CompanyClientDepositEntity companyClientDepositEntity =
				(CompanyClientDepositEntity) depositTransformer.fromModel(depositForSave);

		companyDepositRepository.save(companyClientDepositEntity);
	}

	private GenericDeposit mapFieldsFromOldDeposit(GenericDepositForEntities oldDepositEntity) {
		GenericDeposit deposit = depositTransformer.toModel(oldDepositEntity);

		// TODO refactor this code

		GenericDeposit oldDeposit;

		if (oldDepositEntity instanceof IndividualClientDepositEntity) {
			oldDeposit = (IndividualClientDeposit) deposit.clone();
		} else {
			oldDeposit = (CompanyClientDeposit) deposit.clone();
		}

		return oldDeposit;
	}

	private GenericDeposit mapDepositFieldForNewClient(DepositTicket depositTicket, GenericDeposit deposit) {
		deposit.setClient(depositTicket.getClient());
		deposit.setOperationType(depositTicket.getOperationType());
		deposit.setTicketNumber(depositTicket.getTicketNumber());
		deposit.setDate(depositTicket.getDate());
		deposit.setWheatQty(depositTicket.getWheatQty());

		return deposit;
	}

	private void calculateAndUpdateTransactionLedger(DepositTicket depositTicket) throws WmaException {
		Transaction lastTransaction = transactionService.getMostRecentTransaction();

		Double oldTotalWheatQty = lastTransaction.getTotalWheatQty();
		Double oldTotalWheatQtyOfClients = lastTransaction.getWheatQtyOfClients();

		Double wheatQtyForSave = depositTicket.getWheatQty();

		Double newTotalWheatQtyForSave = calculateNewWheatValueByOperationType(
				depositTicket.getOperationType(), oldTotalWheatQty, wheatQtyForSave);

		Double newTotalWheatQtyOfClients = calculateNewWheatValueByOperationType(
				depositTicket.getOperationType(), oldTotalWheatQtyOfClients, wheatQtyForSave);

		Transaction newTransactionForSave = new Transaction();

		newTransactionForSave.setTotalWheatQty(newTotalWheatQtyForSave);
		newTransactionForSave.setWheatQtyOfClients(newTotalWheatQtyOfClients);
		newTransactionForSave.setTicketNumber(depositTicket.getTicketNumber());
		newTransactionForSave.setOperationType(depositTicket.getOperationType());
		newTransactionForSave.setDate(depositTicket.getDate());

		transactionService.save(newTransactionForSave);
	}

	private Double calculateNewWheatValueByOperationType(OperationTypeEnum operationType, Double oldValue, Double newValue) throws WmaException {
		Double valueForSave;

		switch (operationType) {
			case ADD_DEPOSIT_TICKET:
				valueForSave = oldValue + newValue;
				break;
			case REMOVE_DEPOSIT_TICKET:
				valueForSave = oldValue - newValue;
				break;
			default:
				throw new WmaException(INCONSISTENT_OPERATION);
		}

		if (valueForSave.compareTo(0d) < 0) {
			throw new WmaException(INCONSISTENT_OPERATION);
		}

		return valueForSave;
	}
}
