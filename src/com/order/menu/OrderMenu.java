package com.order.menu;

import java.util.ArrayList;

/**
 * 已点菜品目录
 */
public class OrderMenu {

	private ArrayList<Integer> order;        //用户点的菜品的id保存在这个数组中

	public void init(){
		order = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getOrder(){
		if(order==null){
			init();
		}
		return this.order;
	}
}
