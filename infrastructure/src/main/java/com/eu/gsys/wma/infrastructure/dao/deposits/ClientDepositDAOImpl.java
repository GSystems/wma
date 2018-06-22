package com.eu.gsys.wma.infrastructure.dao.deposits;

import com.eu.gsys.wma.infrastructure.dao.BaseDAOBean;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import com.eu.gsys.wma.infrastructure.entities.deposits.ClientDepositEntity;
import com.eu.gsys.wma.infrastructure.repositories.deposits.ClientDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDepositDAOImpl extends BaseDAOBean<ClientDepositEntity, Long> implements ClientDepositDAO {

	@Autowired
	private ClientDepositRepository clientDepositRepository;

	@Override
	public List<ClientDepositEntity> listAllClientsDeposits() {
		return (List<ClientDepositEntity>) clientDepositRepository.findAll();
	}

	@Override
	public ClientDepositEntity getClientDepositById(Integer id) {
		return clientDepositRepository.findById(id).get();
	}

	@Override
	public void saveClientDeposit(ClientDepositEntity clientDepositEntity) {
		clientDepositRepository.save(clientDepositEntity);
	}

	@Override
	public void deleteClientDeposit(Integer id) {
		clientDepositRepository.deleteById(id);
	}

	@Override
	public ClientDepositEntity getClientDepositByClient(ClientEntity client) {
		TypedQuery<ClientDepositEntity> query = getEntityManager().createNamedQuery(
				ClientDepositEntity.GET_CLIENT_DEPOSIT_BY_CLIENT, ClientDepositEntity.class);

		query.setParameter(1, client);

		return query.getSingleResult();
	}
}
