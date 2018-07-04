package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
import com.eu.gsys.wma.domain.model.deposits.IndividualClientDeposit;
import com.eu.gsys.wma.domain.model.mill.Mill;
import com.eu.gsys.wma.domain.model.mill.MillOutput;
import com.eu.gsys.wma.domain.model.tickets.DepositTicket;
import com.eu.gsys.wma.domain.model.tickets.WithdrawTicket;
import com.eu.gsys.wma.domain.services.DepositService;
import com.eu.gsys.wma.domain.services.TransactionService;
import com.eu.gsys.wma.domain.services.clients.ClientServiceImpl;
import com.eu.gsys.wma.domain.transformers.ClientTransformer;
import com.eu.gsys.wma.domain.transformers.DepositTransformer;
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
	private final ClientServiceImpl clientService;
	private final DepositTicketService depositTicketService;
	private final DepositTransformer depositTransformer;
	private final DepositService depositService;
	private final Mill mill;
	private final TicketTransformer ticketTransformer;
	private final TransactionService transactionService;
	private final WithdrawTicketRepository withdrawTicketRepository;

	@Autowired
	public WithdrawTicketServiceImpl(ClientTransformer clientTransformer,
			ClientServiceImpl clientService, DepositTicketService depositTicketService,
			DepositTransformer depositTransformer, DepositService depositService, Mill mill, TicketTransformer ticketTransformer,
			TransactionService transactionService, WithdrawTicketRepository withdrawTicketRepository) {

		this.clientTransformer = clientTransformer;
		this.clientService = clientService;
		this.depositTicketService = depositTicketService;
		this.depositTransformer = depositTransformer;
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
	public void grindAndWithdrawFromDeposit(WithdrawTicket withdrawTicket) throws WmaException {
		DepositTicket depositTicket =
				depositTicketService.findByTicketNumber(withdrawTicket.getReferenceTicketNumber());

		GenericClientEntity clientEntity = clientTransformer.fromModel(withdrawTicket.getClient());
		GenericDeposit lastDeposit = depositService.findByClient(clientTransformer.toModel(clientEntity));
		double remainingWheatQty = lastDeposit.getWheatQty() - withdrawTicket.getWheatQty();
		MillOutput millOutput;

		if (!depositTicket.getConsumedFlag() && remainingWheatQty >= 0d) {
			millOutput = mill.grindWheat(withdrawTicket.getWheatQty());
			calculateAndUpdateClientDeposit(withdrawTicket, millOutput, remainingWheatQty);
			calculateAndUpdateTransactionLedger(withdrawTicket);
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

		if (clientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(withdrawTicket, millOutput, remainingWheatQty);
		} else {

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

	private void calculateAndUpdateTransactionLedger(WithdrawTicket withdrawTicket) {

	}
}
