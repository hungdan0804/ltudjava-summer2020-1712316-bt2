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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import MyListener.MyListener;
import Object.CheckExaminationPaper;
import Object.CheckExaminationPaperInfo;
import Object.Course;
import Object.CurrentCourse;
import Object.Student;
import Util.HibernateUtil;
import Util.RoundedButton;
import Util.RoundedPanel;
import Util.RoundedTextArea;
import Util.RoundedTextField;
import javax.swing.JTextArea;

public class UI_CEP_FORM extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private String curCep;
	private int curCepID;
	private JLabel Dashboard;
	private JLabel sign_out;
    private JLabel schedule;
    private JLabel profile;
    private JLabel transcripts;
    private JLabel cep;
    private JLabel list_cep;
    private JLabel list_classes;
    private JTextArea textArea;
    private JPanel panel = new JPanel();
    private JPanel profile_content;
    private JTextField studentID_Box;
    private JTextField fullname_Box;
    private JComboBox<String> course_box;
    private JComboBox<String> columnM_Box;
    private JTextField newM_Box; 
    private JButton change_password;
    private JButton save_info;
    private Vector<String> data_column = new  Vector<>();
    private Vector<String> data_course = new  Vector<>();
    
	public UI_CEP_FORM(Student student, String curCep) {
		this.curStudent=student;
		this.curCep=curCep;
		
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initializeData();
				course_box.setSelectedIndex(0);
			}
			
		});
		t1.start();
		
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
		
		JLabel lblNewLabel = new JLabel("ĐƠN PHÚC KHẢO");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		panel.setBackground(Color.WHITE);
		
		
		JLabel studentID = new JLabel("MSSV");
		studentID.setFont(new Font("Arial", Font.BOLD, 16));
		studentID.setBounds(profile_content.getWidth()/20,profile_content.getHeight()/10, 50, 16);
		profile_content.add(studentID);
		
		studentID_Box = new RoundedTextField(20);
		studentID_Box.setBorder(new EmptyBorder(0,10,0,0));
		studentID_Box.setText(curStudent.getStudentID());
		studentID_Box.setBackground(Color.WHITE);
		studentID_Box.setEditable(false);
		studentID_Box.setBounds(profile_content.getX()+60, profile_content.getHeight()/10-3, 90, 25);
		profile_content.add(studentID_Box);
		studentID_Box.setColumns(10);
		
		JLabel fullName = new JLabel("HỌ VÀ TÊN");
		fullName.setFont(new Font("Arial", Font.BOLD, 16));
		fullName.setBounds(studentID_Box.getX() +110,profile_content.getHeight()/10, 90, 16);
		profile_content.add(fullName);
		
		fullname_Box = new RoundedTextField(20);
		fullname_Box.setBorder(new EmptyBorder(0,10,0,0));
		fullname_Box.setText(curStudent.getFullname());
		fullname_Box.setBackground(Color.WHITE);
		fullname_Box.setEditable(false);
		fullname_Box.setBounds(fullName.getX()+100, profile_content.getHeight()/10-3, 200, 25);
		profile_content.add(fullname_Box);
		fullname_Box.setColumns(10);
		
		JLabel courseID = new JLabel("MÔN");
		courseID.setFont(new Font("Arial", Font.BOLD, 16));
		courseID.setBounds(fullname_Box.getX()+220,profile_content.getHeight()/10, 40, 16);
		profile_content.add(courseID);
		
		course_box = new JComboBox<String>(data_course);
		course_box.setBackground(Color.WHITE);
		course_box.setBounds(courseID.getX()+50, profile_content.getHeight()/10-3, 200, 25);
		profile_content.add(course_box);

		
		JLabel columnM  = new JLabel("CỘT ĐIỂM CẦN PHÚC KHẢO");
		columnM .setFont(new Font("Arial", Font.BOLD, 16));
		columnM .setBounds(profile_content.getWidth()/20,profile_content.getHeight()/5, 220, 16);
		profile_content.add(columnM );
		
		columnM_Box = new JComboBox<String>(data_column);
		columnM_Box.setBackground(Color.WHITE);
		columnM_Box.setBounds(columnM.getX()+230, profile_content.getHeight()/5-3, 150, 25);
		profile_content.add(columnM_Box);
		
		JLabel newM  = new JLabel("ĐIỂM MONG MUỐN");
		newM .setFont(new Font("Arial", Font.BOLD, 16));
		newM .setBounds(columnM_Box.getX()+190,profile_content.getHeight()/5, 150, 16);
		profile_content.add(newM );
		
		newM_Box = new RoundedTextField(20);
		newM_Box.setBorder(new EmptyBorder(0,10,0,0));
		newM_Box.setBackground(Color.WHITE);
		newM_Box.setBounds(newM.getX()+160, profile_content.getHeight()/5-3, 90, 25);
		profile_content.add(newM_Box);
		newM_Box.setColumns(10);
		
		save_info = new RoundedButton();
		save_info.setForeground(Color.WHITE);
		save_info.setFont(new Font("Arial", Font.BOLD, 12));
		save_info.setText("Gửi đơn");
		save_info.setBounds(profile_content.getWidth()/20, profile_content.getHeight()*9/10, 90, 23);
		save_info.setBackground(new Color(22,72,159));
		profile_content.add(save_info);

		
		change_password = new RoundedButton();
		change_password.setForeground(Color.WHITE);
		change_password.setFont(new Font("Arial", Font.BOLD, 12));
		change_password.setText("Quay Lại");
		change_password.setBounds(save_info.getX()+100, profile_content.getHeight()*9/10,120, 23);
		change_password.setBackground(new Color(22,72,159));
		profile_content.add(change_password);
		
		JLabel reason = new JLabel("LÝ DO");
		reason.setFont(new Font("Arial", Font.BOLD, 16));
		reason.setBounds(profile_content.getWidth()/20,profile_content.getHeight()*3/10, 220, 16);
		profile_content.add(reason);
		
		textArea = new RoundedTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(profile_content.getWidth()/20,profile_content.getHeight()*2/5, profile_content.getWidth()*9/10, profile_content.getHeight()*4/10);
		textArea.setBorder(new EmptyBorder(10,10,0,0));
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		profile_content.add(textArea);
		
		clickListener();
	}
	
	@SuppressWarnings("unchecked")
	private void initializeData() {
		// TODO Auto-generated method stub
		data_column.add("Điểm giữa kì");
		data_column.add("Điểm cuối kì");
		data_column.add("Điểm khác");
		
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CheckExaminationPaper where title = :title");
            query.setParameter("title", curCep);
            List<CheckExaminationPaper> l = new ArrayList<>(query.list());
            this.curCepID = l.get(0).getCepID();
            if(!l.isEmpty()) {
            	query = session.createQuery("from CurrentCourse where scheduleID = :id");
            	String scheduleID = curStudent.getStudentID()+"-"+l.get(0).getYear()+"-"+l.get(0).getTerm();
                query.setParameter("id", scheduleID);
                List<CurrentCourse> s = new ArrayList<>(query.list());
                for(CurrentCourse cur : s) {
                	data_course.add(cur.getCourse().getCourseName());
                }
            }
            
            //import student for every current course 
			transaction.commit();
			
		}catch (Exception e2) {
            e2.printStackTrace();
        }
			
	}
	
	@SuppressWarnings("unchecked")
	private void saveForm() {
		if(!newM_Box.getText().isEmpty()) {
			try {
				float a = Float.parseFloat(newM_Box.getText());
				if(a<=10 && a>=0) {
					Transaction transaction = null;
					try (Session session = HibernateUtil.getSessionFactory().openSession()) {
						// start a transaction
			            transaction = session.beginTransaction();
			            Query query = session.createQuery("from Course where courseName = :name");
			            query.setParameter("name", course_box.getSelectedItem());
			            List<Course> course = new ArrayList<>(query.list());
			            String curCourseID=curStudent.getClasses()+"-"+course.get(0).getCourseID();
			            String choose_column=(String) columnM_Box.getSelectedItem();
			            CheckExaminationPaperInfo ex= new CheckExaminationPaperInfo(curStudent,curCourseID,curCepID,choose_column,a,textArea.getText(),"Chưa duyệt");
			            session.save(ex);
			            //import student for every current course 
						transaction.commit();
						UI_CEP ui = new UI_CEP(curStudent);
						ui.setVisible(true);
						dispose();
					}catch (Exception e2) {
			            e2.printStackTrace();
			        }
					
				}else {
					JOptionPane.showMessageDialog(contentPane,"Nhập sai Format điểm !!!");
					
				}
			}catch(NumberFormatException e1) {
				e1.printStackTrace();
	        	JOptionPane.showMessageDialog(contentPane,"Nhập sai Format điểm !!!");
			}
		}else {
			JOptionPane.showMessageDialog(contentPane,"Không bỏ trống phần điểm mong muốn !!!");
		}
		
	}

	private void clickListener(){
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
		if(curStudent.getRole()==0) {
			list_classes.addMouseListener(new MyListener(curStudent,this));
		}
		save_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t1= new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						saveForm();
						
					}
					
				});
				t1.start();
			}
			
		});
		
		change_password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UI_CEP ui = new UI_CEP(curStudent);
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
