package com.servlet.demo.annotion;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.demo.annotion.model.User;

@WebServlet(name = "AccountManager", urlPatterns = "/user/account", loadOnStartup = 0) // burda servlet oldu hemde web
																						// xml de tanimmadan
// java nin sondugu annotion  

public class ManagerAccount extends HttpServlet {

	private static final long serialVersionUID = -2017975795588355130L;

	private int userIdConuter;
	private List<User> users;
	
	@Override
	public void init() throws ServletException {
		
		userIdConuter=0;
		users=Collections.synchronizedList(new ArrayList<User>());// collections sychronized
		
	}
	
	@Override
	public void destroy() {
	
	users.clear();// clear yapiyoruz  null a cekmiyoruz 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		loadUserAccountList(resp);
		
	}
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

	private void loadUserAccountList(HttpServletResponse resp) throws IOException{
		
		resp.sendRedirect("../user_account.html");
		
	}
	

}
