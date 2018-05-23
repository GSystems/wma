package com.eu.gsys.wma.domain.transformers;

import com.eu.gsys.wma.domain.model.Client;
import com.eu.gsys.wma.infrastructure.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements BaseTransformer<ClientEntity, Client> {

    @Override
    public ClientEntity fromModel(Client client) {
        return null;
    }

    @Override
    public Client toModel(ClientEntity clientEntity) {
        return null;
    }
}
