package com.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreetingServlet extends HttpServlet {

	private static final long serialVersionUID = 3706707075834617854L;
// servlet olusturmak icin uc asama var  bir init iki doGet uc destroy 
	
	// web.xml de orda tanimlama yapmamiz lazim 
	private String message;
	
	@Override
	public void init() throws ServletException { // burda servlet ini kurucusu olarak dusunebiliriz init 

		message="hello I am  a servlet ";
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //doGet adin ustude 
		
		String name=req.getParameter("name");
		String lastname =req.getParameter("lastname");
		
		
		if ("habib".equals(name) && "dabbag".equals(lastname)) {
			resp.setContentType("text/plain;charset=UTF-8");
			PrintWriter writer=resp.getWriter();
			writer.print(message);
		}else {
			
			resp.setContentType("text/plain;charset=UTF-8");
			PrintWriter write=resp.getWriter();
			write.print("can not access");
		}
	}

	
	@Override
	public void destroy() {// yikilma 
		message=null;
	
	}
}
