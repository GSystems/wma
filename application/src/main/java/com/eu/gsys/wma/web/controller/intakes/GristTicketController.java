package com.eu.gsys.wma.web.controller.intakes;

import com.eu.gsys.wma.domain.model.GristTicket;
import com.eu.gsys.wma.domain.services.GristTicketService;
import com.eu.gsys.wma.web.model.GristTicketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GristTicketController {

	private GristTicketService gristTicketService;

	@Autowired
	public void setGristTicketService(GristTicketService gristTicketService) {
		this.gristTicketService = gristTicketService;
	}

	@RequestMapping(value = "/gristTickets", method = RequestMethod.GET)
	public String listGristTickets(Model model) {
		return "gristTickets";
	}

	@RequestMapping("gristTicket/{id}")
	public String showGristTicket(@PathVariable Integer id, Model model) {
		return "gristTicketShow";
	}

	@RequestMapping("gristTicket/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("gristTicket", new GristTicket());
		return "gristTicketForm";
	}

	@RequestMapping("gristTicket/new")
	public String newGristTicket(Model model) {
		model.addAttribute("gristTicket", new GristTicketModel());
		return "gristTicketForm";
	}

	@RequestMapping("gristTicket/delete/{id}")
	public String delete(@PathVariable Integer id) {
		gristTicketService.deleteGristTicket(id);
		return "redirect:/gristTickets";
	}
}
