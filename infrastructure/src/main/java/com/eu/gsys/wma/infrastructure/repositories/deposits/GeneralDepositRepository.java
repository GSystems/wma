package com.eu.gsys.wma.infrastructure.repositories.deposits;

import com.eu.gsys.wma.infrastructure.entities.deposits.GeneralDepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDepositRepository extends JpaRepository<GeneralDepositEntity, Long> {

	GeneralDepositEntity getMostRecentRecord();
}
