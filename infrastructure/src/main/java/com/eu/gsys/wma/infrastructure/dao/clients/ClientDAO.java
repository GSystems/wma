package com.eu.gsys.wma.infrastructure.dao.clients;

import java.util.List;

public interface ClientDAO<T> {

	List<T> listAllClients();

	T getClientById(Integer id);

	void saveClient(T t);

	void deleteClient(Integer id);
}
