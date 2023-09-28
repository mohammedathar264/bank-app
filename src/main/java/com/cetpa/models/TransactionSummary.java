package com.cetpa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TransactionSummary
{
	@Id
	@GeneratedValue(generator = "tran_seq")
	@SequenceGenerator(name = "tran_seq",initialValue = 101,allocationSize = 1)
	private int trid;
	private int accountNo;
	private int amount;
	private String date;
	private String time;
	private String transactionType;
	public int getTrid() {
		return trid;
	}
	public void setTrid(int trid) {
		this.trid = trid;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
}
