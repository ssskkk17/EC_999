package com.diworksdev.template999.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.template999.dto.LoginDTO;
import com.diworksdev.template999.util.DBConnector;

public class LoginDAO {
	
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		LoginDTO loginDTO = new LoginDTO();
		String sql = "select * from login_user_transaction where login_id = ? and login_pass = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginPassword);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				loginDTO.setLoginPassword(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));
				
				if (resultSet.getString("login_id") != null) {
					loginDTO.setLoginFlg(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}
}