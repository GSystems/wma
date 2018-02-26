package com.eu.gsys.wma.presentation.intakes;

import com.eu.gsys.wma.business.intakes.GristTicketFacade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "gristTicketMB")
@ViewScoped
public class GristTicketManagedBean {

    @Autowired
    private GristTicketFacade gristTicketFacade;
    private String inputText;

    @PostConstruct
    public void init() {
        gristTicketFacade.test();
    }

    public void myMethod() {
        gristTicketFacade.test();
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public GristTicketFacade getGristTicketFacade() {
        return gristTicketFacade;
    }

    public void setGristTicketFacade(GristTicketFacade gristTicketFacade) {
        this.gristTicketFacade = gristTicketFacade;
    }
}
