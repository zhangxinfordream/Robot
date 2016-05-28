package com.order;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public  class Jiudian implements ActionListener,ItemListener{
	 static JFrame f=null;  //因为要在main静态方法中被引用，所以必须设为static类型
	  ButtonGroup bg;      //按钮组，可组合若干单选按钮
	  JRadioButton r1,r2,r3,r4,r5;  //单选按钮
	  JRadioButton c1,c2,c3,c4;  //单选按钮
	  int op=0;static int i=0;
	  public Jiudian()
	  {   // Dialog = new JDialog(f,"选择您想要的桌号和您喜欢的菜",true);
	  	 f=new JFrame("选择您想要的桌号和您喜欢的菜");
		Container dialogPane=f.getContentPane();
	   dialogPane.setLayout(new GridLayout(3,1));
	    JPanel p1=new JPanel();            //新建一个Panel
	    p1.setLayout(new GridLayout(1,5));//设置边框
	    p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
	     Color.green,4),"选择桌号",TitledBorder.CENTER,TitledBorder.TOP));//单选按钮
	    r1=new JRadioButton("one");
	    r2=new JRadioButton("two");
	    r3=new JRadioButton("three");
	    r4=new JRadioButton("four");
	    r5=new JRadioButton("five");
	    p1.add(r1);p1.add(r2);
	    p1.add(r3); p1.add(r4);  p1.add(r5);
	    bg=new ButtonGroup();  //按钮组，组合5个单选按钮，使一次只能选择一个
	    bg.add(r1);bg.add(r2);
	    bg.add(r3);bg.add(r4);bg.add(r5);
	    r1.addItemListener(this);  //为单选按钮增加ItemListener事件监听器
	    r2.addItemListener(this);
	    r3.addItemListener(this);
	    r4.addItemListener(this);
	    r5.addItemListener(this);
	    JPanel p2=new JPanel();   //新建一个Pane1
	    p2.setLayout(new GridLayout(4,1));//设置边框
	    p2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green,4),"选择您喜欢的菜",TitledBorder.CENTER,TitledBorder.TOP));//单选钮
	    c1=new JRadioButton("  小鸡炖蘑菇，价格为：25元/份");
	    c2=new JRadioButton("   青椒炒肉， 价格为：18元/份");
	    c3=new JRadioButton("   东北火锅， 价格为：36元/份");
	    c4=new JRadioButton("  豆腐炖鱼头，价格为：36元/份");
	    p2.add(c1);p2.add(c2);
	    p2.add(c3); p2.add(c4);
	    c1.addItemListener(this);  //为单选按钮增加ItemListener事件监听器
	    c2.addItemListener(this);
	    c3.addItemListener(this);
	    c4.addItemListener(this);
	    JPanel p3=new JPanel();  //创建一个新的Panel
	    p3.setLayout (new GridLayout(1,2));
	    JButton button1=new JButton("确 定");
	    JButton button2=new JButton("结 帐");
	    p3.add(button1); p3.add(button2);
	    button1.addActionListener (this);
	    button2.addActionListener (this);      //将三个Panel加在内容面板上
	    dialogPane.add(p1,BorderLayout.NORTH);
	    dialogPane.add(p2,BorderLayout.CENTER);
	    dialogPane.add(p3,BorderLayout.SOUTH);
	    f.getRootPane ().setDefaultButton (button1); //设置窗体回车对应按钮
	     f.pack(); //排版
	    f.setBounds(250,250,400,400);
	     f.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent evt) {System.exit(0);}});
	  }
	  public void itemStateChanged(ItemEvent e)  //单选钮被点击时触发
	  { if(e.getSource()==r1) op=1; if(e.getSource()==r2) op=2;
	    if(e.getSource()==r3) op=3; if(e.getSource()==r4) op=4;
	    if(e.getSource()==r5) op=5; if(e.getSource()==c1) i=1;
	    if(e.getSource()==c2) i=2;if(e.getSource()==c3) i=3;
	    if(e.getSource()==c4) i=4;
	  }
	  public void actionPerformed(ActionEvent e)  //单选按钮被点击时触发
	  {   String cmd = e.getActionCommand();
	    if (cmd.equals("确 定"))
	    {    	 try{Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:jiudian","test","1234");
					 Statement stmt=con.createStatement();
	         int m=0,val=0;String str=null;String SQLOrder;
	          switch (op)
	          {    	 case 1:{
	              switch(i)
	                 {   case 1: m=1;val=25;str="小鸡炖蘑菇";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 2: m=1;val=18;str="青椒炒肉";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder);
	             case 3: m=1;val=36;str="东北火锅";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
				      stmt.executeUpdate(SQLOrder);
	            case 4:m=1;val=36;str="豆腐炖鱼头";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder); }
	            break;}
	        case 2:
	         switch(i)
	          {   case 1:m=2;val=25;str="小鸡炖蘑菇";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 2:m=2;val=18;str="青椒炒肉";
	          SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 3: m=2;val=36;str="东北火锅";
	          SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	               case 4:m=2;val=36;str="豆腐炖鱼头";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder); }
	        case 3:
	          switch(i)
	          {  case 1: m=3;val=25;str="小鸡炖蘑菇";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder);
	            case 2: m=3;val=18;str="青椒炒肉";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 3: m=3;val=36;str="东北火锅";
	          SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 4:m=3;val=36;str="豆腐炖鱼头";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder); }
	             break;
	        case 4:
	          switch(i)
	          {  case 1:m=4;val=25;str="小鸡炖蘑菇";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 2:m=4;val=18;str="青椒炒肉";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder);
	            case 3:m=4;val=36;str="东北火锅";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	            case 4: m=4;val=36;str="豆腐炖鱼头";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder);  }
	            break;

	case 5: switch(i)
	                 {   case 1:
	                 m=5;val=25;str="小鸡炖蘑菇";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	                 case 2:
	                 m=5;val=18;str="青椒炒肉";
	                 SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	                  case 3:
	                 m=5;val=36;str="东北火锅";
	                  SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					 stmt.executeUpdate(SQLOrder);
	                  case 4:
	                 m=5;val=36;str="豆腐炖鱼头";
	           SQLOrder="Insert Into jiudian Values ("+m+",'"+str+"',"+val+")";
					stmt.executeUpdate(SQLOrder);
	                    }
	            break;}
	          }
	    catch(Exception em){}
	  }
	  if(cmd.equals("结 帐")) {new jiezhang(f);
	  	}
	 }
	 public static void main(String args[])throws Exception
	  {   new Jiudian();
	      new jiudian1(f);             }
	}
	class jiudian1 implements ActionListener {
			JFrame f; //类属性
			    JDialog Dialog;
			public jiudian1(JFrame f)              //构造方法
			{  Dialog=new JDialog();  //新建一对话框
			  Dialog.setTitle ("酒店管理系统");  //设置标题
				//f=new JFrame("酒店管理系统"); //创建一个顶层容器
			Container contentPane=Dialog.getContentPane(); //获得其内容面板
				JPanel buttonPanel = new JPanel(); //创建一中间容器JPanel
			JButton b=new JButton("进入系统");//创建一原子组件——按钮
				b.addActionListener(this);//为按钮添加事件监听器对象
				buttonPanel.add(b);	//将此按钮添加到中间容器
			  b=new JButton("退出系统"); //再创建一按钮
				b.addActionListener(this);//为按钮增加事件监听器
				buttonPanel.add(b);//将按钮添加到中间容器

				buttonPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.green,2),"欢迎光临",
				TitledBorder.CENTER,TitledBorder.TOP));
				contentPane.add(buttonPanel,BorderLayout.CENTER);//将中间容器添加到内容面板
				Dialog.setBounds(250,250,200,200);
				Dialog.setVisible (true);
	      this.f=f;

	  }
	   public void actionPerformed(ActionEvent e)
	      {
	        String cmd = e.getActionCommand();
	         if (cmd.equals("进入系统"))
	             { Dialog.dispose(); f.setVisible (true); } //显示主窗体
	         if(cmd.equals("退出系统"))  System.exit(0);
	     }
	}

	/////////////////////////
	class jiezhang implements ActionListener{
	      	   JDialog Dialog;
	     	   JLabel L1,L2,L3,L4,L5;
				JTextField F1=new JTextField();
				jiezhang(JFrame f){
				Dialog = new JDialog(f,"结账",true);
				Container dialogPane=Dialog.getContentPane();
				dialogPane.setLayout(new GridLayout(4,2));
				  L1=new JLabel("请输入您的桌号",SwingConstants.CENTER);
				  L2=new JLabel("您总共消费了：",SwingConstants.CENTER);
				  L4=new JLabel("您总共点了：",SwingConstants.CENTER);
				  L3=new JLabel("",SwingConstants.CENTER);
				  L5=new JLabel("",SwingConstants.CENTER);
				  dialogPane.add(L1);
				  dialogPane.add(F1);
				  dialogPane.add(L2);
				  dialogPane.add(L3);
				  dialogPane.add(L4);
				  dialogPane.add(L5);
				  JButton b1=new JButton("确定");
					dialogPane.add(b1);
					JButton b2=new JButton("取消");
					dialogPane.add(b2);
					b1.addActionListener(this);
					b2.addActionListener(this);
					Dialog.setBounds(400,400,300,300);
					Dialog.show();
				}
				public void actionPerformed(ActionEvent e){
						String cmd=e.getActionCommand();
						if(cmd.equals("确定")){
							try{
									Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:jiudian","test","1234");
					 Statement stmt=con.createStatement();
					 int sa=Integer.parseInt(F1.getText());
	  	     ResultSet rs=stmt.executeQuery("select sum(values) from jiudian where id="+sa+"");
	  	     while(rs.next()){
	         L3.setText(""+rs.getInt(1)+"元");
	  	          }
	  	       ResultSet rr=stmt.executeQuery("select * from jiudian");
									int j=0;
									while(rr.next()) j=j+1;
									L5.setText(""+j+" 份菜");
						stmt.executeUpdate("delete from jiudian where id="+sa+"");
								JOptionPane.showMessageDialog (Dialog,"谢谢惠顾！","",JOptionPane.WARNING_MESSAGE);
									F1.setText("");
									stmt.close();
									con.close();
								}catch(Exception em){}
						}
			if(cmd.equals("取消")){Dialog.dispose();}
		}
	}
