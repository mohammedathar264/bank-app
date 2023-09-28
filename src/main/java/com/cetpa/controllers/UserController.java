package com.cetpa.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.models.Account;
import com.cetpa.models.User;
import com.cetpa.services.UserService;

@Controller
@RequestMapping("cetpa-bank/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String getLoginView()
	{
		return "user/user-login";
	}
	@RequestMapping("create-account")
	public String getRegistrationView()
	{
		return "user/registration";
	}
	@RequestMapping("registerme")
	public String registerUser(User user,Model model)
	{
		int accountNo=userService.createAccount(user);
		model.addAttribute("an",accountNo);
		model.addAttribute("name",user.getName());
		return "user/registration-success";
	}
	@RequestMapping("authenticate")
	public String authenticateUser(String userid,String password,Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response)
	{
		User user=userService.getUser(userid);
		model.addAttribute("userid",userid);
		if(user==null)
		{
			model.addAttribute("msg","Userid does not exist");
			return "user/user-login";
		}
		if(!user.getPassword().equals(password))
		{
			model.addAttribute("msg","Password is wrong");
			return "user/user-login";
		}
		//session.setMaxInactiveInterval(20);
		Cookie[] cookies=request.getCookies();
		for(Cookie co:cookies)
		{
			if(co.getName().equals("JSESSIONID"))
			{
				co.setMaxAge(365*24*60*60);
				co.setPath("/");
				response.addCookie(co);
			}
		}
		int accountNo=userService.getAccount(userid);
		session.setAttribute("name",user.getName());
		session.setAttribute("accountNo",accountNo);
		return "redirect:/cetpa-bank/home";
	}
	@RequestMapping("logout")
	public String logoutUser(Model model,HttpSession session)
	{
		model.addAttribute("name",(String)session.getAttribute("name"));
		session.invalidate();//code to destroy session object
		return "user/logout";
	}
}
