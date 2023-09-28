package com.cetpa.services;

import com.cetpa.models.Account;

public interface TransactionService 
{
	Account getAccount(Integer an);
	void updateDeposit(int amount,int accountNo);
	void updateWithdraw(int wamount, Integer attribute);
}
