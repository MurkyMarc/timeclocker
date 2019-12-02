package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.skillstorm.beans.User;

public class UserDAO {

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timeclocker", "root", "root");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public User findUserByLogin(String username, String password) throws SQLException {

		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from user where username = ? and password = ?");
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet results = stmt.executeQuery();

		User user = new User();

		if (results.next()) {
			// the cursor begins by pointing at null. must call results.next() to point at
			// the first actual entry.
			// ALWAYS have to call results.next even when working on 1 field
			user.setUserId(results.getInt(1));
			user.setF_name(results.getString(2));
			user.setL_name(results.getString(3));
			user.setTitle(results.getString(6));
			user.setRoleId(results.getInt(7));

//			conn.close();
		}
		conn.close();
		return user;
	}
}
