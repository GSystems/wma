package com.eu.gsys.wma.domain.services.tickets;

import com.eu.gsys.wma.domain.model.deposits.GenericDeposit;
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
	private final TicketTransformer ticketTransformer;
	private final TransactionService transactionService;
	private final WithdrawTicketRepository withdrawTicketRepository;

	@Autowired
	public WithdrawTicketServiceImpl(ClientTransformer clientTransformer,
			ClientServiceImpl clientService, DepositTicketService depositTicketService,
			DepositTransformer depositTransformer, DepositService depositService, TicketTransformer ticketTransformer,
			TransactionService transactionService, WithdrawTicketRepository withdrawTicketRepository) {

		this.clientTransformer = clientTransformer;
		this.clientService = clientService;
		this.depositTicketService = depositTicketService;
		this.depositTransformer = depositTransformer;
		this.depositService = depositService;
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
	public void totalWithdraw(WithdrawTicket withdrawTicket) throws WmaException {
		DepositTicket depositTicket =
				depositTicketService.findByTicketNumber(withdrawTicket.getReferenceTicketNumber());

		if (!depositTicket.getConsumedFlag()) {
			calculateAndUpdateClientDeposit(depositTicket);
			calculateAndUpdateTransactionLedger(depositTicket);
		} else {
			throw new WmaException(INCONSISTENT_OPERATION);
		}

		withdrawTicketRepository.save((WithdrawTicketEntity) ticketTransformer.fromModel(withdrawTicket));
	}

	@Override
	public void deleteById(Long id) {
		withdrawTicketRepository.deleteById(id);
	}

	@Override
	public void deleteByWithdrawTicket(WithdrawTicket withdrawTicket) {

	}

	private void calculateAndUpdateClientDeposit(DepositTicket depositTicket) {
		GenericClientEntity clientEntity = clientTransformer.fromModel(depositTicket.getClient());

		if (clientEntity instanceof IndividualClientEntity) {
			updateDepositForIndividualClient(depositTicket, clientEntity);
		} else {

		}
	}

	private void updateDepositForIndividualClient(DepositTicket depositTicket, GenericClientEntity clientEntity) {
		GenericDeposit lastDeposit = depositService
				.findByClient(clientTransformer.toModel(clientEntity));

		GenericDeposit depositForSave;

	}

	private void calculateAndUpdateTransactionLedger(DepositTicket depositTicket) {

	}
}
