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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import MyListener.MyListener;
import Object.Classes;
import Object.Course;
import Object.CurrentCourse;
import Object.CurrentCourseInfo;
import Object.Schedule;
import Object.Student;
import Object.StudentAndYear;
import Util.HeaderRenderer;
import Util.HibernateUtil;
import Util.RoundedButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;

public class UI_Schedule extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
	private JLabel transcripts;
	private JLabel profile;
	private JLabel cep;
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();//{"STT","MÃ MÔN","TÊN MÔN", "PHÒNG HỌC","THỜI GIAN"};
    private Vector<String> choose_classes = new Vector<>();
    private Vector<String> choose_term = new Vector<>();
    private Vector<String> choose_year = new Vector<>();
    private JComboBox<String> comboBox_term;
    private JComboBox<String> comboBox_year;
    private JComboBox<String> comboBox_classes;
    private JTable table;
    private JButton btn_import;

	public UI_Schedule(Student student) {
		this.curStudent=student;
		initializeData();
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
		
		JLabel schedule = new JLabel("Th\u1EDDi kh\u00F3a bi\u1EC3u");
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
		
		JLabel list_cep = new JLabel("Danh s\u00E1ch ph\u00FAc kh\u1EA3o");
		list_cep.setFont(new Font("Arial", Font.BOLD, 14));
		list_cep.setForeground(Color.WHITE);
		list_cep.setHorizontalAlignment(SwingConstants.LEFT);
		list_cep.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_6.png")));
		list_cep.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(list_cep);
		
		JLabel list_classes = new JLabel("Danh s\u00E1ch l\u1EDBp");
		list_classes.setFont(new Font("Arial", Font.BOLD, 14));
		list_classes.setForeground(Color.WHITE);
		list_classes.setHorizontalAlignment(SwingConstants.LEFT);
		list_classes.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_7.png")));
		list_classes.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(list_classes);
		
		sign_out = new JLabel("\u0110\u0103ng xu\u1EA5t");
		sign_out.setFont(new Font("Arial", Font.BOLD, 14));
		sign_out.setForeground(Color.WHITE);
		sign_out.setHorizontalAlignment(SwingConstants.LEFT);
		sign_out.setIcon(new ImageIcon(UI_Schedule.class.getResource("/img/navi_icon_8.png")));
		sign_out.setBorder(new EmptyBorder(0,10,0,0));
		navi_menu.add(sign_out);
		
		JPanel content = new JPanel();
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
		
		JLabel filter_classes = new JLabel("MÔN");
		filter_classes.setFont(new Font("Arial", Font.BOLD, 16));
		filter_classes.setBounds(content.getWidth()/20, content.getHeight()/7, 46, 16);
		content.add(filter_classes);
		
		comboBox_classes = new JComboBox<String>(choose_classes);
		comboBox_classes.setBounds(content.getWidth()/20 + filter_classes.getWidth(), content.getHeight()/7, 90, 20);
		content.add(comboBox_classes);
		
		JLabel filter_term = new JLabel("HỌC KỲ");
		filter_term.setFont(new Font("Arial", Font.BOLD, 16));
		filter_term.setBounds(comboBox_classes.getX()+comboBox_classes.getWidth()+20, content.getHeight()/7, 70, 16);
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
		
		JLabel lblNewLabel = new JLabel("THỜI KHÓA BIỂU");
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
	
	class TableListener extends MouseAdapter{
		 public void mouseClicked(MouseEvent e)  
		 {  
			 int row = table.rowAtPoint(e.getPoint());
		     int col = table.columnAtPoint(e.getPoint());
		     if(col == 1) {
		    	 String curCourse = (String)table.getValueAt(row, col);
		    	 UI_CurrentCourseInfo frame = new UI_CurrentCourseInfo(curStudent,comboBox_classes.getSelectedItem( )+"-"+curCourse,comboBox_classes.getSelectedItem( )+"-"+comboBox_year.getSelectedItem()+"-" +comboBox_term.getSelectedItem());
		    	 frame.setVisible(true);
		    	 dispose();
		     }
		 }  
	}
	private void clickListener() {
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		
		if(curStudent.getRole()==0) {
			table.addMouseListener(new TableListener());
		}
	}
	
	private void filterListener() {
		comboBox_year.addActionListener (new FilterListener());
		comboBox_term.addActionListener(new FilterListener());
		comboBox_classes.addActionListener(new FilterListener());
	}
	
	private Vector<String> getCurrentYear() {
		Vector<String> res = new Vector<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		res.add(year-1+"-"+year);
		res.add(year+"-"+(year+1));
		return res;
	}
	
	private void importListener() {
		btn_import.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				JComboBox<String> jcb = new JComboBox<String>(choose_classes);			
				JOptionPane.showMessageDialog( null, jcb, "Chọn lớp cần import", JOptionPane.QUESTION_MESSAGE);
				Vector<String> curYear= getCurrentYear();
				JComboBox<String> jcb2 = new JComboBox<String>(curYear);			
				JOptionPane.showMessageDialog( null, jcb2, "Chọn năm học", JOptionPane.QUESTION_MESSAGE);
				JComboBox<String> jcb3 = new JComboBox<String>(choose_term);			
				JOptionPane.showMessageDialog( null, jcb3, "Chọn học kỳ", JOptionPane.QUESTION_MESSAGE);
				if(!choose_classes.contains(jcb.getSelectedItem())) {
					JOptionPane.showMessageDialog(contentPane, "Lớp này không tồn tại");
				}else {
					int choice = JOptionPane.showConfirmDialog(contentPane,"Lớp: "+jcb.getSelectedItem()+"\nNăm học: "+jcb2.getSelectedItem()+"\nHọc kỳ: "+jcb3.getSelectedItem()+"\nBấm yes để chọn file dữ liệu !!!", "Import File",
				            JOptionPane.YES_NO_OPTION);
	
				    if (choice == JOptionPane.YES_OPTION){
				        JFileChooser c = new JFileChooser();
				        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				        int rVal = c.showOpenDialog(contentPane);
				        if (rVal == JFileChooser.APPROVE_OPTION) {
				        	String path=c.getSelectedFile().getAbsolutePath();
				        	String classesID= (String) jcb.getSelectedItem();
				        	String year= (String)jcb2.getSelectedItem();
				        	String term= (String)jcb3.getSelectedItem();
				        	updateSchedule(path,classesID,year,term);
				        }
				        if (rVal == JFileChooser.CANCEL_OPTION) {
	
				        }
				    }
				}
			}
		});
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void updateSchedule(String path,String curClasses,String year,String term) {
		Transaction transaction = null;
		Classes t=null;
		Vector<Vector<String>> filedata= readfile(path);
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			t=session.get(Classes.class, curClasses);
			System.out.println(t.getStudents().toString());
			//import schedule for admin
			String classesSchedule=curClasses+"-"+year+"-"+term;
			session.save(new Schedule(classesSchedule,year,term));
			for(Vector<String> x : filedata) {
				String currentCourseID = curClasses+"-"+x.get(1);
				Course course = new Course(x.get(1),x.get(2));
				Classes classes = new Classes(curClasses);
				session.save(new CurrentCourse(currentCourseID,course,classes,x.get(3),x.get(4),classesSchedule));
			}
			//if new schoolyear
			Query o = session.createQuery("from StudentAndYear where studentID = :id");
            o.setParameter("id", curStudent.getStudentID());
            List<StudentAndYear> c = new ArrayList<StudentAndYear>(o.list());
            for(int i=0;i<c.size();i++) {
            	StudentAndYear d = c.get(i);
            	if(d.getYear().compareTo(year)==0) {
            		break;
            	}
            	if(i==c.size()-1) {
            		choose_year.add(year);
            		session.save(new StudentAndYear(curStudent.getStudentID(),year));
            	}
            }
			//import schedule for every student
			for(Student st : t.getStudents()) {
				String scheduleID =st.getStudentID()+"-"+year+"-"+term;
				o = session.createQuery("from StudentAndYear where studentID = :id");
	            o.setParameter("id", st.getStudentID());
	            List<StudentAndYear> l = new ArrayList<StudentAndYear>(o.list());
	            for(int i=0;i<l.size();i++) {
	            	StudentAndYear d = l.get(i);
	            	if(d.getYear().compareTo(year)==0) {
	            		break;
	            	}
	            	if(i==l.size()-1) {
	            		session.save(new StudentAndYear(st.getStudentID(),year));
	            	}
	            }
				//import current corse for every schedule
				for(Vector<String> x : filedata) {
					String currentCourseID = curClasses+"-"+x.get(1);
					Course course = new Course(x.get(1),x.get(2));
					Classes classes = new Classes(curClasses);
					CurrentCourseInfo ccinfo=new CurrentCourseInfo(st.getStudentID()+"-"+x.get(1),currentCourseID,st);
					session.save(new CurrentCourse(currentCourseID,course,classes,x.get(3),x.get(4),scheduleID));
					session.save(ccinfo);
				}
				session.save(new Schedule(scheduleID,year,term));
			}
			//import student for every current course 
			transaction.commit();
		}
		loadData();
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
		if(curStudent.getRole() == 1) {
			choose_classes.add(curStudent.getClasses());
		}
		
		
		
		choose_term.add("1");
		choose_term.add("2");
		choose_term.add("3");
		
		//LOAD HEADER
		column.add("STT");
		column.add("MÃ MÔN");
		column.add("TÊN MÔN");
		column.add("PHÒNG HỌC");
		column.add("THỜI GIAN");
		
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
                 
            // query a student
            List<Schedule> schedules;
            if(curStudent.getRole()==0) {
        	   Query query = session.createQuery("from Classes");
        	   List<Classes> classes= new ArrayList<>(query.list());
        	   for(Classes i :classes) {
        		   choose_classes.add(i.getClassID());
        	   }
        	   Query query2 = session.createQuery("from Schedule where scheduleID= :id");
        	   String str =choose_classes.get(0)+"-"+choose_year.get(0)+"-"+choose_term.get(0);
	           query2.setParameter("id", str);
	           schedules = new ArrayList<>(query2.list());
	           
           	 }else {
	           Query query2 = session.createQuery("from Schedule where scheduleID= :id");
	           query2.setParameter("id", curStudent.getStudentID()+"-"+choose_year.get(0)+"-"+choose_term.get(0));
	           schedules = new ArrayList<>(query2.list());
	           
           	 }
             if(schedules.isEmpty()) {
            	 return;
             }
	         for(Integer i = 0; i <  schedules.get(0).getCurrentCourses().size();i++) {
	           CurrentCourse cur = schedules.get(0).getCurrentCourses().get(i);
	           Vector<String> row = new Vector<>(); 
	           row.add(i.toString());
	           row.add(cur.getCourse().getCourseID());
	           row.add(cur.getCourse().getCourseName());
	           row.add(cur.getLocation());
	           row.add(cur.getStartingTime());
	           data.add(row);
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
			table.repaint();
			loadData();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadData() {
		Transaction transaction = null;
		List<Schedule> schedules =null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            if(curStudent.getRole() == 0) {
	            // query a student      
            	Query query = session.createQuery("from Schedule where scheduleID= :id");
            	String str= comboBox_classes.getSelectedItem()+"-"+comboBox_year.getSelectedItem()+"-"+comboBox_term.getSelectedItem();
	           	query.setParameter("id", str);
	           	schedules = new ArrayList<>(query.list());
	           
            }else {
            	Query query = session.createQuery("from Schedule where scheduleID= :id");
            	query.setParameter("id", curStudent.getStudentID()+"-"+comboBox_year.getSelectedItem()+"-"+comboBox_term.getSelectedItem());
            	schedules = new ArrayList<>(query.list());
            }
            data.clear();
            if(schedules.isEmpty()) {
           	 	return;
            }
	        for(Integer i = 0; i <  schedules.get(0).getCurrentCourses().size();i++) {
	          CurrentCourse cur = schedules.get(0).getCurrentCourses().get(i);
	          Vector<String> row = new Vector<>(); 
	          row.add(i.toString());
	          row.add(cur.getCourse().getCourseID());
	          row.add(cur.getCourse().getCourseName());
	          row.add(cur.getLocation());
	          row.add(cur.getStartingTime());
	          data.add(row);
	        }
            // commit transaction
            transaction.commit();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.fireTableDataChanged();
       } catch (Exception e) {
            e.printStackTrace();
        }	
	}
}
