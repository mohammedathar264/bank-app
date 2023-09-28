package com.cetpa.services;

import com.cetpa.models.Account;
import com.cetpa.models.User;

public interface UserService 
{
	int createAccount(User user);
	User getUser(String userid);
	int getAccount(String userid);
}
