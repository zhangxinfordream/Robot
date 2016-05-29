package com.order.menu;

import java.util.ArrayList;

/**
 * 已点菜品目录
 */
public class OrderMenu {

	public static ArrayList<String> order = new ArrayList<String>();        //用户点的菜品的id保存在这个数组中
	public static int consumption = 0;                                      //消费总额
	public static ArrayList<String> soldOut = new ArrayList<String>();      //卖光了的菜
}
