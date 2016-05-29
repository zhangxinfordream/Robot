package com.order.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.order.GUI.Impl.RobotInterface;
import com.order.menu.GreetMenu;
import com.order.menu.OrderMenu;
import com.order.service.RobotService;

/**
 * 界面
 */
public class RobotGUI extends JFrame implements RobotInterface{
	private RobotService rs;
	private JPanel jp;
	private final Color[] col = new Color[]{new Color(0,191,255), new Color(250,250,210), 
			new Color(0,255,255), new Color(143,188,143), new Color(255,182,193), new Color(176,196,222)};
	
	private String id;
	private int price;
	
	private void init(){
		if(rs == null){
			rs = new RobotService();
		}
	}
	
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
		this.setVisible(false);
		if(jp!=null){
			jp.removeAll();
		}else{
			jp = new JPanel();
		}
		
		Font font = new Font("宋体",Font.PLAIN,40);      //字体

		jp.setLayout(null);
		jp.setBackground(new Color(135,206,250));       //设置颜色
		
		String[] greets = GreetMenu.greet;                            //绘制问候语
		String greet = greets[new Random().nextInt(greets.length)];
		JTextArea ar = new JTextArea(greet);
		ar.setFont(font);
		ar.setBackground(new Color(135,206,250));
		ar.setEditable(false);
		ar.setBounds(180, 170, 800, 200);
		
		jp.add(ar);
		
		jp.updateUI();
		jp.repaint();
		this.add(jp);
		this.setTitle("welcome");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderList(true);
	}
	
	/**
	 * 食品种类界面
	 * @param jp2 
	 */
	@Override
	public void orderList(boolean wrong) {
		// TODO Auto-generated method stub
		init();
		final ArrayList<HashMap<String, String>> typeList = rs.findAllType();   //所有的食品种类
		
		//重新布局
		this.setVisible(false);       //设置窗口不可见
		jp.removeAll();
		int x = 5;
		int y = 35;
		int count = 1;
		for(HashMap<String, String> map:typeList){      
			JButton jb = new JButton("<html>"+count+"<br>"+map.get("type_name")+"</html>");
			Color color = col[count-1];
			jb.setBackground(color);
			jb.setFont(new Font("宋体",Font.PLAIN,18));

			if(x>555){ 
				x = 5;
				y += 142;
			}
			jb.setBounds(x, y, 256, 140);
			x += 258;
			count++;
			
			jp.add(jb);
		}

		Font fo = new Font("宋体",Font.PLAIN,20);
		Color co = new Color(135,206,250);
		JTextArea jt2 = null;
		if(wrong){
			jt2 = new JTextArea("Please select an option"+":");
		}else{
			jt2 = new JTextArea("The choice is not available.Please select again"+":");
		}
		JTextArea jt1 = new JTextArea("Option selected"+":");
		JTextArea jt3 = new JTextArea();
		jt2.setBounds(5, 5, 600, 25);
		jt1.setBounds(295, 350, 160, 25);
		jt3.setBounds(457, 356, 25, 16);
		
		jt1.setFont(fo);
		jt2.setFont(fo);
		jt2.setEditable(false);
		jt1.setEditable(false);
		jt1.setBackground(co);
		jt2.setBackground(co);
		
		jt3.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String order = null;
				boolean flag = true;
                try {
                	order = e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), 
                    		e.getDocument().getLength());
                    HashMap<String, String> map = null;
    				try{
    					map = typeList.get(Integer.valueOf(order)-1);
    				} catch(NumberFormatException ee){
    					flag = false;
    				} catch(ArrayIndexOutOfBoundsException eee){
    					flag = false;
    				} catch(IndexOutOfBoundsException eeee){
    					flag = false;
    				}
    				if(flag){
    					orderDetails(map.get("type_id"), 1);
    				}else{
    					orderList(false);
    				}
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		jp.updateUI();
		jp.repaint();
		this.setTitle("please choose type");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * 选择某一样具体的食品
	 */
	@Override
	public void orderDetails(final String type_id, final int operate) {
		// TODO Auto-generated method stub
		init();
		ArrayList<HashMap<String, String>> detailsList = rs.findSomeFood(type_id);
		
		this.setVisible(false);       //设置窗口不可见
		jp.removeAll();
		
		ArrayList<Integer> soldOutList = new ArrayList<Integer>();
		int count_ = 0;
		for(HashMap<String, String> map:detailsList){
			for(String soldout:OrderMenu.soldOut){
				if(soldout.equals(map.get("food_id"))){
					soldOutList.add(count_);
				}
			}
			count_++;
		}
		
		ArrayList<HashMap<String, String>> tempList = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<detailsList.size();i++){
			boolean flag = false;
			for(int j:soldOutList){
				if(j==i){
					flag = true;
					break;
				}
			}
			if(flag){
				continue;
			}
			tempList.add(detailsList.get(i));
		}
		
		final ArrayList<HashMap<String, String>> list = tempList;
		int x = 5;
		int y = 35;
		int count = 1;
		for(HashMap<String, String> map:list){
			boolean flag = false;
			JButton jb = jb = new JButton("<html>"+count+"<br>"+map.get("food_name")+"<br>"+"（￥"+map.get("price")+"）</html>");

			Color color = col[count-1];
			jb.setBackground(color);
			jb.setFont(new Font("宋体",Font.PLAIN,14));

			if(x>555){ 
				x = 5;
				y += 142;
			}
			jb.setBounds(x, y, 256, 140);
			x += 258;
			count++;
			
			jp.add(jb);
			if(count>6){
				break;
			}
		}
		
		Font fo = new Font("宋体",Font.PLAIN,18);
		Color co = new Color(135,206,250);
		JTextArea jt2 = null;
		if(operate==1){
			jt2 = new JTextArea("Please select an option"+":");
			jt2.setBounds(5, 5, 600, 25);
		}else if(operate==2){
			jt2 = new JTextArea("The choice is not available.Please select again"+":");
			jt2.setBounds(5, 5, 600, 25);
		}else if(operate==3){
			//确认订单
			jt2 = new JTextArea("Are you sure to order it"+"?");
			jt2.setBounds(226, 5, 225, 25);
			
			JButton button1 = new JButton("yes");
			JButton button2 = new JButton("no");
			button1.setBounds(460, 7, 55, 20);
			button2.setBounds(517, 7, 55, 20);
			
			//处理点击事件
			button1.addActionListener(new ActionListener() {      //下单
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rs.deleteFood(id);
					OrderMenu.order.add(id);
					OrderMenu.consumption += price;
					confirmOrder();
				}
			});
			
			button2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					orderDetails(type_id, 1);
				}
			});
			
			jp.add(button1);
			jp.add(button2);
		}else if(operate==4){
			jt2 = new JTextArea("The goods are sold out.Please select again"+":");
			jt2.setBounds(5, 5, 600, 25);
		}
		
		final JTextArea jt3 = new JTextArea();

		JTextArea jt1 = new JTextArea("Option selected"+":");
		jt1.setBounds(295, 350, 160, 25);
		jt3.setBounds(457, 356, 25, 16);
		
		jt1.setFont(fo);
		jt2.setFont(fo);
		jt2.setEditable(false);
		jt1.setEditable(false);
		jt1.setBackground(co);
		jt2.setBackground(co);
		
		jt3.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String order = null;
				boolean flag = true;
                try {
                	order = e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), 
                    		e.getDocument().getLength());
                    HashMap<String, String> map = null;
    				try{
    					map = list.get(Integer.valueOf(order)-1);
    				} catch(NumberFormatException ee){
    					flag = false;
    				} catch(ArrayIndexOutOfBoundsException eee){
    					flag = false;
    				} catch(IndexOutOfBoundsException eeee){
    					flag = false;
    				}
    				if(flag){
    					if(operate==1||operate==2||operate==4){
    						if(map.get("stock").equals("0")){
    							OrderMenu.soldOut.add(map.get("food_id"));
    							orderDetails(type_id, 4);
    						}else{
        						id = map.get("food_id");
        						price = Integer.valueOf(map.get("price"));
        						orderDetails(type_id, 3);
    						}
    					}
    				}else{
    					orderDetails(type_id, 2);
    				}
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		
		jp.updateUI();
		jp.repaint();
		this.setTitle("please choose food");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * 确认订单
	 */
	@Override
	public void confirmOrder() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		jp.removeAll();

		Font font = new Font("宋体",Font.PLAIN,40);      //字体
		jp.setBackground(new Color(135,206,250));       //设置颜色

		Random ran = new Random();
		String joke = GreetMenu.joke[ran.nextInt(GreetMenu.joke.length)];//获取笑话
		
		JTextArea ar1 = new JTextArea();
		ar1.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		ar1.setFont(font);
		ar1.setBackground(new Color(135,206,250));
		ar1.setEditable(false);
		ar1.setBounds(0, 50, 785, 300);
		
		JTextArea ar2 = new JTextArea("Let me tell a joke for you~\n"+joke);
		ar2.setFont(new Font("宋体",Font.PLAIN,30));
		ar2.setBackground(new Color(135,206,250));
		ar2.setEditable(false);
		ar2.setBounds(120, 130, 500, 200);
		
		JTextArea arr3 = new JTextArea("Now,you have ordered "+OrderMenu.consumption+" ￥ in total");
		arr3.setFont(new Font("宋体",Font.PLAIN,30));
		arr3.setBackground(new Color(135,206,250));
		arr3.setEditable(false);
		arr3.setBounds(120, 5, 600, 50);
		
		jp.add(ar2);
		jp.add(ar1);
		jp.add(arr3);
		
		JButton button1 = new JButton("Back to menu");
		button1.setBounds(125, 360, 200, 40);
		button1.setBackground(new Color(0,255,255));
		button1.setFont(new Font("宋体",Font.PLAIN,20));
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				orderList(true);
			}
		});
		
		JButton button2 = new JButton("Leave the restaurant");
		button2.setBounds(330, 360, 300, 40);
		button2.setBackground(new Color(152,251,152));
		button2.setFont(new Font("宋体",Font.PLAIN,20));
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				leave();
			}
		});
		
		jp.add(button1);
		jp.add(button2);
		
		jp.updateUI();
		jp.repaint();
		this.add(jp);
		this.setTitle("please wait");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	/**
	 * 离开餐厅
	 */
	@Override
	public void leave() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		jp.removeAll();
		
		Font font = new Font("宋体",Font.PLAIN,40);      //字体

		jp.setLayout(null);
		jp.setBackground(new Color(135,206,250));       //设置颜色

		JTextArea ar = new JTextArea("Wish you a pleasant journey!\nGood luck!");
		ar.setFont(font);
		ar.setBackground(new Color(135,206,250));
		ar.setEditable(false);
		ar.setBounds(110, 150, 800, 200);
		
		jp.add(ar);
		
		jp.updateUI();
		jp.repaint();
		this.add(jp);
		this.setTitle("leave");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		RobotGUI gui = new RobotGUI();
		gui.welcome();
	}
}
