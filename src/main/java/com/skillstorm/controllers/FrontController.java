package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	private UserController userController = new UserController();
	private TimesheetController timesheetController = new TimesheetController();

	protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		switch (uri) {
		case "/timeclocker/api/login":
			if (req.getMethod().equals("POST")) {
				userController.getUser(req, resp);
				return;
			}
			/*if (req.getMethod().equals("GET")) {
				userController.getUser(req, resp);
				return;
			}
			if (req.getMethod().equals("PUT")) {
				userController.putUser(req, resp);
				return;
			}
			if (req.getMethod().equals("DELETE")) {
				userController.deleteUser(req, resp);
				return;
			}*/
		case "/timeclocker/api/timesheet":
			if (req.getMethod().equals("GET")) {
				timesheetController.getTimesheet(req, resp);
				return;
			}
			if (req.getMethod().equals("POST")) {
				timesheetController.postTimesheet(req, resp);
				return;
			}
			if (req.getMethod().equals("PUT")) {
				timesheetController.putTimesheet(req, resp);
				return;
			}
			
			if (req.getMethod().equals("DELETE")) {
				timesheetController.deleteTimesheet(req, resp);
				return;
			}
		case "/timeclocker/api/route-1":
			req.getRequestDispatcher("/one.html").forward(req, resp);
			return;
		case "/timeclocker/api/route-2":
			req.getRequestDispatcher("/timesheets.html").forward(req, resp);
			return;

		default:
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Front Controller Initialized!");
	}

}

//public class FrontController extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("Dispatcher received url: " + req.getRequestURL());
//		
//		switch(req.getRequestURI()) {
//		case "/timeclocker/api/route-1":
//			req.getRequestDispatcher("/one.html").forward(req,  resp);
//			break;
//		case "/timeclocker/api/route-2" :
//			req.getRequestDispatcher("/timesheets.html").forward(req,  resp);
//			break;
//		default:
//			break;
//		}
//		
//	}
//}
