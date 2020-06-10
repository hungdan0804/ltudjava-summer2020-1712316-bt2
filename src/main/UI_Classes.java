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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import MyListener.MyListener;
import Object.Classes;
import Object.Student;

import Util.HeaderRenderer;
import Util.HibernateUtil;
import Util.RoundedButton;


public class UI_Classes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
	private JLabel transcripts;
	private JLabel profile;
	private JLabel cep;
	private JLabel list_cep;
	private JLabel list_classes;
	private JLabel schedule;
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();
    private Vector<String> choose_classes = new Vector<>();
    private Vector<String> choose_year = new Vector<>();
    private JComboBox<String> comboBox_classes = new JComboBox<String>(choose_classes);
    private JComboBox<String> comboBox_schoolyear = new JComboBox<String>(choose_year);
    private JTable table;
    private JButton btn_import;

	public UI_Classes(Student student) {
		this.curStudent=student;
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initializeData();
				comboBox_schoolyear.setSelectedIndex(0);
				comboBox_classes.setSelectedIndex(0);
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
		
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setBounds(250,81,contentPane.getWidth()-navigation.getWidth(),contentPane.getHeight()-header.getHeight());
		contentPane.add(content);
		content.setLayout(null);
		
		table = new JTable(data,column);
		table.setEnabled(false);
		table.setRowHeight(30);
		allignCenterAllColumn(table);
		JTableHeader t_header = table.getTableHeader();
		t_header.setDefaultRenderer(new HeaderRenderer(table));
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(content.getWidth()/20, content.getHeight()/5, content.getWidth()*9/10, content.getHeight()*3/4);
		table.setFillsViewportHeight(true);
		content.add(sp);
		
		JLabel filter_schoolyear = new JLabel("NIÊN KHÓA");
		filter_schoolyear.setFont(new Font("Arial", Font.BOLD, 16));
		filter_schoolyear.setBounds(content.getWidth()/20, content.getHeight()/7, 100, 16);
		content.add(filter_schoolyear);
		
		comboBox_schoolyear.setBounds(content.getWidth()/20 + filter_schoolyear.getWidth(), content.getHeight()/7, 100, 20);
		content.add(comboBox_schoolyear);
		
		JLabel filter_classes = new JLabel("LỚP");
		filter_classes.setFont(new Font("Arial", Font.BOLD, 16));
		filter_classes.setBounds(comboBox_schoolyear.getX()+comboBox_schoolyear.getWidth()+20, content.getHeight()/7, 50, 16);
		content.add(filter_classes);
		
		comboBox_classes.setBounds(filter_classes.getX() + filter_classes.getWidth()+10, content.getHeight()/7, 100, 20);
		content.add(comboBox_classes);
		
		
		JLabel lblNewLabel = new JLabel("DANH S\u00C1CH L\u1EDAP");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		//authentication
		if(curStudent.getRole()==0) {
			btn_import = new RoundedButton();
			btn_import.setText("Import");
			btn_import.setForeground(Color.WHITE);
			btn_import.setBackground(new Color(22,72,159));
			btn_import.setFont(new Font("Arial", Font.BOLD, 12));
			btn_import.setBounds(content.getWidth()-content.getWidth()/5,content.getHeight()/7, 100, 23);
			content.add(btn_import);	
			importListener();
		}
		filterListener();
		clickListener();
	}
	
	
	private void clickListener() {
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
		
	}
	
	private void filterListener() {
		comboBox_classes.addActionListener(new FilterListener());
		comboBox_schoolyear.addActionListener(new FilterListener2());
	}
	
	private Vector<String> getCurrentYear() {
		Vector<String> res = new Vector<>();
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		res.add(year.toString());
		year ++;
		res.add(year.toString());
		return res;
	}
	
	private void importListener() {
		btn_import.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				Vector<String> curYear= getCurrentYear();
				JComboBox<String> jcb = new JComboBox<String>(curYear);			
				JOptionPane.showMessageDialog( null, jcb, "Chọn niên khóa", JOptionPane.QUESTION_MESSAGE);
				String classID = JOptionPane.showInputDialog(contentPane, "Nhập vào mã lớp ");
				Transaction transaction = null;
				Classes checkClasses = null;
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					// start a transaction
		            transaction = session.beginTransaction();
		            checkClasses = session.get(Classes.class, classID);
		            //import student for every current course 
					transaction.commit();
				}catch (Exception e2) {
		            e2.printStackTrace();
		        }
				if(checkClasses != null) {
					JOptionPane.showMessageDialog(contentPane, "Lớp này đã tồn tại");
				}else {
					int choice = JOptionPane.showConfirmDialog(contentPane,"Niên khóa: "+jcb.getSelectedItem()+"\nLớp: "+classID+"\nBấm yes để chọn file dữ liệu !!!", "Import File",
				            JOptionPane.YES_NO_OPTION);
	
				    if (choice == JOptionPane.YES_OPTION){
				        JFileChooser c = new JFileChooser();
				        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				        int rVal = c.showOpenDialog(contentPane);
				        if (rVal == JFileChooser.APPROVE_OPTION) {
				        	String path=c.getSelectedFile().getAbsolutePath();
				        	Thread t1= new Thread(new Runnable() {
				    			@Override
				    			public void run() {
				    				// TODO Auto-generated method stub
				    				updateClasses(path,classID,(String)jcb.getSelectedItem());
				    				
				    			}
				    			
				    		});
				    		t1.start();
				        	
				        	
				        }
				        if (rVal == JFileChooser.CANCEL_OPTION) {
	
				        }
				    }
				}
			}
		});
		
	}
	

	private void updateClasses(String path,String classID,String schoolyear) {
		Transaction transaction = null;
		Vector<Vector<String>> filedata= readfile(path);
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			Classes newClasses = new Classes(classID,schoolyear);
			session.save(newClasses);
			for(int i = 0;i<filedata.size();i++) {
				Vector<String> cur=filedata.get(i);
				Student newStudent= new Student(cur.get(1),cur.get(2),cur.get(3),cur.get(4),classID,BCrypt.hashpw("123456", BCrypt.gensalt()),cur.get(5));
				session.save(newStudent);
			}
			//import student for every current course 
			transaction.commit();
		}
		initializeData();
	}
	
	private Vector<Vector<String>> readfile(String path) {
		// TODO Auto-generated method stub
		BufferedReader bfr=null;
		String row="";
		Vector<Vector<String>> res = new Vector<>();
		try {
			bfr= new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.defaultCharset()));
			bfr.readLine();
			while ((row = bfr.readLine()) != null) {
				Vector<String> temp = new Vector<>();
			    String[] data = row.split(",");
			    temp.add(data[0]);
			    temp.add(data[1]);
			    temp.add(data[2]);
			    temp.add(data[3]);
			    temp.add(data[4]);
			    temp.add(data[5]);
			    res.add(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//read header
		return res;
	}

	@SuppressWarnings("unchecked")
	private void initializeData() {
		
		if(column.isEmpty()) {
			//LOAD HEADER
			column.add("STT");
			column.add("MSSV");
			column.add("HỌ VÀ TÊN");
			column.add("GIỚI TÍNH");
			column.add("CMND");
			column.add("ĐỊA CHỈ");
		}
		
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();        
            Query q = session.createQuery("from Classes group by schoolyear,classID");
            List<Classes> l = new ArrayList<>(q.list());
            if(!l.isEmpty()) {
            	choose_year.clear();
            	for(Classes cur : l) {
            		choose_year.add(cur.getSchoolyear());
            	}
            }
           
            if(!choose_year.isEmpty()) {
            	
            	q = session.createQuery("from Classes where schoolyear = :year");
            	q.setParameter("year", choose_year.get(choose_year.size()-1));
            	List<Classes> l2 = new ArrayList<>(q.list());
            	if(!l2.isEmpty()) {
            		for(Classes cur :l2) {
            			choose_classes.clear();
            			choose_classes.add(cur.getClassID());
            		}
            		Classes curClasses = l2.get(0);
            		for(Integer i=0;i<curClasses.getStudents().size();i++) {
            			Student cur = curClasses.getStudents().get(i);
            			Vector<String> temp= new Vector<>();
            			temp.add(i.toString());
            			temp.add(cur.getStudentID());
            			temp.add(cur.getFullname());
            			temp.add(cur.getGender());
            			temp.add(cur.getIdCard());
            			temp.add(cur.getAddress());
            			data.add(temp);
            		}
            	}
            	comboBox_schoolyear.setSelectedIndex(choose_year.size()-1);
            	comboBox_classes.setSelectedIndex(0);
            }
            
            
            // commit transaction
            transaction.commit();
       } catch (Exception e) {
            e.printStackTrace();
        }	
	}
	
	private void allignCenterAllColumn(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0;i<table.getColumnCount();i++) {
			 table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
	}
	
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
	
	class FilterListener implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Thread t1= new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loadData();
					table.repaint();
				}
				
			});
			t1.start();
		}
	}
	class FilterListener2 implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Thread t1= new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loadClasses();
					table.repaint();
				}
				
			});
			t1.start();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadClasses() {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();        
            if(!choose_year.isEmpty()) {
            	
            	Query q = session.createQuery("from Classes where schoolyear = :year");
            	q.setParameter("year", comboBox_schoolyear.getSelectedItem());
            	List<Classes> l2 = new ArrayList<>(q.list());
            	data.clear();
            	if(!l2.isEmpty()) {
            		choose_classes.clear();
            		for(Classes cur :l2) {
            			choose_classes.add(cur.getClassID());
            		}
            		Classes curClasses = l2.get(0);
            		for(Integer i=0;i<curClasses.getStudents().size();i++) {
            			Student cur = curClasses.getStudents().get(i);
            			Vector<String> temp= new Vector<>();
            			temp.add(i.toString());
            			temp.add(cur.getStudentID());
            			temp.add(cur.getFullname());
            			temp.add(cur.getGender());
            			temp.add(cur.getIdCard());
            			temp.add(cur.getAddress());
            			data.add(temp);
            		}
            		
            		comboBox_classes.setSelectedIndex(0);
            		comboBox_classes.repaint();
            	}
            }
           
            // commit transaction
            transaction.commit();
       } catch (Exception e) {
            e.printStackTrace();
        }	
	}
	
	@SuppressWarnings("unchecked")
	private void loadData() {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();        
            if(!choose_year.isEmpty()) {
            	
            	Query q = session.createQuery("from Classes where classID= :id");
            	q.setParameter("id", comboBox_classes.getSelectedItem());
            	List<Classes> l2 = new ArrayList<>(q.list());
            	data.clear();
            	if(!l2.isEmpty()) {
            		
            		Classes curClasses = l2.get(0);
            		for(Integer i=0;i<curClasses.getStudents().size();i++) {
            			Student cur = curClasses.getStudents().get(i);
            			Vector<String> temp= new Vector<>();
            			temp.add(i.toString());
            			temp.add(cur.getStudentID());
            			temp.add(cur.getFullname());
            			temp.add(cur.getGender());
            			temp.add(cur.getIdCard());
            			temp.add(cur.getAddress());
            			data.add(temp);
            		}
            	}
            }
           
            // commit transaction
            transaction.commit();
       } catch (Exception e) {
            e.printStackTrace();
        }	
	}
}
