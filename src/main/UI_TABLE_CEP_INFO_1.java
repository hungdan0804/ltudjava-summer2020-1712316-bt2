package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import Object.CheckExaminationPaper;
import Object.Student;
import Object.StudentAndYear;
import Util.HeaderRenderer;
import Util.HibernateUtil;


public class UI_TABLE_CEP_INFO_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
    private JLabel schedule;
    private JLabel profile;
    private JLabel transcripts;
    private JLabel cep;
    private JLabel list_classes;
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();
    private JTable table;
    private JComboBox<String> comboBox_year;
    private Vector<String> choose_year = new Vector<>();

	public UI_TABLE_CEP_INFO_1(Student student) {
		this.curStudent=student;
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initializeData();
				comboBox_year.setSelectedIndex(0);				
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
		
		JLabel list_cep = new JLabel("H\u1ED3 s\u01A1 c\u1EA7n duy\u1EC7t");
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
		
		
		JLabel filter_year = new JLabel("NĂM HỌC");
		filter_year.setFont(new Font("Arial", Font.BOLD, 16));
		filter_year.setBounds(content.getWidth()/20, content.getHeight()/7, 90, 16);
		content.add(filter_year);
		
		comboBox_year = new JComboBox<String>(choose_year);
		comboBox_year.setBounds(content.getWidth()/20 + filter_year.getWidth(), content.getHeight()/7, 120, 20);
		content.add(comboBox_year);
		
		JLabel lblNewLabel = new JLabel("H\u1ED2 S\u01A0 C\u1EA6N DUY\u1EC6T");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		
		clickListener();
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
	private void initializeData() {
		// TODO Auto-generated method stub
		column.add("STT");
		column.add("TIÊU ĐỀ");
		column.add("NGÀY BẮT ĐẦU");
		column.add("NGÀY KẾT THÚC");
		
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
            
            t = session.createQuery("from CheckExaminationPaper where year = :year");
            t.setParameter("year", choose_year.get(choose_year.size()-1));
            
            List<CheckExaminationPaper> l2 = new ArrayList<CheckExaminationPaper>(t.list());
            if(l2.isEmpty()) {
            	return;
            }
            else {
            	Vector<String> temp= null;
            	Locale.setDefault(new Locale("vi", "VN"));
            	for(Integer i =0;i<l2.size();i++) {
            		CheckExaminationPaper cur = l2.get(i);
            		temp=new Vector<>();
            		temp.add(i.toString());
            		temp.add(cur.getTitle());
            		temp.add(cur.getStartingDate().toString());
            		temp.add(cur.getEndingDate().toString());
            		data.add(temp);
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
		// TODO Auto-generated method stub	
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction(); 
            Query t = session.createQuery("from CheckExaminationPaper where year = :year");
            t.setParameter("year", comboBox_year.getSelectedItem());
            List<CheckExaminationPaper> l2 = new ArrayList<CheckExaminationPaper>(t.list());
            data.clear();
            if(l2.isEmpty()) {
            	return;
            }
            else {
            	Vector<String> temp= null;
            	Locale.setDefault(new Locale("vi", "VN"));
            	for(Integer i =0;i<l2.size();i++) {
            		CheckExaminationPaper cur = l2.get(i);
            		temp=new Vector<>();
            		temp.add(i.toString());
            		temp.add(cur.getTitle());
            		temp.add(cur.getStartingDate().toString());
            		temp.add(cur.getEndingDate().toString());
            		data.add(temp);
            	}
            }        
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}
	class TableListener extends MouseAdapter{
		 public void mouseClicked(MouseEvent e)  
		 {  
			 int row = table.rowAtPoint(e.getPoint());
		     if(!data.isEmpty()) {
		    	 String curCEP = (String)table.getValueAt(row, 1);
		    	 UI_TABLE_CEP_INFO_2 frame = new UI_TABLE_CEP_INFO_2(curStudent,curCEP);
		    	 frame.setVisible(true);
		    	 dispose();
		     }
		 }  
	}
	private void clickListener(){
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		cep.addMouseListener(new MyListener(curStudent,this));
		list_classes.addMouseListener(new MyListener(curStudent,this));
		comboBox_year.addActionListener (new FilterListener());
		comboBox_year.setSelectedIndex(choose_year.size()-1);
		
		table.addMouseListener(new TableListener());
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
}
