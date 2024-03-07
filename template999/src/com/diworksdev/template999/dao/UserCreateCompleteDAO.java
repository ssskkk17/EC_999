package com.diworksdev.template999.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diworksdev.template999.util.DateUtil;

public class UserCreateCompleteDAO {
	
	private DateUtil dateUtil = new DateUtil();
	private String sql = "insert into login_user_transaction(login_id, login_pass, user_name, insert_date) values(?,?,?,?)";
	
	public void createUser(String loginUserId, String loginUserPassword, String userName) throws SQLException {
		com.diworksdev.template999.util.DBConnector dbConnector = new com.diworksdev.template999.util.DBConnector();
		Connection connection = dbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginUserPassword);
			preparedStatement.setString(3, userName);
			
			preparedStatement.setString(4, dateUtil.getDate());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}