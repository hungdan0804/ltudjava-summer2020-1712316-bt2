package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Object.Student;
import Util.RoundedLabel;

public class UI_DashBoard extends JFrame {
	private static final long serialVersionUID = 1L;
	private Student curStudent;
	private JPanel contentPane;
	private JLabel schedule_box;
	private JLabel schedule;
	private JLabel sign_out;

	/**
	 * Create the frame.
	 */
	public UI_DashBoard(Student student) {
		
		this.curStudent=student;
		setResizable(false);
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,(int)dim.getWidth()*4/5,(int)dim.getHeight()*5/6);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height*6/11);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pack();
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight()/8); 
		contentPane.add(header);
		header.setLayout(new BorderLayout(0, 0));
		
		JLabel icon = new JLabel("");
		header.add(icon, BorderLayout.WEST);
		icon.setIcon(new ImageIcon(UI_SignIn.class.getResource("/img/hcmus_icon.png")));
		
		JLabel title = new JLabel("HCMUS PORTAL",SwingConstants.CENTER);
		title.setBackground(Color.WHITE);
		title.setForeground(new Color(22,72,159));
		title.setFont(new Font("Arial", Font.BOLD, 22));
		header.add(title, BorderLayout.CENTER);
		
		JPanel navigation = new JPanel();
		navigation.setBounds(0, header.getHeight(),contentPane.getWidth()/5, contentPane.getHeight()-header.getHeight());
		contentPane.add(navigation);
		navigation.setLayout(null);
		
		JPanel navi_header = new JPanel();
		navi_header.setBounds(0, 0,navigation.getWidth(), navigation.getHeight()/5);
		navi_header.setBackground(new Color(22,72,159));
		navigation.add(navi_header);
		navi_header.setLayout(null);
		
		JLabel user_icon = new JLabel("");
		user_icon.setHorizontalAlignment(SwingConstants.CENTER);
		user_icon.setIcon(new ImageIcon(new ImageIcon(UI_SignIn.class.getResource("/img/user_icon.png")).getImage().getScaledInstance(navi_header.getWidth()*1/3, navi_header.getHeight()*2/3, Image.SCALE_DEFAULT)));
		user_icon.setBounds(0, 0,navigation.getWidth(),navi_header.getHeight()*2/3);
		user_icon.setOpaque(false);
		navi_header.add(user_icon);
		
		
		JLabel user_name = new JLabel("Chào "+getLastName(curStudent.getFullname()));
		user_name.setFont(new Font("Arial", Font.BOLD, 16));
		user_name.setForeground(Color.WHITE);
		user_name.setHorizontalAlignment(SwingConstants.CENTER);
		user_name.setBounds(0,user_icon.getHeight(), navi_header.getWidth(), navi_header.getHeight()-user_icon.getHeight());
		navi_header.add(user_name);
		
		JPanel navi_menu = new JPanel();
		navi_menu.setBounds(0,navi_header.getHeight(),navigation.getWidth(), navigation.getHeight()-navi_header.getHeight());
		navi_menu.setBackground(new Color(22,72,159));
		navigation.add(navi_menu);
		navi_menu.setLayout(new GridLayout(8, 10));
		
		JLabel Dashboard = new JLabel("Dashboard");
		Dashboard.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_1.png")));
		Dashboard.setForeground(Color.WHITE);
		Dashboard.setFont(new Font("Arial", Font.BOLD, 14));
		Dashboard.setBorder(new EmptyBorder(0,10,0,0));
		Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		navi_menu.add(Dashboard);
		
		schedule = new JLabel("Th\u1EDDi kh\u00F3a bi\u1EC3u");
		schedule.setForeground(Color.WHITE);
		schedule.setFont(new Font("Arial", Font.BOLD, 14));
		schedule.setBorder(new EmptyBorder(0,10,0,0));
		schedule.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_2.png")));
		schedule.setHorizontalAlignment(SwingConstants.LEFT);
		navi_menu.add(schedule);
		
		JLabel transcripts = new JLabel("B\u1EA3ng \u0111i\u1EC3m");
		transcripts.setForeground(Color.WHITE);
		transcripts.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_3.png")));
		transcripts.setHorizontalAlignment(SwingConstants.LEFT);
		transcripts.setFont(new Font("Arial", Font.BOLD, 14));
		transcripts.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(transcripts);
		
		JLabel profile = new JLabel("Th\u00F4ng tin c\u00E1 nh\u00E2n");
		profile.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_4.png")));
		profile.setFont(new Font("Arial", Font.BOLD, 14));
		profile.setForeground(Color.WHITE);
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(profile);
		
		JLabel cep = new JLabel("Ph\u00FAc kh\u1EA3o \u0111i\u1EC3m");
		cep.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_5.png")));
		cep.setHorizontalAlignment(SwingConstants.LEFT);
		cep.setForeground(Color.WHITE);
		cep.setFont(new Font("Arial", Font.BOLD, 14));
		cep.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(cep);
		
		JLabel list_cep = new JLabel("Danh s\u00E1ch ph\u00FAc kh\u1EA3o");
		list_cep.setFont(new Font("Arial", Font.BOLD, 14));
		list_cep.setForeground(Color.WHITE);
		list_cep.setHorizontalAlignment(SwingConstants.LEFT);
		list_cep.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_6.png")));
		list_cep.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(list_cep);
		
		JLabel list_classes = new JLabel("Danh s\u00E1ch l\u1EDBp");
		list_classes.setFont(new Font("Arial", Font.BOLD, 14));
		list_classes.setForeground(Color.WHITE);
		list_classes.setHorizontalAlignment(SwingConstants.LEFT);
		list_classes.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_7.png")));
		list_classes.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(list_classes);
		
		sign_out = new JLabel("\u0110\u0103ng xu\u1EA5t");
		sign_out.setFont(new Font("Arial", Font.BOLD, 14));
		sign_out.setForeground(Color.WHITE);
		sign_out.setHorizontalAlignment(SwingConstants.LEFT);
		sign_out.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_8.png")));
		sign_out.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(sign_out);
		
		JPanel home = new JPanel();
		home.setBackground(Color.WHITE);
		home.setBounds(navigation.getWidth(),header.getHeight(),contentPane.getWidth()-navigation.getWidth(),contentPane.getHeight()-header.getHeight());
		home.setBorder(new EmptyBorder(home.getWidth()/13,home.getWidth()/13,home.getWidth()/13,home.getWidth()/13));
		contentPane.add(home);
		home.setLayout(new GridLayout(2, 3,home.getWidth()/13,home.getWidth()/13));
		
		schedule_box = new RoundedLabel();
		schedule_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_1.png")));
		schedule_box.setFont(new Font("Arial", Font.BOLD, 18));
		schedule_box.setBackground(Color.WHITE);
		schedule_box.setVerticalTextPosition(SwingConstants.TOP);
		schedule_box.setHorizontalTextPosition(SwingConstants.CENTER);
		schedule_box.setHorizontalAlignment(SwingConstants.CENTER);
		schedule_box.setText("TH\u1EDCI KH\u00D3A BI\u1EC2U");
		home.add(schedule_box);
		
		JLabel Transcript_box = new RoundedLabel();
		Transcript_box.setBackground(Color.WHITE);
		Transcript_box.setText("B\u1EA2NG \u0110I\u1EC2M");
		Transcript_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_2.png")));
		Transcript_box.setFont(new Font("Arial", Font.BOLD, 18));
		Transcript_box.setVerticalTextPosition(SwingConstants.TOP);
		Transcript_box.setHorizontalTextPosition(SwingConstants.CENTER);
		Transcript_box.setHorizontalAlignment(SwingConstants.CENTER);
		home.add(Transcript_box);
		
		JLabel profile_box = new RoundedLabel();
		profile_box.setFont(new Font("Arial", Font.BOLD, 18));
		profile_box.setHorizontalAlignment(SwingConstants.CENTER);
		profile_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_3.png")));
		profile_box.setText("TH\u00D4NG TIN C\u00C1 NH\u00C2N");
		profile_box.setVerticalTextPosition(SwingConstants.TOP);
		profile_box.setHorizontalTextPosition(SwingConstants.CENTER);
		profile_box.setBackground(Color.WHITE);
		home.add(profile_box);
		
		JLabel cep_box = new RoundedLabel();
		cep_box.setBackground(Color.WHITE);
		cep_box.setFont(new Font("Arial", Font.BOLD, 18));
		cep_box.setHorizontalAlignment(SwingConstants.CENTER);
		cep_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_4.png")));
		cep_box.setText("PH\u00DAC KH\u1EA2O \u0110I\u1EC2M");
		cep_box.setVerticalTextPosition(SwingConstants.TOP);
		cep_box.setHorizontalTextPosition(SwingConstants.CENTER);
		home.add(cep_box);
		
		JLabel list_cep_box = new RoundedLabel();
		list_cep_box.setBackground(Color.WHITE);
		list_cep_box.setFont(new Font("Arial", Font.BOLD, 18));
		list_cep_box.setHorizontalAlignment(SwingConstants.CENTER);
		list_cep_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_5.png")));
		list_cep_box.setText("<html><p>DANH S\u00C1CH <br> PH\u00DAC KH\u1EA2O</p></html>");
		list_cep_box.setVerticalTextPosition(SwingConstants.TOP);
		list_cep_box.setHorizontalTextPosition(SwingConstants.CENTER);
		home.add(list_cep_box);
		
		JLabel list_classes_box = new RoundedLabel();
		list_classes_box.setBackground(Color.WHITE);
		list_classes_box.setFont(new Font("Arial", Font.BOLD, 18));
		list_classes_box.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/dashboard_icon_6.png")));
		list_classes_box.setHorizontalAlignment(SwingConstants.CENTER);
		list_classes_box.setText("DANH S\u00C1CH L\u1EDAP");
		list_classes_box.setVerticalTextPosition(SwingConstants.TOP);
		list_classes_box.setHorizontalTextPosition(SwingConstants.CENTER);
		home.add(list_classes_box);
		
		clickListener();
	}
	
	private void clickListener() {
		schedule_box.addMouseListener(new MListener());
		schedule.addMouseListener(new MListener());
		sign_out.addMouseListener(new MListener());
	}
	
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
	class MListener extends MouseAdapter{
		 public void mouseClicked(MouseEvent e)  
		 {  
			 JLabel choose =(JLabel) e.getSource();
			 switch(choose.getText()) {
			 	case "THỜI KHÓA BIỂU": case"Thời khóa biểu": UI_Schedule ui= new UI_Schedule(curStudent); ui.setVisible(true);dispose();break;
			 	case "Đăng xuất": UI_SignIn ui2= new UI_SignIn();ui2.setVisible(true);dispose();break;
			 };
		 }  
    }
}
