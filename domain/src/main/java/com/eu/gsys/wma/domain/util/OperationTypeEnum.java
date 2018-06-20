package com.eu.gsys.wma.domain.util;

import static com.eu.gsys.wma.domain.util.GeneralConstants.*;

public enum OperationTypeEnum {

	ADD_DEPOSIT_TICKET(1, NEW_DEPOSIT_TICKET),
	ADD_GRIST_TICKET(2, NEW_GRIST_TICKET),
	REMOVE_DEPOSIT_TICKET(-1, DELETE_DEPOSIT_TICKET),
	REMOVE_GRIST_TICKET(-2, DELETE_GRIST_TICKET);

	private Integer code;
	private String ticketName;

	OperationTypeEnum(Integer code, String ticketName) {
		this.code = code;
		this.ticketName = ticketName;
	}

	public Integer getCode() {
		return code;
	}

	public String getTicketName() {
		return ticketName;
	}

	public static OperationTypeEnum getTicketTypeByCode(Integer code) {
		for (OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()) {
			if (code == operationTypeEnum.getCode()) {
				return operationTypeEnum;
			}
		}
		return null;
	}
}
