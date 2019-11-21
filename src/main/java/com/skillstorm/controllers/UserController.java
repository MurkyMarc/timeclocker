package com.skillstorm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.service.UserService;

public class UserController {

	private UserService userService = new UserService();

	// GET /timeclocker/api/user?id=1
	public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		// login functionality. checks if a username and password were input 
		if (req.getParameter("username").length() > 0 && req.getParameter("password").length() > 0) {
			// add input validation
			
			resp.getWriter().println(new ObjectMapper()
					.writeValueAsString(userService.findByUserAndPass(req.getParameter("username"), req.getParameter("password"))));
		} else {
			
			// write directly to the HTTP body
			PrintWriter out = resp.getWriter();
			
			out.println("<script>alert(\"Hello! I am an alert box!!\");</script>");
			
			// declare MIME type - this is so it can work on safari
			resp.setContentType("text/html");

			return;
		}
	}
}
