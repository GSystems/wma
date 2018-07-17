package com.eu.gsys.wma.domain.util;

import static com.eu.gsys.wma.domain.util.GeneralConstants.*;

public enum OperationTypeEnum {

	ADD_DEPOSIT_TICKET(1, ADD_DEPOSIT_TICKET_OPERATION),
	ADD_GRIST_TICKET(2, ADD_GRIST_TICKET_OPERATION),
	ADD_WITHDRAW_TICKET(3, ADD_WITHDRAW_TICKET_OPERATION),
	REMOVE_DEPOSIT_TICKET(-1, REMOVE_DEPOSIT_TICKET_OPERATION),
	REMOVE_GRIST_TICKET(-2, REMOVE_GRIST_TICKET_OPERATION),
	REMOVE_WITHDRAW_TICKET(-3, REMOVE_WITHDRAW_TICKET_OPERATION);

	private Integer code;
	private String ticketName;

	OperationTypeEnum(Integer code, String ticketName) {
		this.code = code;
		this.ticketName = ticketName;
	}

	public static OperationTypeEnum getTicketTypeByCode(Integer code) {
		for (OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()) {
			if (code == operationTypeEnum.getCode()) {
				return operationTypeEnum;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public String getTicketName() {
		return ticketName;
	}
}
