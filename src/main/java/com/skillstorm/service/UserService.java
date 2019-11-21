package com.skillstorm.service;


import java.sql.SQLException;

import com.skillstorm.beans.User;
import com.skillstorm.data.UserDAO;

public class UserService {

	private UserDAO userDAO = new UserDAO();
	
	public User findByUserAndPass(String username, String password) {
		try {
			return userDAO.findUserByLogin(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
