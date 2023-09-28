package com.cetpa.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cetpa.models.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> 
{
	Account findByUserid(String userid);
	@Modifying
	@Transactional
	@Query("update Account set amount=amount+:arg1 where accountNo=:arg2")
	void updateAmount(@Param("arg1") int amount,@Param("arg2") int accountNo);
}
