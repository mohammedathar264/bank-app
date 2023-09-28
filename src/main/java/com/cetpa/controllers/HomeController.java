package com.cetpa.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cetpa-bank/home")
public class HomeController 
{
	@RequestMapping("")
	public String getHomeView(HttpSession session)
	{
		if(session.getAttribute("name")==null)
		{
			return "user/user-login";
		}
		return "home/home";
	}
}
