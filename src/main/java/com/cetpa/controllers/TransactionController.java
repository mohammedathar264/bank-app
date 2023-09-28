package com.cetpa.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.models.Account;
import com.cetpa.models.User;
import com.cetpa.services.TransactionService;
import com.cetpa.services.UserService;

@Controller
@RequestMapping("cetpa-bank/transaction")
public class TransactionController 
{
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("show-balance-amount")
	public String getCurrentBalanceView(Model model,HttpSession ses)
	{
		if(ses.getAttribute("name")==null)
		{
			return "user/user-login";
		}
		Account account=transactionService.getAccount((Integer)ses.getAttribute("accountNo"));
		int amount=account.getAmount();
		model.addAttribute("amount",amount);
		return "transaction/show-balance";
	}
	@RequestMapping("deposit-amount")
	public String getDepositAmountView(HttpSession ses)
	{
		if(ses.getAttribute("name")==null)
		{
			return "user/user-login";
		}
		return "transaction/deposit-money";
	}
	@RequestMapping("update-deposit-amount")
	public String updateDepositAmount(int amount,Model model,HttpSession ses)
	{
		transactionService.updateDeposit(amount,(Integer)ses.getAttribute("accountNo"));
		model.addAttribute("amount",amount);
		return "transaction/deposit-success";
	}
	@RequestMapping("withdraw-amount")
	public String getWithdrawAmountView(HttpSession ses)
	{
		if(ses.getAttribute("name")==null)
		{
			return "user/user-login";
		}
		return "transaction/withdraw-money";
	}
	@RequestMapping("update-withdraw-amount")
	public String updateWithdrawAmount(int wamount,Model model,HttpSession ses)
	{
		Account account=transactionService.getAccount((Integer)ses.getAttribute("accountNo"));
		int camount=account.getAmount();
		model.addAttribute("amount",wamount);
		if(wamount>camount)
		{
			model.addAttribute("msg","Sorry!!! you do not have sufficient amount");
			return "transaction/withdraw-money";
		}
		transactionService.updateWithdraw(wamount,(Integer)ses.getAttribute("accountNo"));
		return "transaction/withdraw-success";
	}
	@RequestMapping("beneficiary-accountno")
	public String getBeneficiaryAccountNoView(HttpSession ses)
	{
		if(ses.getAttribute("name")==null)
		{
			return "user/user-login";
		}
		return "transaction/accept-account-no";
	}
	@RequestMapping("transfer-amount")
	public String getTransferAmountView(int accountNo,HttpSession ses,Model model)
	{
		Account account=transactionService.getAccount(accountNo);
		if(account==null)
		{
			model.addAttribute("msg","Entered account number does not exist");
			model.addAttribute("an",accountNo);
			return "transaction/accept-account-no";
		}
		User user=userService.getUser(account.getUserid());
		model.addAttribute("name",user.getName());
		return "transaction/transfer-money";
	}
}
