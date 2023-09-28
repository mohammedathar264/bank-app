package com.cetpa.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.models.Account;
import com.cetpa.models.TransactionSummary;
import com.cetpa.repositories.AccountRepository;
import com.cetpa.repositories.TransactionRepository;
import com.cetpa.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService 
{
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;

	public Account getAccount(Integer an) 
	{
		Account account=accountRepository.findById(an).orElse(null);
		return account;
	}
	public void updateDeposit(int amount,int accountNo) 
	{
		accountRepository.updateAmount(amount,accountNo);
		updateSummary("credit",accountNo,amount);
	}
	public void updateWithdraw(int wamount, Integer accountNo) 
	{
		accountRepository.updateAmount(-wamount,accountNo);
		updateSummary("debit",accountNo,wamount);
	} 
	private void updateSummary(String str,int an,int amount)
	{
		TransactionSummary summary=new TransactionSummary();
		summary.setAccountNo(an);
		summary.setAmount(amount);
		LocalTime tm=LocalTime.now();
		String time=tm.getHour()+":"+tm.getMinute()+":"+tm.getSecond();
		summary.setTime(time);
		summary.setDate(LocalDate.now().toString());
		summary.setTransactionType(str);
		transactionRepository.save(summary);
	}
}
