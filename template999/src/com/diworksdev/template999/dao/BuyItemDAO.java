package com.diworksdev.template999.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.template999.dto.BuyItemDTO;
import com.diworksdev.template999.util.DBConnector;

public class BuyItemDAO {
	
	public BuyItemDTO getBuyItemInfo() {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		BuyItemDTO buyItemDTO = new BuyItemDTO();
		
		String sql = "select id, item_name, item_price from item_info_transaction";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buyItemDTO;
	}
}