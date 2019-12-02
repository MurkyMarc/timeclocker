package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.beans.Timesheet;

public class TimesheetDAO {

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeclocker", "root", "root");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Timesheet> findByUserId(int id) throws SQLException {

		Connection conn = getConnection();

		LinkedList<Timesheet> results = new LinkedList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement("select * from timesheet where userid = ?");
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();

			while (result.next()) {

				// call the small constructor because there are many instance variables
				Timesheet timesheet = new Timesheet(result.getInt("timesheetid"));

				// set the rest of the fields
				timesheet.setMonday(result.getFloat("monday"));
				timesheet.setTuesday(result.getFloat("tuesday"));
				timesheet.setWednesday(result.getFloat("wednesday"));
				timesheet.setThursday(result.getFloat("thursday"));
				timesheet.setFriday(result.getFloat("friday"));
				timesheet.setSaturday(result.getFloat("saturday"));
				timesheet.setSunday(result.getFloat("sunday"));
				timesheet.setWeekEndDate(result.getString("weekenddate"));
				timesheet.setStatusId(result.getInt("statusid"));
				timesheet.setUserId(id);

				results.add(timesheet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return results;
	}

	public List<Timesheet> findAllEmployees() throws SQLException {

		Connection conn = getConnection();

		LinkedList<Timesheet> results = new LinkedList<>();
		try {
			ResultSet resultSet = conn
					.prepareStatement(
							"select timesheetid, monday, tuesday, wednesday, thursday, friday, saturday, sunday, "
									+ "weekenddate, statusid, timesheet.userid from timesheet "
									+ "inner join user on timesheet.UserId = user.UserId "
									+ "inner join role on user.roleid = role.roleid "
									+ "where role.roleid = 2 order by user.userid asc, TimesheetId asc;")
					.executeQuery();

			while (resultSet.next()) {
				// call the small constructor because there are many instance variables
				Timesheet timesheet = new Timesheet(resultSet.getInt("timesheetid"));

				// set the rest of the fields
				timesheet.setMonday(resultSet.getFloat("monday"));
				timesheet.setTuesday(resultSet.getFloat("tuesday"));
				timesheet.setWednesday(resultSet.getFloat("wednesday"));
				timesheet.setThursday(resultSet.getFloat("thursday"));
				timesheet.setFriday(resultSet.getFloat("friday"));
				timesheet.setSaturday(resultSet.getFloat("saturday"));
				timesheet.setSunday(resultSet.getFloat("sunday"));
				timesheet.setWeekEndDate(resultSet.getString("weekenddate"));
				timesheet.setStatusId(resultSet.getInt("statusid"));
				timesheet.setUserId(resultSet.getInt("userid"));

				results.add(timesheet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return results;
	}

	public void update(Timesheet timesheet) throws SQLException {

		Connection conn = getConnection();

		try {
			if (timesheet.getStatusId() < 1) {
				conn.setAutoCommit(false);
				String sql = "update Timesheet set Monday = ?, Tuesday = ?,  Wednesday = ?,  Thursday = ?,  "
						+ "Friday = ?,  Saturday = ?,  Sunday = ?, WeekEndDate = ?  where TimesheetId = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setFloat(1, timesheet.getMonday());
				stmt.setFloat(2, timesheet.getTuesday());
				stmt.setFloat(3, timesheet.getWednesday());
				stmt.setFloat(4, timesheet.getThursday());
				stmt.setFloat(5, timesheet.getFriday());
				stmt.setFloat(6, timesheet.getSaturday());
				stmt.setFloat(7, timesheet.getSunday());
				stmt.setString(8, timesheet.getWeekEndDate());
				stmt.setInt(9, timesheet.getTimesheetId());

				stmt.executeUpdate();
				conn.commit();
			} else if (timesheet.getStatusId() > 0) {
				conn.setAutoCommit(false);
				String sql = "update Timesheet set statusid = ? where TimesheetId = ?";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, timesheet.getStatusId());
				stmt.setInt(2, timesheet.getTimesheetId());

				stmt.executeUpdate();
				conn.commit();
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public Timesheet save(Timesheet timesheet) throws SQLException {
		Connection conn = getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO TIMESHEET VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[] { "TimesheetId" });

			// TimesheetId`, `Monday`, `Tuesday`, `Wednesday`, `Thursday`, `Friday`,
			// `Saturday`, `Sunday`, `WeekEndDate`, `StatusId`, `UserId

			System.out.println("one");
			System.out.println(timesheet.getMonday());

			stmt.setFloat(1, timesheet.getMonday());
			stmt.setFloat(2, timesheet.getTuesday());
			stmt.setFloat(3, timesheet.getWednesday());
			stmt.setFloat(4, timesheet.getThursday());
			stmt.setFloat(5, timesheet.getFriday());
			stmt.setFloat(6, timesheet.getSaturday());
			stmt.setFloat(7, timesheet.getSunday());
			stmt.setString(8, timesheet.getWeekEndDate());

			// set status id and user id
			stmt.setInt(9, 1);
			stmt.setInt(10, timesheet.getUserId());

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();

			while (keys.next()) {
				int timesheetId = keys.getInt(1);
				timesheet.setTimesheetId(timesheetId);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return timesheet;
	}

	public void delete(int id) throws SQLException {
		Connection conn = getConnection();

		String sql = "DELETE FROM TIMESHEET WHERE TIMESHEETID = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
