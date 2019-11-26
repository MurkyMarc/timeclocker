package com.skillstorm.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.skillstorm.beans.Timesheet;
import com.skillstorm.service.TimesheetService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TimesheetController {

	private TimesheetService timesheetService = new TimesheetService();

	// GET /timeclocker/api/timesheet?id=1
	public void getTimesheet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		// get all timesheets for one user
		if (req.getParameter("id") != null) {
			resp.getWriter().println(new ObjectMapper()
					.writeValueAsString(timesheetService.findByUserId(Integer.parseInt(req.getParameter("id")))));
		} else if (Integer.parseInt(req.getParameter("role")) == 2) {
			
			// get all timesheets for users with the role of employee. 
			// can only be called by users with the manager role. manager role code is 2
			resp.getWriter().println(new ObjectMapper()
					.writeValueAsString(timesheetService.findAllEmployeeTimesheets()));
		}
	}

	// POST /timeclocker/api/timesheet
	public void postTimesheet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		resp.getWriter().println(new ObjectMapper().writeValueAsString(
				timesheetService.saveTimesheet(new ObjectMapper().readValue(req.getInputStream(), Timesheet.class))));

		resp.setStatus(201);
	}

	// PUT /timeclocker/api/timesheet
	public void putTimesheet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		timesheetService.updateTimesheet(new ObjectMapper().readValue(req.getInputStream(), Timesheet.class));

		resp.setStatus(204);

	}

	// DELETE /timeclocker/api/timesheet?id=1
	public void deleteTimesheet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		timesheetService.deleteTimesheet(Integer.parseInt(req.getParameter("id")));

		resp.setStatus(204);
	}

}
