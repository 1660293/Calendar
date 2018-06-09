package trantrongkim.com.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import sun.awt.IconInfo;
import trantrongkim.com.model.Lich_Tay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class ViewLich extends JFrame{
	public ViewLich(Lich_Tay lt)
	{
		jf=new JFrame("Lịch Vạn Niên");
		pnlLich=new JPanel();
		pnlhand=new JPanel();
		textday=new JTextField();
		textday.setBackground(Color.decode("#f5f984"));
		jf.setSize(550,550);
		less=new JButton(new ImageIcon(System.getProperty("user.dir")+"\\Number\\less.png"));
		plus=new JButton(new ImageIcon(System.getProperty("user.dir")+"\\Number\\plus.png"));
		plus.setName(">");
		less.setName("<");
		time=new JButton("hello");
		time.setName("time");
		time.setSize(550, 40);
		btn=new JButton[42];
		jf.setMaximizedBounds(new Rectangle(550, 550));
		jf.setMaximizedBounds(new Rectangle(550, 550));
		jf.setBackground(Color.decode("#230398"));
		plus.setSize(64, 34);
		this.lt=lt;
	}
	// tạo ngày tháng năm
	private void createLich()
	{
		// tạo lưới cho lịch
		pnlLich.setLayout(new GridLayout(7, 7));
		pnlLich.setBackground(Color.white);
		// thanh thứ 
		for(int i=0;i<7;i++)
		{
			JLabel lb=new JLabel(thu(i));
			
			lb.setOpaque(true);
			lb.setHorizontalAlignment(lb.CENTER);
			lb.setBorder(BorderFactory.createLineBorder(Color.RED,FlowLayout.CENTER));
			lb.setFont(new Font("Time New Roman",Font.ITALIC,25));
			lb.setBackground(Color.ORANGE);
			pnlLich.add(lb);
		}
		boolean flagDay=false;
		// tạo ngày vô trong bảng
		for(int i=0;i<42;i++)
		{
			
			Integer n= lt.getDay();
			if(flagDay==false && lt.layNgay()==n)
			{
				flagDay=true;
				JButton btnchild=new JButton(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\f", n)));
				btnchild.setBorder(BorderFactory.createLineBorder(Color.BLACK,FlowLayout.CENTER));
				btnchild.setName(n.toString());
				btn[i]=btnchild;
				pnlLich.add(btnchild);
			}
			else {
				JButton btnchild=new JButton(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\", n)));
				btnchild.setBorder(BorderFactory.createLineBorder(Color.gray,FlowLayout.CENTER));
				btnchild.setName(n.toString());
				btn[i]=btnchild;
				pnlLich.add(btnchild);
			}
			
		}
	}
	// hàm lấy link hình ảnh
	private String getIcon(String URL,Integer i)
	{
		String st=i.toString()+".png";
		URL+=st;
		return URL;
		
	}
	// tạo thanh điều khiển tăng giảm month
	private void createHand()
	{
		pnlhand.setLayout(new GridLayout(1,7));
		pnlhand.setBackground(null);
		pnlhand.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		textday.setText(lt.dayMonthYear());
		textday.setFont(new Font("Tahoma",Font.BOLD,25));
		textday.setHorizontalAlignment(textday.CENTER);
		pnlhand.add(less,BorderLayout.BEFORE_LINE_BEGINS);
		pnlhand.add(textday);
		pnlhand.add(plus,BorderLayout.AFTER_LINE_ENDS);
	}
	public void initUI() {
		createLich();
		createHand();
		ImageIcon icon = new ImageIcon("D:\\Learn\\More_Learn\\Icon\\big_bundle_graphic_vito\\500_vector_icons\\basic_application\\PNG\\512\\ic_07.png");
		jf.setIconImage(icon.getImage());
		jf.add(time,BorderLayout.AFTER_LAST_LINE);
		time.setFont(new Font("Tahoma", Font.PLAIN, 30));
		jf.add(pnlhand,BorderLayout.BEFORE_FIRST_LINE);
		jf.add(pnlLich);
		Thread t = new Thread(new ChangeTime());
		t.start();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	}
	private String thu(int i)
	{
		if(i==0)return "Su";
		if(i==1)return "Mo";
		if(i==2)return "Tu";
		if(i==3)return "We";
		if(i==4)return "Th";
		if(i==5)return "Fr";
		if(i==6)return "Sa";
		return "";
	}
	//hàm thay đổi icon cho JLabel
	public void changIconJlabel()
	{
		boolean flagDay=false;
		for(int i=0;i<42;i++)
		{
			Integer n= lt.getDay();
			if(flagDay==false&&lt.layNgay()==n)
			{
				btn[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,FlowLayout.CENTER));
				btn[i].setIcon(new ImageIcon(getIcon(".\\Number\\f", n)));
				btn[i].setName(n.toString());
				flagDay=true;
			}
			else {
				btn[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,FlowLayout.CENTER));
				btn[i].setIcon(new ImageIcon(getIcon(".\\Number\\", n)));
				btn[i].setName(n.toString());
			}
			
		}
	}
	// hàm bắt sự kiện của lịch
	public void handAction()
	{
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
					lt.plusOneMonth();
					lt.setDay(1);
					Thread t = new Thread(new ChangeIcon());
					t.start();
					textday.setText(lt.dayMonthYear());
			}
		});
		less.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				lt.lessOneMonth();
				lt.setDay(1);
				Thread t = new Thread(new ChangeIcon());
				t.start();
				textday.setText(lt.dayMonthYear());
			}
		});
		textday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				if(lt.dayMonthYear().compareToIgnoreCase(textday.getText())!=0)
				{
					if(lt.check(textday.getText())==true)
					{
						String a[]=textday.getText().split("/");
						
						Integer i=new Integer(0);
						i=i.parseInt(a[0]);
						lt.setDay(i);
						i=i.parseInt(a[1]);
						lt.setMonth(i);
						i=i.parseInt(a[2]);
						lt.setYear(i);
						Thread t = new Thread(new ChangeIcon());
						t.start();	
						
					}
						
				}
			}
			
		});
		time.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				lt.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				lt.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
				lt.setYear(Calendar.getInstance().get(Calendar.YEAR));
				Thread t = new Thread(new ChangeIcon());
				t.start();
				textday.setText(lt.dayMonthYear());
			}
		});
		for(int i=0;i<42;i++)
		{
			final int ibtn=i;
			giam=false;
			tang=false;
			btn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated method stub
					Integer text1 =new Integer(0);
					Integer text2 =new Integer(0);
					for(int i=0;i< 42;i++)
					{
						if(btn[i].getName().compareToIgnoreCase(lt.layNgay().toString())==0)
						{
							text1 = text1.parseInt(btn[i].getName());
							btn[i].setIcon(new ImageIcon(getIcon(".\\Number\\",text1)));
						}
					}
					text2=text2.parseInt(btn[ibtn].getName());
					btn[ibtn].setIcon(new ImageIcon(getIcon(".\\Number\\f",text2)));
					if(ibtn<14)
					{
						if(text2>8)
						{							
							if(tang == true)
							{
								lt.setMonth(lt.getMonth()-2);
								giam=true;
								tang=false;
							}
							else if(giam==true)
							{
								lt.setMonth(lt.getMonth());
							}
							else
							{
								lt.setMonth(lt.getMonth()-1);
								giam=true;
							}
						}
						else {
							if(giam==true)
							{
								lt.setMonth(lt.getMonth()+1);
								giam=false;
							}
							else if(tang==true)
							{
								lt.setMonth(lt.getMonth()-1);
								tang=false;
							}
						}
					}
					else if(ibtn>28)
					{
						if(text2<20)
						{
							if(giam == true)
							{
								lt.setMonth(lt.getMonth()+2);
								tang=true;
								giam=false;
							}
							else if(tang==true)
							{
								lt.setMonth(lt.getMonth());
							}
							else
							{
								lt.setMonth(lt.getMonth()+1);
								tang=true;
							}
						}
						else {
							if(giam==true)
							{
								lt.setMonth(lt.getMonth()+1);
								giam=false;
							}
							else if(tang==true)
							{
								lt.setMonth(lt.getMonth()-1);
								tang=false;
							}
						}
					}
					else if(giam==true)
					{
						lt.setMonth(lt.getMonth()+1);
						giam=false;
					}
					else if(tang==true)
					{
						lt.setMonth(lt.getMonth()-1);
						tang=false;
					}
					lt.setDay(text2);
					textday.setText(lt.dayMonthYear());
				}
			});
		}
	}

	private class ChangeIcon implements Runnable
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean flagDay=false;
			try {
				for(int i=0;i<42;i++)
				{
					Integer n= lt.getDay();
					if(flagDay==false&&lt.layNgay()==n)
					{
						if(lt.layNgay()>20)
						{
							if(i>7)
							{
								btn[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,FlowLayout.CENTER));
								btn[i].setIcon(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\f", n)));
								btn[i].setName(n.toString());
								flagDay=true;
							}
							else 
							{
								btn[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,FlowLayout.CENTER));
								btn[i].setIcon(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\", n)));
								btn[i].setName(n.toString());
							}
						}
						else 
						{
							btn[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,FlowLayout.CENTER));
							btn[i].setIcon(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\f", n)));
							btn[i].setName(n.toString());
							flagDay=true;
						}
					}
					else {
						btn[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,FlowLayout.CENTER));
						btn[i].setIcon(new ImageIcon(getIcon(System.getProperty("user.dir")+"\\Number\\", n)));
						btn[i].setName(n.toString());
					}

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	private class ChangeTime implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        time.setText(sdf.format(cal.getTime()));
		        try {
					Thread.sleep(990);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	private JButton []btn;
	private JTextField textday;
	private JPanel pnlLich;
	private JPanel pnlhand;
	private JFrame jf;
	private JButton plus;
	private JButton less;
	private JButton time;
	Lich_Tay lt;
	private boolean giam;
	private boolean tang;
}	
