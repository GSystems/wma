package com.eu.gsys.wma.web.controller.intakes;

import com.eu.gsys.wma.domain.services.DepositTicketService;
import com.eu.gsys.wma.web.mappers.DepositTicketMapper;
import com.eu.gsys.wma.web.model.DepositTicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepositTicketController {

	private DepositTicketService depositTicketService;

	@Autowired
	public void setDepositTicketService(DepositTicketService depositTicketService) {
		this.depositTicketService = depositTicketService;
	}

	@RequestMapping(value = "/depositTickets", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("depositTickets", depositTicketService.listAllDepositTickets());
		return "depositTickets";
	}

	@RequestMapping("depositTicket/{id}")
	public String showDepositTicket(@PathVariable Integer id, Model model) {
		model.addAttribute("depositTicket", depositTicketService.getDepositTicketsById(id));
		return "depositTicketShow";
	}

	@RequestMapping("depositTicket/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("depositTicket", depositTicketService.getDepositTicketsById(id));
		return "depositTicketForm";
	}

	@RequestMapping("depositTicket/new")
	public String newDepositTicket(Model model) {
		model.addAttribute("depositTicket", new DepositTicketModel());
		return "depositTicketForm";
	}

	@RequestMapping(value = "depositTicket", method = RequestMethod.POST)
	public String saveDepositTicket(DepositTicketModel depositTicketModel) {
		depositTicketService.saveDepositTicket(DepositTicketMapper.toDepositTicketFromModel(depositTicketModel));
		return "redirect:/depositTicket/" + depositTicketModel.getTicketId();
	}

	@RequestMapping("depositTicket/delete/{id}")
	public String delete(@PathVariable Integer id) {
		depositTicketService.deleteDepositTicket(id);
		return "redirect:/depositTickets";
	}
}
