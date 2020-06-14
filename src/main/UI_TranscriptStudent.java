package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import MyListener.MyListener;

import Object.CurrentCourse;
import Object.Student;
import Object.StudentAndYear;
import Object.Transcript;
import Util.HeaderRenderer;
import Util.HibernateUtil;

public class UI_TranscriptStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
	private JLabel schedule;
	private JLabel profile;
	private JTable table;
	private JLabel cep;
	private JLabel list_cep;
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();
    private Vector<String> choose_term = new Vector<>();
    private Vector<String> choose_year = new Vector<>();
    private JComboBox<String> comboBox_term;
    private JComboBox<String> comboBox_year;
    

	public UI_TranscriptStudent(Student student) {
		this.curStudent=student;
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initializeData();
				comboBox_year.setSelectedIndex(0);
				table.repaint();
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
		
		JLabel transcripts = new JLabel("B\u1EA3ng \u0111i\u1EC3m");
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		JLabel filter_term = new JLabel("HỌC KỲ");
		filter_term.setFont(new Font("Arial", Font.BOLD, 16));
		filter_term.setBounds(content.getWidth()/20, content.getHeight()/7, 70, 16);
		content.add(filter_term);
		
		comboBox_term = new JComboBox<String>(choose_term);
		comboBox_term.setBounds(filter_term.getX() + 80, content.getHeight()/7, 50, 20);
		content.add(comboBox_term);
		
		JLabel filter_year = new JLabel("NĂM HỌC");
		filter_year.setFont(new Font("Arial", Font.BOLD, 16));
		filter_year.setBounds(comboBox_term.getX()+comboBox_term.getWidth()+20, content.getHeight()/7, 100, 16);
		content.add(filter_year);
		
		comboBox_year = new JComboBox<String>(choose_year);
		comboBox_year.setBounds(filter_year.getX()+90, content.getHeight()/7, 90, 20);
		content.add(comboBox_year);
		
		JLabel lblNewLabel = new JLabel("BẢNG ĐIỂM");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		
		
		filterListener();
		clickListener();
	}
	
	private void clickListener() {
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
	}
	
	private void filterListener() {
		comboBox_year.addActionListener (new FilterListener());
		comboBox_term.addActionListener(new FilterListener());
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
	@SuppressWarnings("unchecked")
	private void loadData() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	// start a transaction
            transaction = session.beginTransaction();
            List<Transcript>transcripts;
            Query query = session.createQuery("from CurrentCourse where scheduleID = :id");
            String scheduleID=curStudent.getStudentID()+"-"+comboBox_year.getSelectedItem()+"-"+comboBox_term.getSelectedItem();
            query.setParameter("id", scheduleID);
            List<CurrentCourse> schedules= new ArrayList<>(query.list());
            data.clear();
            if(schedules.isEmpty()) {
	           	return;
           	}
            for(Integer i=0;i<schedules.size();i++) {
            	CurrentCourse cur=schedules.get(i);
	            query = session.createQuery("from Transcript where transcriptID= :id");
	            query.setParameter("id", curStudent.getStudentID()+"-"+cur.getCourse().getCourseID());
	            transcripts = new ArrayList<>(query.list());
	            for(Integer j=0;j< transcripts.size();j++) {
	            	Transcript x = transcripts.get(j);
	            	Vector<String> t2 =new Vector<>();       
	            	t2.add(i.toString());
	            	t2.add(cur.getCourse().getCourseID());
	           		t2.add(cur.getCourse().getCourseName());
	           		t2.add(Float.toString(x.getMidtermMark()));
	           		t2.add(Float.toString(x.getFinaltermMark()));
	            	t2.add(Float.toString(x.getOtherMark()));
	            	t2.add(Float.toString(x.getTotalMark()));
	            	data.add(t2);
	            }
            }
        	// commit transaction
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
	private void allignCenterAllColumn(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0;i<table.getColumnCount();i++) {
			 table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
	}

	@SuppressWarnings("unchecked")
	private void initializeData() {
		// TODO Auto-generated method stub
		choose_term.add("1");
		choose_term.add("2");
		choose_term.add("3");
		
		//LOAD HEADER
		column.add("STT");
		column.add("MÃ MÔN");
		column.add("TÊN MÔN");
		column.add("ĐIỂM GIỮA KÌ");
		column.add("ĐIỂM CUỐI KÌ");
		column.add("ĐIỂM KHÁC");
		column.add("ĐIỂM TỔNG");
		
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	// start a transaction
            transaction = session.beginTransaction();  
            Query t = session.createQuery("from StudentAndYear where studentID = :id");
            t.setParameter("id", curStudent.getStudentID());
            List<StudentAndYear> l = new ArrayList<StudentAndYear>(t.list());
            for(StudentAndYear x : l) {
            	choose_year.add(x.getYear());
            }
            
            List<Transcript>transcripts;
            Query query = session.createQuery("from CurrentCourse where scheduleID = :id");
            if(!choose_year.isEmpty() && !choose_term.isEmpty()) {
	            String scheduleID=curStudent.getStudentID()+"-"+choose_year.get(0)+"-"+choose_term.get(0);
	            query.setParameter("id", scheduleID);
	            List<CurrentCourse> schedules= new ArrayList<>(query.list());
	            
	            if(schedules.isEmpty()) {
		           	return;
	           	}
	            for(Integer i=0;i<schedules.size();i++) {
	            	CurrentCourse cur=schedules.get(i);
		            query = session.createQuery("from Transcript where transcriptID= :id");
		            query.setParameter("id", curStudent.getStudentID()+"-"+cur.getCourse().getCourseID());
		            transcripts = new ArrayList<>(query.list());
		            for(Integer j=0;j< transcripts.size();j++) {
		            	Transcript x = transcripts.get(j);
		            	Vector<String> t2 =new Vector<>();       
		            	t2.add(i.toString());
		            	t2.add(cur.getCourse().getCourseID());
		           		t2.add(cur.getCourse().getCourseName());
		           		t2.add(Float.toString(x.getMidtermMark()));
		           		t2.add(Float.toString(x.getFinaltermMark()));
		            	t2.add(Float.toString(x.getOtherMark()));
		            	t2.add(Float.toString(x.getTotalMark()));
		            	data.add(t2);
		            }
	            }
            }
        	// commit transaction
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
