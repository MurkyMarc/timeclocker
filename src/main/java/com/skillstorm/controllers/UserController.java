package com.skillstorm.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

				// storing the userid, name, and role in the cookie

//				Cookie[] cookies = req.getCookies();

//				if (cookies != null) {
//					for (Cookie cookie : cookies) {
//						if (cookie.getName().equals("UserId")) {
//							System.out.println("Replacing old cookie value: " + cookie.getValue());
//							continue;
//						} else if (cookie.getName().equals("F_name")) {
//							
//						} else if (cookie.getName().equals("L_name")) {
//							
//						} else if (cookie.getName().equals("RoleId")) {
//							
//						}
//					}
//				}

				Cookie userid_cookie = new Cookie("UserId", String.valueOf(user.getUserId()));
				Cookie fname_cookie = new Cookie("F_name", String.valueOf(user.getF_name()));
				Cookie lname_cookie = new Cookie("L_name", String.valueOf(user.getL_name()));
				Cookie roleid_cookie = new Cookie("RoleId", String.valueOf(user.getRoleId()));

				userid_cookie.setMaxAge(1_800);
				fname_cookie.setMaxAge(1_800);
				lname_cookie.setMaxAge(1_800);
				roleid_cookie.setMaxAge(1_800);

				resp.addCookie(userid_cookie);
				resp.addCookie(fname_cookie);
				resp.addCookie(lname_cookie);
				resp.addCookie(roleid_cookie);

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

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		Cookie userid_cookie = new Cookie("UserId", "");
		Cookie fname_cookie = new Cookie("F_name", "");
		Cookie lname_cookie = new Cookie("L_name", "");
		Cookie roleid_cookie = new Cookie("RoleId", "");

		userid_cookie.setMaxAge(0);
		fname_cookie.setMaxAge(0);
		lname_cookie.setMaxAge(0);
		roleid_cookie.setMaxAge(0);

		resp.addCookie(userid_cookie);
		resp.addCookie(fname_cookie);
		resp.addCookie(lname_cookie);
		resp.addCookie(roleid_cookie);
		
		resp.sendRedirect("/timeclocker/");
	}
}
