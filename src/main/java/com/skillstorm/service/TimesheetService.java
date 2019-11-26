package com.skillstorm.service;

import java.sql.SQLException;
import java.util.List;
import com.skillstorm.beans.Timesheet;
import com.skillstorm.data.TimesheetDAO;

public class TimesheetService {

	private TimesheetDAO timesheetDAO = new TimesheetDAO();

	public List<Timesheet> findByUserId(int id) {
		try {
			return timesheetDAO.findByUserId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Timesheet> findAllEmployeeTimesheets() {
		try {
			return timesheetDAO.findAllEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Timesheet saveTimesheet(Timesheet t) {
		try {
			return timesheetDAO.save(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateTimesheet(Timesheet t) {
		try {
			timesheetDAO.update(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteTimesheet(int id) {
		try {
			timesheetDAO.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
