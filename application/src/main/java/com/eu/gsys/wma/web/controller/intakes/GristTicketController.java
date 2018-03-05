package com.eu.gsys.wma.web.controller.intakes;

import org.springframework.stereotype.Controller;

@Controller
public class GristTicketController {

    private String inputText;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

}
