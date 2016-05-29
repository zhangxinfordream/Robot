package com.order.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.order.dao.RobotDao;

public class RobotService {
	private RobotDao rd;
	
	private void init(){
		if(rd == null){
			rd = new RobotDao();
		}
	}
	
	public ArrayList<HashMap<String, String>> findAllType(){
		init();
		return rd.findAllType();
	}

	public ArrayList<HashMap<String, String>> findSomeFood(String food_id){
		init();
		return rd.findSomeFood(food_id);
	}

	public void deleteFood(String id) {
		rd.deleteFood(id);
	}
}
