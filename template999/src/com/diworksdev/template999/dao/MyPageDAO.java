package com.diworksdev.template999.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.template999.dto.MyPageDTO;
import com.diworksdev.template999.util.DBConnector;

public class MyPageDAO {
	
	public MyPageDTO getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		MyPageDTO myPageDTO = new MyPageDTO();
		
		String sql = "select iit.item_name, ubit.total_price, ubit.total_count, ubit.pay from user_buy_item_transaction ubit left join item_info_transaction iit on ubit.item_transaction_id = iit.id where ubit.item_transaction_id = ? and ubit.user_master_id = ? order by ubit.insert_date desc";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				myPageDTO.setTotalPrice(resultSet.getString("total_price"));
				myPageDTO.setTotalCount(resultSet.getString("total_count"));
				myPageDTO.setPayment(resultSet.getString("pay"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return myPageDTO;
	}
	
	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		
		String sql = "delete from user_buy_item_transaction where item_transaction_id = ? and user_master_id = ?";
		PreparedStatement preparedStatement;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return result;
	}
}