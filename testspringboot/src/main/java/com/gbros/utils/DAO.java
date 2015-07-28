package com.gbros.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;

@Repository
public class DAO {
	@Autowired
	private DruidDataSource dataSource;

	public void startTransation(Connection connection) {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("开始事务失败");
		}

	}

	public static void endTransation(Connection connection) {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("提交事务失败");
		}
	}

	public static void rollbackTransation(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("回滚事务失败");
		}
	}

	public <T> List<T> selectList(Class<T> clazz, String sql, Object... args) {
		List<T> lists = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Object> maps = null;
		T object = null;
		try {
			connection = dataSource.getConnection();
			startTransation(connection);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			endTransation(connection);
			List<HashMap<String, Object>> datalist = new ArrayList<HashMap<String, Object>>();
			while (resultSet.next()) {
				maps = new HashMap<String, Object>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String ColumnName = metaData.getColumnLabel(i + 1);
					Object ColumnValue = resultSet.getObject(ColumnName);
					maps.put(ColumnName, ColumnValue);
				}
				for (Entry<String, Object> entry : maps.entrySet()) {

					String FieldName = entry.getKey();
					Object FieldValue = entry.getValue();
					maps.put(FieldName, FieldValue);
				}
				datalist.add((HashMap<String, Object>) maps);
			}
			if (datalist.size() > 0) {
				for (Map<String, Object> m : datalist) {
					object = clazz.newInstance();
					for (Entry<String, Object> entry : m.entrySet()) {
						String propety = entry.getKey();
						Object value = entry.getValue();
						BeanUtils.setProperty(object, propety, value);

					}
					lists.add(object);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			relase(connection, preparedStatement, resultSet);
		}

		return lists;
	}

	public <T> T select(Class<T> clazz, String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		T object=null;
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			connection = dataSource.getConnection();
			startTransation(connection);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				object= clazz.newInstance();
				ResultSetMetaData metaData = resultSet.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String ColumnName = metaData.getColumnName(i + 1);
					Object ColumnValue = resultSet.getObject(ColumnName);
					maps.put(ColumnName, ColumnValue);
					endTransation(connection);
				}
				try {
					for (Entry<String, Object> entry : maps.entrySet()) {
						
						String FiledName = entry.getKey();
						Object FiledValue = entry.getValue();
						System.out.println(FiledName);
						System.out.println(FiledValue);
						BeanUtils.setProperty(object, FiledName, FiledValue);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out
							.println("---------------set bean failed-------------");
				} 
			}
			System.out.println(maps);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-------------select failed------------");
			rollbackTransation(connection);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			relase(connection, preparedStatement, resultSet);
		}

		return object;

	}

	public void delete(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			startTransation(connection);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			endTransation(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("--------------------delete failed-------------------------");
			rollbackTransation(connection);
		} finally {
			relase(connection, preparedStatement, null);
		}
	}

	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			startTransation(connection);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			endTransation(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("--------------------update failed-------------------------");
			rollbackTransation(connection);
		} finally {
			relase(connection, preparedStatement, null);
		}

	}

	public void insert(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			startTransation(connection);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			endTransation(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("--------------------insert failed-------------------------");
			rollbackTransation(connection);
		} finally {
			relase(connection, preparedStatement, null);
		}
	}

	public void relase(Connection connection, Statement statement,
			ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out
						.println("-----------connection close failed-------------");
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out
						.println("-----------statement close failed-------------");
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out
						.println("-----------resultset close failed-------------");
			}
		}
	}
}
