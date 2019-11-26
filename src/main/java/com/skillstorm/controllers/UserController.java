package com.skillstorm.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.skillstorm.beans.User;
import com.skillstorm.service.UserService;

public class UserController {

	private UserService userService = new UserService();

	// GET /timeclocker/api/user?id=1
	public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// login functionality. checks if a username and password were input
		if (username.length() > 0 && password.length() > 0) {

			User user = userService.findByUserAndPass(username, password);

			if (user.getF_name() == null) {
				resp.sendRedirect("/timeclocker/api/try-again");
			} else {

				// COOKIE AND SESSION LOGIC

				resp.sendRedirect("/timeclocker/api/timesheets");
			}

		} else {

			if (username.equals(null) || password.equals(null)) {
				resp.sendRedirect("/timeclocker/api/missing-something");
			} else {
				resp.sendRedirect("/timeclocker/api/missing-something");
			}

		}
	}
}
