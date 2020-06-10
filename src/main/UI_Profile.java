package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import org.hibernate.Session;
import org.hibernate.Transaction;

import MyListener.MyListener;
import Object.Student;
import Util.HibernateUtil;
import Util.RoundedButton;
import Util.RoundedPanel;
import Util.RoundedTextField;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UI_Profile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
    private JLabel schedule;
    private JLabel profile;
    private JLabel cep;
    private JLabel transcripts;
    private JLabel list_cep;
    private JLabel list_classes;
    private JPanel profile_content;
    private JTextField studentID_Box;
    private JTextField fullname_Box;
    private JTextField gender_Box;
    private JTextField idCard_Box;
    private JTextField classes_Box;
    private JTextField address_Box;
    private JButton change_password;
    private JButton update_info;
    private JButton save_info;
	
	public UI_Profile(Student student) {
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
		
		Dashboard = new JLabel("Dashboard");
		Dashboard.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_1.png")));
		Dashboard.setForeground(Color.WHITE);
		Dashboard.setFont(new Font("Arial", Font.BOLD, 14));
		Dashboard.setBorder(new EmptyBorder(0,10,0,0));
		Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		navi_menu.add(Dashboard);
		
		schedule = new JLabel("Th\u1EDDi kh\u00F3a bi\u1EC3u");
		schedule.setForeground(Color.WHITE);
		schedule.setFont(new Font("Arial", Font.BOLD, 14));
		schedule.setBorder(new EmptyBorder(0,10,0,0));
		schedule.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_2.png")));
		schedule.setHorizontalAlignment(SwingConstants.LEFT);
		navi_menu.add(schedule);
		
		transcripts = new JLabel("B\u1EA3ng \u0111i\u1EC3m");
		transcripts.setForeground(Color.WHITE);
		transcripts.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_3.png")));
		transcripts.setHorizontalAlignment(SwingConstants.LEFT);
		transcripts.setFont(new Font("Arial", Font.BOLD, 14));
		transcripts.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(transcripts);
		
		profile = new JLabel("Th\u00F4ng tin c\u00E1 nh\u00E2n");
		profile.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_4.png")));
		profile.setFont(new Font("Arial", Font.BOLD, 14));
		profile.setForeground(Color.WHITE);
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(profile);
		
		cep = new JLabel("Ph\u00FAc kh\u1EA3o \u0111i\u1EC3m");
		cep.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_5.png")));
		cep.setHorizontalAlignment(SwingConstants.LEFT);
		cep.setForeground(Color.WHITE);
		cep.setFont(new Font("Arial", Font.BOLD, 14));
		cep.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(cep);
		
		list_cep = new JLabel("Hồ sơ cần duyệt");
		list_cep.setFont(new Font("Arial", Font.BOLD, 14));
		list_cep.setForeground(Color.WHITE);
		list_cep.setHorizontalAlignment(SwingConstants.LEFT);
		list_cep.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_6.png")));
		list_cep.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(list_cep);
		
		if(curStudent.getRole()==0) {
			list_classes = new JLabel("Danh s\u00E1ch l\u1EDBp");
			list_classes.setFont(new Font("Arial", Font.BOLD, 14));
			list_classes.setForeground(Color.WHITE);
			list_classes.setHorizontalAlignment(SwingConstants.LEFT);
			list_classes.setIcon(new ImageIcon(UI_DashBoard.class.getResource("/img/navi_icon_7.png")));
			list_classes.setBorder(new EmptyBorder(0,10,0,0));
			navi_menu.add(list_classes);
		}
		
		sign_out = new JLabel("\u0110\u0103ng xu\u1EA5t");
		sign_out.setFont(new Font("Arial", Font.BOLD, 14));
		sign_out.setForeground(Color.WHITE);
		sign_out.setHorizontalAlignment(SwingConstants.LEFT);
		sign_out.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_8.png")));
		sign_out.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(sign_out);
		
		content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setBounds(navigation.getWidth(),header.getHeight(),contentPane.getWidth()-navigation.getWidth(),contentPane.getHeight()-header.getHeight());
		contentPane.add(content);
		content.setLayout(null);
		
		profile_content = new RoundedPanel();
		profile_content.setBackground(Color.WHITE);
		profile_content.setBounds(content.getWidth()/20, content.getHeight()/5, content.getWidth()*9/10, content.getHeight()*3/4);
		content.add(profile_content);
		profile_content.setLayout(null);
		
		JLabel studentID = new JLabel("MSSV");
		studentID.setFont(new Font("Arial", Font.BOLD, 16));
		studentID.setBounds(profile_content.getWidth()/20,profile_content.getHeight()/10, 50, 16);
		profile_content.add(studentID);
		
		studentID_Box = new RoundedTextField(20);
		studentID_Box.setBorder(new EmptyBorder(0,10,0,0));
		studentID_Box.setText(curStudent.getStudentID());
		studentID_Box.setBackground(Color.LIGHT_GRAY);
		studentID_Box.setEditable(false);
		studentID_Box.setBounds(profile_content.getX()+60, profile_content.getHeight()/10-3, 90, 25);
		profile_content.add(studentID_Box);
		studentID_Box.setColumns(10);
		
		JLabel fullName = new JLabel("HỌ VÀ TÊN");
		fullName.setFont(new Font("Arial", Font.BOLD, 16));
		fullName.setBounds(studentID_Box.getX() +140,profile_content.getHeight()/10, 90, 16);
		profile_content.add(fullName);
		
		fullname_Box = new RoundedTextField(20);
		fullname_Box.setBorder(new EmptyBorder(0,10,0,0));
		fullname_Box.setText(curStudent.getFullname());
		fullname_Box.setBackground(Color.LIGHT_GRAY);
		fullname_Box.setEditable(false);
		fullname_Box.setBounds(fullName.getX()+100, profile_content.getHeight()/10-3, 200, 25);
		profile_content.add(fullname_Box);
		fullname_Box.setColumns(10);
		
		JLabel gender = new JLabel("GIỚI TÍNH");
		gender.setFont(new Font("Arial", Font.BOLD, 16));
		gender.setBounds(fullname_Box.getX()+250,profile_content.getHeight()/10, 80, 16);
		profile_content.add(gender);
		
		gender_Box = new RoundedTextField(20);
		gender_Box.setBorder(new EmptyBorder(0,10,0,0));
		gender_Box.setText(curStudent.getGender());
		gender_Box.setBackground(Color.LIGHT_GRAY);
		gender_Box.setEditable(false);
		gender_Box.setBounds(gender.getX()+90, profile_content.getHeight()/10-3, 60, 25);
		profile_content.add(gender_Box);
		gender_Box.setColumns(10);
		
		JLabel idCard  = new JLabel("CHỨNG MINH NHÂN DÂN");
		idCard .setFont(new Font("Arial", Font.BOLD, 16));
		idCard .setBounds(profile_content.getWidth()/20,profile_content.getHeight()/5, 200, 16);
		profile_content.add(idCard );
		
		idCard_Box = new RoundedTextField(20);
		idCard_Box.setBorder(new EmptyBorder(0,10,0,0));
		idCard_Box.setText(curStudent.getIdCard());
		idCard_Box.setBackground(Color.LIGHT_GRAY);
		idCard_Box.setEditable(false);
		idCard_Box.setBounds(idCard.getX()+210, profile_content.getHeight()/5-3, 150, 25);
		profile_content.add(idCard_Box);
		idCard_Box.setColumns(10);
		
		JLabel classes  = new JLabel("LỚP");
		classes .setFont(new Font("Arial", Font.BOLD, 16));
		classes .setBounds(idCard_Box.getX()+190,profile_content.getHeight()/5, 40, 16);
		profile_content.add(classes );
		
		classes_Box = new RoundedTextField(20);
		classes_Box.setBorder(new EmptyBorder(0,10,0,0));
		classes_Box.setText(curStudent.getClasses());
		classes_Box.setBackground(Color.LIGHT_GRAY);
		classes_Box.setEditable(false);
		classes_Box.setBounds(classes.getX()+50, profile_content.getHeight()/5-3, 90, 25);
		profile_content.add(classes_Box);
		classes_Box.setColumns(10);
		
		JLabel address  = new JLabel("ĐỊA CHỈ");
		address .setFont(new Font("Arial", Font.BOLD, 16));
		address .setBounds(profile_content.getWidth()/20,profile_content.getHeight()*3/10, 60, 16);
		profile_content.add(address );
		
		address_Box = new RoundedTextField(20);
		address_Box.setBorder(new EmptyBorder(0,10,0,0));
		address_Box.setText(curStudent.getAddress());
		address_Box.setBackground(Color.LIGHT_GRAY);
		address_Box.setEditable(false);
		address_Box.setBounds(address.getX()+70, profile_content.getHeight()*3/10-3, 500, 25);
		profile_content.add(address_Box);
		address_Box.setColumns(10);
		
		update_info = new RoundedButton();
		update_info.setForeground(Color.WHITE);
		update_info.setFont(new Font("Arial", Font.BOLD, 12));
		update_info.setText("Cập nhật");
		update_info.setBounds(profile_content.getWidth()/20, profile_content.getHeight()*9/10, 90, 23);
		update_info.setBackground(new Color(22,72,159));
		profile_content.add(update_info);
		
		save_info = new RoundedButton();
		save_info.setForeground(Color.WHITE);
		save_info.setFont(new Font("Arial", Font.BOLD, 12));
		save_info.setText("Lưu lại");
		save_info.setBounds(profile_content.getWidth()/20, profile_content.getHeight()*9/10, 90, 23);
		save_info.setBackground(new Color(22,72,159));
		profile_content.add(save_info);
		save_info.setVisible(false);
		
		change_password = new RoundedButton();
		change_password.setForeground(Color.WHITE);
		change_password.setFont(new Font("Arial", Font.BOLD, 12));
		change_password.setText("Đổi mật khẩu");
		change_password.setBounds(update_info.getX()+100, profile_content.getHeight()*9/10,120, 23);
		change_password.setBackground(new Color(22,72,159));
		profile_content.add(change_password);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN CÁ NHÂN");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		
		clickListener();
	}
	
	private void clickListener() {
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
		if(curStudent.getRole()==0) {
			list_classes.addMouseListener(new MyListener(curStudent,this));
		}
		update_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				update_info.setVisible(false);
				save_info.setVisible(true);
		
				fullname_Box.setEditable(true);
				fullname_Box.setBackground(Color.WHITE);
				
				idCard_Box.setEditable(true);
				idCard_Box.setBackground(Color.WHITE);
				
				address_Box.setEditable(true);
				address_Box.setBackground(Color.WHITE);
			}
		});
		save_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update_info.setVisible(true);
				save_info.setVisible(false);
		
				fullname_Box.setEditable(false);
				fullname_Box.setBackground(Color.LIGHT_GRAY);
				
				idCard_Box.setEditable(false);
				idCard_Box.setBackground(Color.LIGHT_GRAY);
				
				address_Box.setEditable(false);
				address_Box.setBackground(Color.LIGHT_GRAY);
				
				
				if(idCard_Box.getText().length() > 10) {
		        	JOptionPane.showMessageDialog(contentPane,"Không nhập đúng dữ liệu !!!" );
		        	idCard_Box.setText(curStudent.getIdCard());
					return;
				}
				curStudent.setFullname(fullname_Box.getText());
				curStudent.setIdCard(idCard_Box.getText());
				curStudent.setAddress(address_Box.getText());
				
				Thread t1= new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Transaction transaction = null;
						try (Session session = HibernateUtil.getSessionFactory().openSession()) {
							// start a transaction
				            transaction = session.beginTransaction();
				            session.merge(curStudent);
				            //import student for every current course 
							transaction.commit();
						}catch (Exception e2) {
				            e2.printStackTrace();
				        }
					}
					
				});
				t1.start();
			}	
		});
		change_password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UI_Profile_ChangePassword ui = new UI_Profile_ChangePassword(curStudent);
				ui.setVisible(true);
				dispose();
			}
			
		});
	}
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
}
