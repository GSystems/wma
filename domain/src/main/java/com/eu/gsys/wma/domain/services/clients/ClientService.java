package com.eu.gsys.wma.domain.services.clients;

import java.util.List;

//TODO refactor this class

public interface ClientService<T> {

	List<T> listAll();

	T findById(Long id);

	void save(T t);

	void deleteById(Long id);
}
