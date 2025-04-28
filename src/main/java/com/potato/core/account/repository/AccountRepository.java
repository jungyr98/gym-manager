package com.potato.core.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.SubscribeUser;

@Repository
public interface AccountRepository extends JpaRepository<SubscribeUser, Integer> {

	Long countBySuId(String suId);
	SubscribeUser findBySuId(String suId);

}
