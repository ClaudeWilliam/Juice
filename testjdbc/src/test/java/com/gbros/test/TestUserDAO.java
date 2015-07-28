package com.gbros.test;

import com.gbros.modle.User;
import com.gbros.tools.UserDAO;

public class TestUserDAO {
	public static void main(String[] args) {
		
		User user=UserDAO.getuser();
		System.out.println(user);
	}
}
