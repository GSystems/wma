package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.Transaction;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.model.mill.Mill;
import com.eu.gsys.wma.domain.model.mill.MillOutput;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.services.ClientDepositService;
import com.eu.gsys.wma.domain.services.TransactionService;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.TicketTransformer;
import com.eu.gsys.wma.domain.util.WmaException;
import com.eu.gsys.wma.infrastructure.entities.clients.GenericClientEntity;
import com.eu.gsys.wma.infrastructure.entities.clients.IndividualClientEntity;
import com.eu.gsys.wma.infrastructure.entities.tickets.WithdrawTicketEntity;
import com.eu.gsys.wma.infrastructure.repositories.tickets.WithdrawTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.eu.gsys.wma.domain.util.ErrorMessages.INCONSISTENT_OPERATION;
import static com.eu.gsys.wma.domain.util.OperationTypeEnum.WITHDRAW_WITH_DEPOSIT_TICKET;

@Service
public class WithdrawTicketServiceImpl implements WithdrawTicketService {

	private final ClientTransformer clientTransformer;
	private final DepositTicketService depositTicketService;
	private final ClientDepositService clientDepositService;
	private final Mill mill;
	private final TicketTransformer ticketTransformer;
	private final TransactionService transactionService;
	private final WithdrawTicketRepository withdrawTicketRepository;

	@Autowired
	public WithdrawTicketServiceImpl(ClientTransformer clientTransformer,
			DepositTicketService depositTicketService, ClientDepositService clientDepositService, Mill mill,
			TicketTransformer ticketTransformer, TransactionService transactionService,
			WithdrawTicketRepository withdrawTicketRepository) {

		this.clientTransformer = clientTransformer;
		this.depositTicketService = depositTicketService;
		this.clientDepositService = clientDepositService;
		this.mill = mill;
		this.ticketTransformer = ticketTransformer;
		this.transactionService = transactionService;
		this.withdrawTicketRepository = withdrawTicketRepository;
	}

	@Override
	public List<WithdrawTicket> findAll() {
		List<WithdrawTicket> withdrawTickets = new ArrayList<>();
		List<WithdrawTicketEntity> withdrawTicketEntities = (List<WithdrawTicketEntity>) withdrawTicketRepository.findAll();

		for (WithdrawTicketEntity withdrawTicketEntity : withdrawTicketEntities) {
			withdrawTickets.add((WithdrawTicket) ticketTransformer.toModel(withdrawTicketEntity));
		}

		return withdrawTickets;
	}

	@Override
	public WithdrawTicket findById(Long id) {
		if (withdrawTicketRepository.findById(id).isPresent()) {
			return (WithdrawTicket) ticketTransformer.toModel(withdrawTicketRepository.findById(id).get());
		}

		return null;
	}

	@Override
	public void save(WithdrawTicket withdrawTicket) {
		withdrawTicketRepository.save((WithdrawTicketEntity) ticketTransformer.fromModel(withdrawTicket));
	}

	@Override
	public void grindAndWithdrawFromDeposit(WithdrawTicket withdrawTicket, boolean isDirectClient) throws WmaException {
		DepositTicket depositTicket =
				depositTicketService.findByTicketNumber(withdrawTicket.getReferenceTicketNumber());

		if (!depositTicket.getConsumedFlag()) {
//			if (isDirectClient) {
				withdrawOperationForDirectClient(withdrawTicket);
//			} else {
//				 TODO see this case
//			}
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}
	}

	private void withdrawOperationForDirectClient(WithdrawTicket withdrawTicket) throws WmaException {
		// TODO refactor this logic

		DepositTicket lastDepositTicket = depositTicketService.findByTicketNumber(withdrawTicket.getReferenceTicketNumber());
		double remainingWheatQty = lastDepositTicket.getWheatQty() - withdrawTicket.getWheatQtyWithdrawn();

		if (remainingWheatQty >= 0d) {
			withdrawTicket.setOperationType(WITHDRAW_WITH_DEPOSIT_TICKET);
			MillOutput millOutput = mill.grindWheat(withdrawTicket.getWheatQtyWithdrawn());

			mapFieldsForTicket(withdrawTicket, lastDepositTicket, millOutput);

			calculateAndUpdateClientDeposit(withdrawTicket, remainingWheatQty);
			calculateAndUpdateTransactionLedger(withdrawTicket);

			withdrawTicketRepository.save((WithdrawTicketEntity) ticketTransformer.fromModel(withdrawTicket));
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}
	}

	private void mapFieldsForTicket(WithdrawTicket withdrawTicket,
			DepositTicket lastDepositTicekt, MillOutput millOutput) {

		withdrawTicket.setClient(lastDepositTicekt.getClient());
		withdrawTicket.setBranQtyWithdrawn(millOutput.getBranQty());
		withdrawTicket.setFlourQtyWithdrawn(millOutput.getFlourQty());
		withdrawTicket.setManufacturingLossesQty(millOutput.getManufacturingLossesQty());
		withdrawTicket.setOtherCorpusQty(millOutput.getOtherCorpusQty());
		withdrawTicket.setTollWheatQty(millOutput.getTollWheatQty());
	}

	@Override
	public void deleteById(Long id) {
		withdrawTicketRepository.deleteById(id);
	}

	@Override
	public void deleteByWithdrawTicket(WithdrawTicket withdrawTicket) {

	}

	private void calculateAndUpdateClientDeposit(WithdrawTicket withdrawTicket, double remainingWheatQty) {
		GenericClientEntity clientEntity = clientTransformer.fromModel(withdrawTicket.getClient());

		// TODO refactor this code

		if (clientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(withdrawTicket, remainingWheatQty);
		}
		// TODO see the else case
	}

	private void updateDepositForIndividualClient(WithdrawTicket withdrawTicket, double remainingWheatQty) {
		IndividualClientDeposit depositForSave = new IndividualClientDeposit();

		depositForSave.setClient(withdrawTicket.getClient());
		depositForSave.setDate(withdrawTicket.getDate());
		depositForSave.setOperationType(withdrawTicket.getOperationType());
		depositForSave.setReferenceTicketNumber(withdrawTicket.getReferenceTicketNumber());
		depositForSave.setTicketNumber(withdrawTicket.getTicketNumber());
		depositForSave.setWheatQty(remainingWheatQty);
		depositForSave.setOperationType(WITHDRAW_WITH_DEPOSIT_TICKET);

		clientDepositService.save(depositForSave);
	}

	private void calculateAndUpdateTransactionLedger(WithdrawTicket withdrawTicket) {
		Transaction lastTransaction = transactionService.getMostRecentTransaction();

		Double totalWheatForSave = lastTransaction.getTotalWheatQty() - withdrawTicket.getWheatQtyWithdrawn();
		Double wheatQtyOfClients =
				lastTransaction.getWheatQtyOfClients() - withdrawTicket.getWheatQtyWithdrawn() - withdrawTicket.getTollWheatQty();
		Double wheatQtyOfCompany = lastTransaction.getWheatQtyOfCompany() + withdrawTicket.getTollWheatQty();

		Transaction transactionForSave = new Transaction();

		transactionForSave.setDate(withdrawTicket.getDate());
		transactionForSave.setOperationType(withdrawTicket.getOperationType());
		transactionForSave.setTicketNumber(withdrawTicket.getTicketNumber());
		transactionForSave.setTotalWheatQty(totalWheatForSave);
		transactionForSave.setWheatQtyOfClients(wheatQtyOfClients);
		transactionForSave.setWheatQtyOfCompany(wheatQtyOfCompany);
		transactionForSave.setOperationType(WITHDRAW_WITH_DEPOSIT_TICKET);

		transactionService.save(transactionForSave);
	}
}
