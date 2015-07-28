package com.gbros.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gbros.jdbc.JdbcTools;
import com.gbros.modle.User;

public class UserDAO {
	public static User getuser() {

		Connection connection = JdbcTools.getConnection();
		System.out.println("---------" + connection);
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		User user = new User();
		String sql = "select * from user;";
		try {
			preparedStatement = (PreparedStatement) connection
					.prepareStatement(sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
			}
			System.out.println(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

}
