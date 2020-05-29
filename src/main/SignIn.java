package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Util.RoundedButton;
import Util.RoundedPanel;
import Util.RoundedTextField;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SignIn extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignIn() {
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
		icon.setIcon(new ImageIcon(SignIn.class.getResource("/img/hcmus_icon.png")));
		
		JLabel title = new JLabel("HCMUS PORTAL",SwingConstants.CENTER);
		title.setBackground(Color.WHITE);
		title.setForeground(new Color(22,72,159));
		title.setFont(new Font("Arial", Font.BOLD, 22));
		header.add(title, BorderLayout.CENTER);
		
		
		JPanel content = new JPanel();
		content.setBounds(0, header.getHeight(), contentPane.getWidth(), contentPane.getHeight()*7/8);
		contentPane.add(content);
		content.setLayout(null);
		
		//A Panel extend JPanel
		RoundedPanel loginform = new RoundedPanel();
		loginform.setBackground(new Color(255,255,255,200));
		loginform.setBounds(content.getWidth()/4, content.getHeight()/10, content.getWidth()/2, content.getHeight()*4/5);
		content.add(loginform);
		loginform.setLayout(null);
		
		JLabel slogan = new JLabel("H\u00E3y d\u00F9ng t\u00E0i kho\u1EA3n c\u1EE7a b\u1EA1n \u0111\u1EC3 \u0111\u0103ng nh\u1EADp",SwingConstants.CENTER);
		slogan.setFont(new Font("Arial", Font.BOLD, 16));
		slogan.setBounds(0, 10, loginform.getWidth(), loginform.getHeight()/15);
		loginform.add(slogan);
		
		JLabel loginform_icon = new JLabel(new ImageIcon(SignIn.class.getResource("/img/hcmus_icon_1.png")));
		loginform_icon.setBounds(0, slogan.getHeight()+slogan.getY(), loginform.getWidth(), 100);
		loginform.add(loginform_icon);
		
		JLabel username = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp",SwingConstants.LEFT);
		username.setFont(new Font("Arial", Font.BOLD, 13));
		username.setBounds(loginform.getWidth()/10, loginform_icon.getY()+loginform_icon.getHeight()+40, loginform.getWidth()*8/10, 20);
		loginform.add(username);
		
		JTextField usernameField = new RoundedTextField(30);
		usernameField.setBackground(Color.WHITE);
		usernameField.setBounds(loginform.getWidth()/10,username.getY()+30,loginform.getWidth()*8/10, 30);
		loginform.add(usernameField);
		
		JLabel password = new JLabel("M\u1EADt kh\u1EA9u", SwingConstants.LEFT);
		password.setFont(new Font("Arial", Font.BOLD, 13));
		password.setBounds(loginform.getWidth()/10,usernameField.getY()+50, loginform.getWidth()*8/10, 20);
		loginform.add(password);
		
		JTextField passwordField = new RoundedTextField(30);
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(loginform.getWidth()/10,password.getY()+30, loginform.getWidth()*8/10, 30);
		loginform.add(passwordField);
		
		JButton signin = new RoundedButton();
		signin.setFont(new Font("Tahoma", Font.BOLD, 14));
		signin.setText("\u0110\u0103ng nh\u1EADp");
		signin.setForeground(new Color(22,72,159));
		signin.setBounds(loginform.getWidth()/3, passwordField.getY()+80, loginform.getWidth()/3, 30);
		loginform.add(signin);
			
		JLabel background = new JLabel("");
		background.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight()*7/8);
		background.setOpaque(true);
		background.setIcon(new ImageIcon(new ImageIcon(SignIn.class.getResource("/img/hcmus_bg.jpg")).getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight()*7/8, Image.SCALE_DEFAULT)));
		content.add(background);
		
	}
}
