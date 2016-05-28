package com.order.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.order.GUI.Impl.RobotInterface;
import com.order.menu.GreetMenu;

/**
 * 界面
 */
public class RobotGUI extends JFrame implements RobotInterface{
	
	/**
	 * 初始化界面
	 */
	public RobotGUI(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * 欢迎界面
	 */
	@Override
	public void welcome() {
		// TODO Auto-generated method stub
		Font font = new Font("宋体",Font.PLAIN,40);      //字体
		
		JPanel jp = new JPanel();                       //绘制面板
		jp.setLayout(null);
		jp.setBackground(new Color(197,228,251));       //设置颜色
		
		String[] greets = GreetMenu.greet;                            //绘制问候语
		String greet = greets[new Random().nextInt(greets.length)];
		JTextArea ar = new JTextArea(greet);
		ar.setFont(font);
		ar.setBackground(new Color(197,228,251));
		ar.setEditable(false);
		ar.setBounds(100, 150, 800, 200);
		
		JButton jb = new JButton("进入选餐");
		jb.setBounds(100, 400, 150, 50);
		jb.setFont(new Font("宋体",Font.PLAIN,26));
		jb.addActionListener(new ActionListener() {             //触发点击事件后进入食品类别列表界面
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderList();
			}
		});
		
		jp.add(ar);
		jp.add(jb);
		
		this.add(jp);
		this.setTitle("欢迎");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * 食品种类界面
	 */
	@Override
	public void orderList() {
		// TODO Auto-generated method stub
		this.setVisible(false);       //设置窗口不可见
		this.removeAll();             //移除所有组件
		
		//重新布局
		//code
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		RobotGUI gui = new RobotGUI();
		gui.welcome();
	}
}
