package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.Transaction;
import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.model.mill.Mill;
import com.eu.gsys.wma.domain.model.mill.MillOutput;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.services.DepositService;
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

@Service
public class WithdrawTicketServiceImpl implements WithdrawTicketService {

	private final ClientTransformer clientTransformer;
	private final DepositTicketService depositTicketService;
	private final DepositService depositService;
	private final Mill mill;
	private final TicketTransformer ticketTransformer;
	private final TransactionService transactionService;
	private final WithdrawTicketRepository withdrawTicketRepository;

	@Autowired
	public WithdrawTicketServiceImpl(ClientTransformer clientTransformer,
			DepositTicketService depositTicketService, DepositService depositService, Mill mill,
			TicketTransformer ticketTransformer, TransactionService transactionService,
			WithdrawTicketRepository withdrawTicketRepository) {

		this.clientTransformer = clientTransformer;
		this.depositTicketService = depositTicketService;
		this.depositService = depositService;
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

		if (isDirectClient) {
			withdrawOperationForDirectClient(withdrawTicket, depositTicket);
		} else {
			// TODO see this case
		}
	}

	private void withdrawOperationForDirectClient(WithdrawTicket withdrawTicket, DepositTicket depositTicket) throws WmaException {
		// TODO refactor this logic

		GenericDeposit lastDeposit = depositService.findByTicketNumber(withdrawTicket.getReferenceTicketNumber());
		double remainingWheatQty = lastDeposit.getWheatQty() - withdrawTicket.getWheatQty();

		if (!depositTicket.getConsumedFlag() && remainingWheatQty >= 0d) {
			MillOutput millOutput = mill.grindWheat(withdrawTicket.getWheatQty());

			withdrawTicket.setClient(lastDeposit.getClient());

			calculateAndUpdateClientDeposit(withdrawTicket, millOutput, remainingWheatQty);
			calculateAndUpdateTransactionLedger(withdrawTicket, millOutput);

			withdrawTicketRepository.save((WithdrawTicketEntity) ticketTransformer.fromModel(withdrawTicket));
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}
	}

	@Override
	public void deleteById(Long id) {
		withdrawTicketRepository.deleteById(id);
	}

	@Override
	public void deleteByWithdrawTicket(WithdrawTicket withdrawTicket) {

	}

	private void calculateAndUpdateClientDeposit(WithdrawTicket withdrawTicket,
			MillOutput millOutput, double remainingWheatQty) {

		GenericClientEntity clientEntity = clientTransformer.fromModel(withdrawTicket.getClient());

		// TODO refactor this code

		if (clientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(withdrawTicket, millOutput, remainingWheatQty);
		} else {
			// TODO see this case
		}
	}

	private void updateDepositForIndividualClient(WithdrawTicket withdrawTicket,
			MillOutput millOutput, double remainingWheatQty) {

		IndividualClientDeposit depositForSave = new IndividualClientDeposit();

		depositForSave.setBranQty(millOutput.getBranQty());
		depositForSave.setClient(withdrawTicket.getClient());
		depositForSave.setDate(withdrawTicket.getDate());
		depositForSave.setFlourQty(millOutput.getFlourQty());
		depositForSave.setOperationType(withdrawTicket.getOperationType());
		depositForSave.setReferenceTicketNumber(withdrawTicket.getReferenceTicketNumber());
		depositForSave.setTicketNumber(withdrawTicket.getTicketNumber());
		depositForSave.setWheatQty(remainingWheatQty);

		depositService.save(depositForSave);
	}

	private void calculateAndUpdateTransactionLedger(WithdrawTicket withdrawTicket, MillOutput millOutput) {

		Transaction lastTransaction = transactionService.getMostRecentTransaction();

		Double totalWheatForSave = lastTransaction.getTotalWheatQty() - withdrawTicket.getWheatQty();
		Double wheatQtyOfClients = lastTransaction.getWheatQtyOfClients() - withdrawTicket.getWheatQty();
		Double wheatQtyOfCompany = lastTransaction.getWheatQtyOfCompany() + millOutput.getTollWheatQty();

		Transaction transactionForSave = new Transaction();

		transactionForSave.setDate(withdrawTicket.getDate());
		transactionForSave.setOperationType(withdrawTicket.getOperationType());
		transactionForSave.setTicketNumber(withdrawTicket.getTicketNumber());
		transactionForSave.setTotalWheatQty(totalWheatForSave);
		transactionForSave.setWheatQtyOfClients(wheatQtyOfClients);
		transactionForSave.setWheatQtyOfCompany(wheatQtyOfCompany);

		transactionService.save(transactionForSave);
	}
}
