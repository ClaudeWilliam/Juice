package com.mysql.test;

import java.sql.Connection;

import jdbctools.JdbcTools;

public class TestConnection {
	public static void main(String[] args) {
		Connection connection=null;
		 connection=JdbcTools.getConnection();
		 System.out.println(connection);
	}
}
