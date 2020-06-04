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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import Object.CurrentCourse;
import Object.CurrentCourseInfo;
import Object.Schedule;
import Object.Student;
import Object.StudentAndYear;
import Util.HeaderRenderer;
import Util.HibernateUtil;
import Util.RoundedButton;
import javax.swing.JButton;

public class UI_CurrentCourseInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private Student curStudent;
	private String curCourseID;
	private JPanel contentPane;
	private String curScheduleID;
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();//{"STT","MÃ MÔN","TÊN MÔN", "PHÒNG HỌC","THỜI GIAN"};
    private JTable table;
    private JLabel label_course;
    private JButton deleteRow;
    private JButton insertRow;
    private JLabel sign_out;
    private JLabel schedule;
    private JLabel Dashboard;
  

	public UI_CurrentCourseInfo(Student student,String curCourse,String scheduleID) {
		this.curScheduleID=scheduleID;
		this.curCourseID=curCourse;
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
		
		JLabel list_cep = new JLabel("Hồ sơ cần duyệt");
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
		
		JLabel filter_course = new JLabel("MÔN");
		filter_course.setFont(new Font("Arial", Font.BOLD, 16));
		filter_course.setBounds(content.getWidth()/20, content.getHeight()/7, 46, 16);
		content.add(filter_course);
		
		this.label_course = new JLabel(this.curCourseID);
		label_course.setFont(new Font("Arial", Font.BOLD, 14));
		label_course.setHorizontalAlignment(SwingConstants.CENTER);
		label_course.setOpaque(true);
		this.label_course.setBounds(content.getWidth()/20 + filter_course.getWidth(), content.getHeight()/7, 120, 20);
		content.add(this.label_course);
		
		insertRow = new RoundedButton();
		insertRow.setBackground(new Color(22,72,159));
		insertRow.setForeground(Color.WHITE);
		insertRow.setFont(new Font("Arial", Font.BOLD, 12));
		insertRow.setText("Thêm SV");
		insertRow.setBounds(label_course.getX() + 130, content.getHeight()/7, 90, 23);
		content.add(insertRow);
		
		deleteRow = new RoundedButton();
		deleteRow.setBackground(new Color(22,72,159));
		deleteRow.setForeground(Color.WHITE);
		deleteRow.setFont(new Font("Arial", Font.BOLD, 12));
		deleteRow.setText("Xóa SV");
		deleteRow.setBounds(insertRow.getX() + 100, content.getHeight()/7, 90, 23);
		content.add(deleteRow);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH HỌC SINH TỪNG MÔN HỌC");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/20, content.getWidth(), 20);
		content.add(lblNewLabel);
		
		buttonListener();
		clickListener();
	}
	
	private void clickListener() {
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
	}
	
	private void buttonListener() {
		insertRow.addActionListener(new BListener());
		deleteRow.addActionListener(new BListener());
	}
	
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
	
	class BListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton temp=(JButton) e.getSource();
			switch(temp.getText()) {
				case "Thêm SV": insertRow();break;
				case "Xóa SV": deleteRow();break;
			}
			
		}

		@SuppressWarnings({ "unchecked", "unused" })
		private void deleteRow() {
			// TODO Auto-generated method stub
			String MSSV =JOptionPane.showInputDialog("Nhập vào MSSV: ");
			Transaction transaction = null;
			Student student = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        	 // start a transaction
	            transaction = session.beginTransaction();
	            student = session.get(Student.class, MSSV);
	            if(student == null) {
	            	JOptionPane.showMessageDialog(contentPane, "Không tồn tại sinh viên !!!");
	            }else {
	            	Vector<String> res = new Vector<>();
	            	res.add(Integer.toString(data.size()));
	            	res.add(student.getStudentID());
	            	res.add(student.getFullname());
	            	res.add(student.getIdCard());
	            	if(!isContains(data,res)) {
	            		JOptionPane.showMessageDialog(contentPane, "Không tồn tại sinh viên trong danh sách lớp này !!!");
	            	}
	            	else {
	            		int i = getRemoveElementID(data,res);
	            		data.remove(i);
	            		Query query = session.createQuery("from CurrentCourseInfo where currentCourse = :id");
	            		query.setParameter("id", curCourseID);
	            		List<CurrentCourseInfo>list = new ArrayList<>(query.list());
	            		query = session.createQuery("from CurrentCourse where currentCourseID = :id");
	            		query.setParameter("id", curCourseID);
	            		List<CurrentCourse>list2 = new ArrayList<>(query.list());
	            		if(!list.isEmpty()) {
		            		for(CurrentCourseInfo x: list) {
		            			if(x.getStudent().getStudentID().compareTo(student.getStudentID())==0) {
		            				Query query2 = session.createQuery("delete from CurrentCourseInfo where currentCourseInfoID = :id");
		            				query2.setParameter("id", x.getCurrentCourseInfoID());
		            				query2.executeUpdate();		
		            				break;
		            			}
		            		}
		            		table.repaint();
			            	DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			                tableModel.fireTableDataChanged();
	            		}
	            		if(!list2.isEmpty()) {
	            			for(CurrentCourse x : list2) {
	            				if(x.getScheduleID().split("-")[0].compareTo(student.getStudentID())==0) {
	            					Query query3 = session.createQuery("delete from CurrentCourse where scheduleID = :id");
		            				query3.setParameter("id",x.getScheduleID());
		            				query3.executeUpdate();		
		            				break;
	            				}
	            			}
	            		}
	            	}
	            }
	            // commit transaction
	            transaction.commit();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		}

		private int getRemoveElementID(Vector<Vector<String>> list, Vector<String> student) {
			// TODO Auto-generated method stub
				for(int i=0;i<list.size();i++) {
					Vector<String> temp=list.get(i);
					if(temp.get(1).compareTo(student.get(1))==0){ //Column studentID
						return i;
						}
					}
			return -1;
		}

		@SuppressWarnings("unchecked")
		private void insertRow() {
			// TODO Auto-generated method stub
			String MSSV =JOptionPane.showInputDialog("Nhập vào MSSV: ");
			Transaction transaction = null;
			Student student = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        	 // start a transaction
	            transaction = session.beginTransaction();
	            student = session.get(Student.class, MSSV);
	            if(student == null) {
	            	JOptionPane.showMessageDialog(contentPane, "Không tồn tại sinh viên !!!");
	            }else {
	            	Vector<String> res = new Vector<>();
	            	res.add(Integer.toString(data.size()));
	            	res.add(student.getStudentID());
	            	res.add(student.getFullname());
	            	res.add(student.getIdCard());
	            	if(isContains(data,res)) {
	            		JOptionPane.showMessageDialog(contentPane, "Đã tồn tại sinh viên trong danh sách lớp này !!!");
	            	}else {
	            		data.add(res);
	            		String[] split= curCourseID.split("-");
	            		CurrentCourse cc=session.get(CurrentCourse.class,curScheduleID);
	            		Schedule sche = session.get(Schedule.class, curScheduleID);
	            		String scheduleID = student.getStudentID()+"-"+sche.getYear()+"-"+sche.getTerm();
	            		//Check if schedule is created
	            		Schedule t=null;
	            		t= session.get(Schedule.class,scheduleID);
	            		//check if new schoolyear
	        			Query o = session.createQuery("from StudentAndYear where studentID = :id");
	                    o.setParameter("id", student.getStudentID());
	                    List<StudentAndYear> c = new ArrayList<StudentAndYear>(o.list());
	                    for(int i=0;i<c.size();i++) {
	                    	StudentAndYear d = c.get(i);
	                    	if(d.getYear().compareTo(sche.getYear())==0) {
	                    		break;
	                    	}
	                    	if(i==c.size()-1) {
	                    		session.save(new StudentAndYear(student.getStudentID(),sche.getYear()));
	                    	}
	                    }
	            		//new info for new Student
	            		Schedule newsche=new Schedule(scheduleID,sche.getYear(),sche.getTerm());
	            		CurrentCourse newcc=new CurrentCourse(cc.getCurrentCourseID(),cc.getCourse(),new Classes(student.getClasses()),cc.getLocation(),cc.getStartingTime(),scheduleID);
	            		CurrentCourseInfo ccinfo=new CurrentCourseInfo(student.getStudentID()+"-"+split[1],curCourseID,student);
	            		session.save(ccinfo);
	            		session.save(newcc);
	            		//check if schedule is created
	            		if(t==null) {
	            			session.save(newsche);
	            		}
	            		
	            	}
	            	table.repaint();
	            	DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	                tableModel.fireTableDataChanged();
	            }
	            // commit transaction
	            transaction.commit();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
		}

		private boolean isContains(Vector<Vector<String>> list,Vector<String> student) {
			// TODO Auto-generated method stub
			for(int i=0;i<list.size();i++) {
				Vector<String> temp=list.get(i);
				if(temp.get(1).compareTo(student.get(1))==0){ //Column studentID
					return true;
				}
			}
			return false;
		}
		
	}
	
	
	private void allignCenterAllColumn(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0;i<table.getColumnCount();i++) {
			 table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void initializeData() {
		//data table header
		column.add("STT");
		column.add("MSSV");
		column.add("HỌ VÀ TÊN");
		column.add("CMND");
		
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	 // start a transaction
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CurrentCourse where scheduleID= :scheduleID");
            query.setParameter("scheduleID", this.curScheduleID);
            
            List<CurrentCourse> res= new ArrayList<>(query.list());
            query = session.createQuery("from CurrentCourseInfo where currentCourse = :id");
            query.setParameter("id", res.get(0).getCurrentCourseID());
            List<CurrentCourseInfo> students = new ArrayList<>(query.list());
            for(Integer i = 0; i< students.size();i++) {
            	CurrentCourseInfo cur = students.get(i);
            	Vector<String> row = new Vector<>();
            	row.add(i.toString());
            	row.add(cur.getStudent().getStudentID());
            	row.add(cur.getStudent().getFullname());
            	row.add(cur.getStudent().getIdCard());
            	data.add(row);
            }
            // commit transaction
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
}
