package com.cetpa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.models.Account;
import com.cetpa.models.User;
import com.cetpa.repositories.AccountRepository;
import com.cetpa.repositories.UserRepository;
import com.cetpa.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;

	public int createAccount(User user) 
	{
		userRepository.save(user);
		Account account=new Account();
		account.setUserid(user.getUserid());
		accountRepository.save(account);
		return account.getAccountNo();
	}
	public User getUser(String userid) 
	{
		return userRepository.findById(userid).orElse(null);
	}
	public int getAccount(String userid) 
	{
		Account ac=accountRepository.findByUserid(userid);
		return ac.getAccountNo();
	}
}
