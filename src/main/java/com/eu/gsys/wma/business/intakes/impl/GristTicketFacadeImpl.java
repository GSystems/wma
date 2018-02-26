package com.eu.gsys.wma.business.intakes.impl;

import com.eu.gsys.wma.business.intakes.GristTicketFacade;
import org.springframework.stereotype.Component;

@Component
public class GristTicketFacadeImpl implements GristTicketFacade {

    @Override
    public void test() {
        System.out.print("spring test");
    }
}
