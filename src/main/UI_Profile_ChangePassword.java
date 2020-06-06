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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import MyListener.MyListener;
import Object.Student;
import Util.HibernateUtil;
import Util.RoundedButton;
import Util.RoundedPanel;
import Util.RoundedPasswordField;

import javax.swing.JButton;

public class UI_Profile_ChangePassword extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
    private JLabel schedule;
    private JLabel profile;
    private JLabel transcripts;
    private JPanel profile_content;
    private JLabel cep;
    private JLabel list_cep;
    private JLabel list_classes;
    private JPasswordField old_password_box;
    private JPasswordField new_password_box;
    private JPasswordField rpnew_password_box;
    private JButton save_password;
    private JButton cancel;
    private JLabel old_password_visible;
    private JLabel new_password_visible;
    private JLabel rpnew_password_visible;
    private JLabel old_password_invisible;
    private JLabel new_password_invisible;
    private JLabel rpnew_password_invisible;
    private boolean old_password_check=false;
    private boolean rpnew_password_check=false;

	public UI_Profile_ChangePassword(Student student) {
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
		profile_content.setBounds(content.getWidth()/20, content.getHeight()/5, content.getWidth()*9/10, content.getHeight()*3/10);
		content.add(profile_content);
		profile_content.setLayout(null);
		
		JLabel old_password = new JLabel("M\u1EACT KH\u1EA8U C\u0168");
		old_password.setFont(new Font("Arial", Font.BOLD, 14));
		old_password.setBounds(profile_content.getWidth()/20, profile_content.getHeight()/10, 110, 14);
		profile_content.add(old_password);
		
		JLabel new_password = new JLabel("M\u1EACT KH\u1EA8U M\u1EDAI");
		new_password.setFont(new Font("Arial", Font.BOLD, 14));
		new_password.setBounds(profile_content.getWidth()/20, old_password.getY()+34, 110, 14);
		profile_content.add(new_password);
		
		JLabel rpnew_password = new JLabel("NH\u1EACP L\u1EA0I");
		rpnew_password.setFont(new Font("Arial", Font.BOLD, 14));
		rpnew_password.setBounds(profile_content.getWidth()/20, new_password.getY()+34, 110, 14);
		profile_content.add(rpnew_password);
		
		save_password = new RoundedButton();
		save_password.setText("Lưu lại");
		save_password.setBackground(new Color(22,72,159));
		save_password.setForeground(Color.WHITE);
		save_password.setFont(new Font("Arial", Font.BOLD, 12));
		save_password.setBounds(profile_content.getWidth()/20, profile_content.getHeight()*4/5, 90, 23);
		profile_content.add(save_password);
		
		cancel = new RoundedButton();
		cancel.setText("Quay Lại");
		cancel.setBackground(new Color(22,72,159));
		cancel.setFont(new Font("Arial", Font.BOLD, 12));
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(save_password.getX()+100, profile_content.getHeight()*4/5, 89, 23);
		profile_content.add(cancel);
		
		old_password_visible = new JLabel("");
		old_password_visible.setBounds(old_password.getX()+old_password.getWidth()+180, profile_content.getHeight()/10, 18, 14);
		profile_content.add(old_password_visible);
		old_password_visible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_off_black_18dp.png")));
		
		new_password_visible = new JLabel("");
		new_password_visible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_off_black_18dp.png")));
		new_password_visible.setBounds(new_password.getX()+new_password.getWidth()+180, old_password.getY()+34, 18, 14);
		profile_content.add(new_password_visible);
		
		rpnew_password_visible = new JLabel("");
		rpnew_password_visible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_off_black_18dp.png")));
		rpnew_password_visible.setBounds(rpnew_password.getX()+rpnew_password.getWidth()+180, new_password.getY()+34, 18, 14);
		profile_content.add(rpnew_password_visible);
		
		old_password_invisible = new JLabel("");
		old_password_invisible.setBounds(old_password.getX()+old_password.getWidth()+180, profile_content.getHeight()/10, 18, 14);
		profile_content.add(old_password_invisible);
		old_password_invisible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_black_18dp.png")));
		
		new_password_invisible = new JLabel("");
		new_password_invisible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_black_18dp.png")));
		new_password_invisible.setBounds(new_password.getX()+new_password.getWidth()+180, old_password.getY()+34, 18, 14);
		profile_content.add(new_password_invisible);
		
		rpnew_password_invisible = new JLabel("");
		rpnew_password_invisible.setIcon(new ImageIcon(UI_Profile_ChangePassword.class.getResource("/img/baseline_visibility_black_18dp.png")));
		rpnew_password_invisible.setBounds(rpnew_password.getX()+rpnew_password.getWidth()+180, new_password.getY()+34, 18, 14);
		profile_content.add(rpnew_password_invisible);
		
		old_password_box = new RoundedPasswordField();
		old_password_box.setBorder(new EmptyBorder(0,10,0,0));
		old_password_box.setBounds(old_password.getX()+120, profile_content.getHeight()/10-5, 200, 25);
		profile_content.add(old_password_box);
		old_password_box.setColumns(10);
		
		new_password_box = new RoundedPasswordField();
		new_password_box.setBorder(new EmptyBorder(0,10,0,0));
		new_password_box.setBounds(new_password.getX()+120, old_password.getY()+29, 200, 25);
		profile_content.add(new_password_box);
		new_password_box.setColumns(10);
		
		rpnew_password_box = new RoundedPasswordField();
		rpnew_password_box.setBorder(new EmptyBorder(0,10,0,0));
		rpnew_password_box.setBounds(rpnew_password.getX()+120,new_password.getY()+29, 200, 25);
		profile_content.add(rpnew_password_box);
		rpnew_password_box.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("ĐỔI MẬT KHẨU");
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
		profile.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
		if(curStudent.getRole()==0) {
			list_classes.addMouseListener(new MyListener(curStudent,this));
		}
		old_password_visible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				old_password_box.setEchoChar((char)0);
				old_password_visible.setVisible(false);
				old_password_invisible.setVisible(true);
			}
		});
		new_password_visible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				new_password_box.setEchoChar((char)0);
				new_password_visible.setVisible(false);
				new_password_invisible.setVisible(true);
			}
		});
		rpnew_password_visible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				rpnew_password_box.setEchoChar((char)0);
				rpnew_password_visible.setVisible(false);
				rpnew_password_invisible.setVisible(true);
			}
		});
		
		old_password_invisible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				old_password_box.setEchoChar((char)UIManager.get("PasswordField.echoChar"));
				old_password_visible.setVisible(true);
				old_password_invisible.setVisible(false);
			}
		});
		new_password_invisible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				new_password_box.setEchoChar((char)UIManager.get("PasswordField.echoChar"));
				new_password_visible.setVisible(true);
				new_password_invisible.setVisible(false);
			}
		});
		rpnew_password_invisible.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
			{  
				rpnew_password_box.setEchoChar((char)UIManager.get("PasswordField.echoChar"));
				rpnew_password_visible.setVisible(true);
				rpnew_password_invisible.setVisible(false);
			}
		});
		
		old_password_box.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLog(e, "removed from");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLog(e, "remove into");
			}
			public void updateLog(DocumentEvent e, String action) {
				Document doc = (Document)e.getDocument();			    
			    try {
			    	String myPass=curStudent.getPassword();
			    	
			    	if(!BCrypt.checkpw(doc.getText(0, doc.getLength()), myPass)) {
			    		old_password_box.setForeground(Color.RED);
			    		old_password_check = false;
			    	}else {
			    		old_password_box.setForeground(Color.BLACK);
			    		old_password_check = true;
			    	}
			    	
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		
		rpnew_password_box.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLog(e, "removed from");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateLog(e, "remove into");
			}
			public void updateLog(DocumentEvent e, String action) {
				Document doc = (Document)e.getDocument();			    
			    try {
			    	String myPass=String.valueOf(new_password_box.getPassword());
			    	if(myPass.compareTo(doc.getText(0, doc.getLength())) != 0) {
			    		rpnew_password_box.setForeground(Color.RED);
			    		rpnew_password_check = false;
			    	}else {
			    		rpnew_password_box.setForeground(Color.BLACK);
			    		rpnew_password_check = true;
			    	}
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		save_password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(old_password_check && rpnew_password_check) {
					String myPass=String.valueOf(new_password_box.getPassword());
					curStudent.setPassword(BCrypt.hashpw(myPass, BCrypt.gensalt()));	
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
					UI_Profile ui = new UI_Profile(curStudent);
					ui.setVisible(true);
					dispose();
				}
			}
			
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UI_Profile ui = new UI_Profile(curStudent);
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
