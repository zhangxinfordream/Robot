package com.order.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.PreparedStatement;
import com.order.db.DBConnection;

/**
 * 数据交互类
 * @author zhangxin
 * 
 */
public class RobotDao {
	private Connection conn;

	private void init(){
			conn = new DBConnection().getConnection();
	}
	
	/**
	 * 查询所有食品种类
	 * @return
	 */
	public ArrayList<HashMap<String, String>> findAllType(){
		init();
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from foodtype";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("type_id", rs.getString(1));
				map.put("type_name", rs.getString(2));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 查询选定类目下的所有食品
	 * @param food_id
	 * @return
	 */
	public ArrayList<HashMap<String, String>> findSomeFood(String food_id){
		init();
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select * from food where type_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, food_id);
			rs = ps.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("food_id", rs.getString(1));
				map.put("type_id", rs.getString(2));
				map.put("food_name", rs.getString(3));
				map.put("price", rs.getString(4));
				map.put("stock", rs.getString(5));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void deleteFood(String id) {
		init();
		
		String sql = "select stock from food where food_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String stock = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				stock = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		sql = "update food set stock = ? where food_id = ?";
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			String str = String.valueOf(Integer.valueOf(stock)-1);
			ps.setString(1, str);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
