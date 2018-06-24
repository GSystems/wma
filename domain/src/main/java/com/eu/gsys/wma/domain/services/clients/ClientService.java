package com.eu.gsys.wma.domain.services.clients;

import java.util.List;

public interface ClientService<T> {

	List<T> listAllClients();

	T getClientById(Integer id);

	void saveClientTicket(T t);

	void deleteClientTicket(Integer id);
}
