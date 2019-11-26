package com.skillstorm.beans;

public class Timesheet {

	private int timesheetId;
	private float monday;
	private float tuesday;
	private float wednesday;
	private float thursday;
	private float friday;
	private float saturday;
	private float sunday;
	private String weekEndDate;
	private int statusId;
	private int userId;

	public Timesheet() {}
	
	public Timesheet(int timesheetId) {
		super();
		this.timesheetId = timesheetId;
	}
	
	public Timesheet(int timesheetId, int statusId) {
		super();
		this.timesheetId = timesheetId;
		this.statusId = statusId;
	}
	
	public Timesheet(float monday, float tuesday, float wednesday, float thursday, float friday, float saturday,
			float sunday, String weekEndDate, int statusId, int userId) {
		super();
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.weekEndDate = weekEndDate;
		this.statusId = statusId;
		this.userId = userId;
	}
	
	public Timesheet(float monday, float tuesday, float wednesday, float thursday, float friday, float saturday,
			float sunday, String weekEndDate, int statusId) {
		super();
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
		this.weekEndDate = weekEndDate;
		this.statusId = statusId;
	}
	
	public int getTimesheetId() {
		return timesheetId;
	}
	
	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}


	public float getMonday() {
		return monday;
	}

	public void setMonday(float monday) {
		this.monday = monday;
	}

	public float getTuesday() {
		return tuesday;
	}

	public void setTuesday(float tuesday) {
		this.tuesday = tuesday;
	}

	public float getWednesday() {
		return wednesday;
	}

	public void setWednesday(float wednesday) {
		this.wednesday = wednesday;
	}

	public float getThursday() {
		return thursday;
	}

	public void setThursday(float thursday) {
		this.thursday = thursday;
	}

	public float getFriday() {
		return friday;
	}

	public void setFriday(float friday) {
		this.friday = friday;
	}

	public float getSaturday() {
		return saturday;
	}

	public void setSaturday(float saturday) {
		this.saturday = saturday;
	}

	public float getSunday() {
		return sunday;
	}

	public void setSunday(float sunday) {
		this.sunday = sunday;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timesheet other = (Timesheet) obj;
		if (friday != other.friday)
			return false;
		if (monday != other.monday)
			return false;
		if (saturday != other.saturday)
			return false;
		if (statusId != other.statusId)
			return false;
		if (sunday != other.sunday)
			return false;
		if (thursday != other.thursday)
			return false;
		if (timesheetId != other.timesheetId)
			return false;
		if (tuesday != other.tuesday)
			return false;
		if (userId != other.userId)
			return false;
		if (wednesday != other.wednesday)
			return false;
		if (weekEndDate == null) {
			if (other.weekEndDate != null)
				return false;
		} else if (!weekEndDate.equals(other.weekEndDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timesheet [weekEndDate=" + weekEndDate + "]";
	}
}
