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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import MyListener.MyListener;
import Object.Classes;
import Object.CurrentCourse;
import Object.Student;
import Object.StudentAndYear;
import Object.Transcript;
import Util.HeaderRenderer;
import Util.HibernateUtil;
import Util.RoundedButton;
import Util.RoundedLabel;

public class UI_Transcript extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
	private JTable table;
	private JLabel profile;
	private JLabel ratio_pass = new RoundedLabel();
	private JLabel ratio_fail = new RoundedLabel();
	private Vector<Vector<String>> data= new Vector<Vector<String>>();
    private Vector<String> column=new Vector<>();
    private Vector<String> choose_classes = new Vector<>();
    private Vector<String> choose_course = new Vector<>();
    private Vector<String> choose_term = new Vector<>();
    private Vector<String> choose_year = new Vector<>();
    private Vector<String> choose_pass = new Vector<>();
    private JComboBox<String> comboBox_term;
    private JComboBox<String> comboBox_year;
    private JComboBox<String> comboBox_course;
    private JComboBox<String> comboBox_pass;
    private JButton btn_change;
    private JButton btn_import;
    private JLabel schedule;

	public UI_Transcript(Student student){
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
		
		JLabel cep = new JLabel("Ph\u00FAc kh\u1EA3o \u0111i\u1EC3m");
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
		
		content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setBounds(navigation.getWidth(),header.getHeight(),contentPane.getWidth()-navigation.getWidth(),contentPane.getHeight()-header.getHeight());
		contentPane.add(content);
		content.setLayout(null);
		
		table = new JTable(data,column) {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 3 || column==4 || column==5 || column==6? true : false;
		    }
		};
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(true);
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
		
		comboBox_course = new JComboBox<String>(choose_course);
		comboBox_course.setBounds(content.getWidth()/20 + filter_course.getWidth(), content.getHeight()/7, 130, 20);
		content.add(comboBox_course);
		
		JLabel filter_term = new JLabel("HỌC KỲ");
		filter_term.setFont(new Font("Arial", Font.BOLD, 16));
		filter_term.setBounds(comboBox_course.getX()+comboBox_course.getWidth()+20, content.getHeight()/7, 70, 16);
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
		
		btn_import = new RoundedButton();
		btn_import.setText("Import");
		btn_import.setForeground(Color.WHITE);
		btn_import.setBackground(new Color(22,72,159));
		btn_import.setFont(new Font("Arial", Font.BOLD, 12));
		btn_import.setBounds(comboBox_year.getX()+130,content.getHeight()/7-1, 100, 23);
		content.add(btn_import);
			
		btn_change = new RoundedButton();
		btn_change.setText("Thay đổi");
		btn_change.setForeground(Color.WHITE);
		btn_change.setBackground(new Color(22,72,159));
		btn_change.setFont(new Font("Arial", Font.BOLD, 12));
		btn_change.setBounds(btn_import.getX()+120,content.getHeight()/7-1, 100, 23);
		content.add(btn_change);	
		btn_change.setVisible(false);
			
		JLabel filter_pass = new JLabel("LOẠI");
		filter_pass.setFont(new Font("Arial", Font.BOLD, 16));
		filter_pass.setBounds(content.getWidth()/20, content.getHeight()/10+1, 46, 16);
		content.add(filter_pass);
			
		comboBox_pass = new JComboBox<String>(choose_pass);
		comboBox_pass.setBounds(content.getWidth()/20 + filter_course.getWidth(), content.getHeight()/10, 70, 20);
		content.add(comboBox_pass);
			
		JLabel filter_ratio = new JLabel("TỶ LỆ");
		filter_ratio.setFont(new Font("Arial", Font.BOLD, 16));
		filter_ratio.setBounds(comboBox_pass.getX()+80, content.getHeight()/10+1, 50, 16);
		content.add(filter_ratio);
			
		ratio_pass.setForeground(Color.WHITE);
		ratio_pass.setBackground(Color.GREEN);
		ratio_pass.setHorizontalAlignment(SwingConstants.CENTER);
		ratio_pass.setFont(new Font("Arial", Font.BOLD, 14));
		ratio_pass.setBounds(filter_ratio.getX()+60, content.getHeight()/10-4, 80, 25);
		content.add(ratio_pass);
			
		ratio_fail.setForeground(Color.WHITE);
		ratio_fail.setBackground(Color.RED);
		ratio_fail.setHorizontalAlignment(SwingConstants.CENTER);
		ratio_fail.setFont(new Font("Arial", Font.BOLD, 14));
		ratio_fail.setBounds(ratio_pass.getX()+90, content.getHeight()/10-4, 80, 25);
		content.add(ratio_fail);
	
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
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				// TODO Auto-generated method stub
				if(table.isEditing()) {
					String value = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
					try {
						float a = Float.parseFloat(value);
						if(a<10 && a>0) {
							
							Transaction transaction = null;
							try (Session session = HibernateUtil.getSessionFactory().openSession()) {
								// start a transaction
					            transaction = session.beginTransaction();
					            String[]course = comboBox_course.getSelectedItem().toString().split("-");
					            String transcriptID=table.getValueAt(table.getSelectedRow(),1)+"-"+course[1];
					            Transcript transcript= (Transcript)session.get(Transcript.class,transcriptID);
					            switch(table.getSelectedColumn()) {
									case 3: transcript.setMidtermMark(Float.parseFloat(value));break;
									case 4:	transcript.setFinaltermMark(Float.parseFloat(value));break;
									case 5:	transcript.setOtherMark(Float.parseFloat(value));break;
									case 6: transcript.setTotalMark(Float.parseFloat(value));break;		
					            }
					            session.merge(transcript);
					            //import student for every current course 
								transaction.commit();
								loadData();
							}catch (Exception e2) {
					            e2.printStackTrace();
					        }
						}else {
							JOptionPane.showMessageDialog(contentPane,"Nhập sai Format điểm !!!");
							loadData();
						}
					}catch(NumberFormatException e) {
						e.printStackTrace();
						loadData();
			        	JOptionPane.showMessageDialog(contentPane,"Nhập sai Format điểm !!!");
					}
				}
			        
			}
		});
		btn_import.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "unused" })
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<String> jcb = new JComboBox<String>(choose_classes);			
				JOptionPane.showMessageDialog( null, jcb, "Chọn lớp", JOptionPane.QUESTION_MESSAGE);
				JComboBox<String> jcb2 = new JComboBox<String>(choose_year);			
				JOptionPane.showMessageDialog( null, jcb2, "Chọn năm học", JOptionPane.QUESTION_MESSAGE);
				JComboBox<String> jcb3 = new JComboBox<String>(choose_term);
				JOptionPane.showMessageDialog( null, jcb3, "Chọn học kỳ", JOptionPane.QUESTION_MESSAGE);
				Transaction transaction = null;
				List<CurrentCourse> schedules=null;
				Vector<String> res=null;
		        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        	// start a transaction
		            transaction = session.beginTransaction(); 
					Query query = session.createQuery("from CurrentCourse where scheduleID = :id");
		            String scheduleID=jcb.getSelectedItem()+"-"+jcb2.getSelectedItem()+"-"+jcb3.getSelectedItem();
		            query.setParameter("id", scheduleID);
		            schedules= new ArrayList<>(query.list());
		            res=getCurrentCourseID(schedules);
		          //import student for every current course 
					transaction.commit();
		        }catch (Exception e) {
		            e.printStackTrace();
		        }
		        if(schedules.isEmpty()) {
		        	JOptionPane.showMessageDialog(contentPane, "Lớp: "+jcb.getSelectedItem()+"\nNăm học: "+jcb2.getSelectedItem()+"\nHọc kỳ: "+jcb3.getSelectedItem()+"\nKhông có môn học nào !!!");
		        	return;
		        }
		        JComboBox<String> jcb4 = new JComboBox<String>(res);
				JOptionPane.showMessageDialog( null, jcb4, "Chọn Môn Học", JOptionPane.QUESTION_MESSAGE);
				int choice = JOptionPane.showConfirmDialog(contentPane,"Lớp: "+jcb.getSelectedItem()+"\nNăm học: "+jcb2.getSelectedItem()+"\nHọc kỳ: "+jcb3.getSelectedItem()+"\nMôn học: "+jcb4.getSelectedItem()+"\nBấm yes để chọn file dữ liệu !!!", "Import File",
			            JOptionPane.YES_NO_OPTION);
			    if (choice == JOptionPane.YES_OPTION){
			        JFileChooser c = new JFileChooser();
			        c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			        int rVal = c.showOpenDialog(contentPane);
			        if (rVal == JFileChooser.APPROVE_OPTION) {
			        	String path=c.getSelectedFile().getAbsolutePath();
			        	String currentCourseID= (String) jcb4.getSelectedItem();
			        	updateTranscript(path,currentCourseID);
			        	
			        	comboBox_year.setSelectedItem(jcb2.getSelectedItem());
			        	comboBox_term.setSelectedItem(jcb3.getSelectedItem());
			        	comboBox_course.setSelectedItem(jcb4.getSelectedItem());
			        	loadData();	        	
			        }
			        if (rVal == JFileChooser.CANCEL_OPTION) {

			        }
			    }
			}

			private void updateTranscript(String path, String currentCourseID) {
				Transaction transaction = null;
				String[]split=currentCourseID.split("-");
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			        // start a transaction
			        transaction = session.beginTransaction(); 
			        Vector<Vector<String>>res=readfile(path);
			        for(Vector<String> x : res) {
			        	String transcriptID= x.get(1)+"-"+split[1];
			        	Student student= new Student(x.get(1));
			        	Transcript t=new Transcript(transcriptID,currentCourseID,student,Float.parseFloat(x.get(3)),Float.parseFloat(x.get(4)),Float.parseFloat(x.get(5)),Float.parseFloat(x.get(6)));
			        	session.save(t);
			        }
			        data.clear();
			        data.addAll(res);
			        
			        //commit a transaction
			        transaction.commit();
			    }catch (Exception e) {
			        e.printStackTrace();
			    }
				
			}

			private Vector<String> getCurrentCourseID(List<CurrentCourse> schedules) {
				Vector<String> res= new Vector<>();
				for(CurrentCourse x : schedules) {
					res.add(x.getCurrentCourseID());
				}
				return res;
			}
		});		
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
			    temp.add(data[6]);
			    res.add(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//read header
		return res;
	}
	
	@SuppressWarnings({"unchecked"})
	private void initializeData() {
			
		
		choose_term.add("1");
		choose_term.add("2");
		choose_term.add("3");
		
		choose_pass.add("Tất cả");
		choose_pass.add("Đậu");
		choose_pass.add("Rớt");
		
		//LOAD HEADER
		column.add("STT");
		column.add("MSSV");
		column.add("HỌ VÀ TÊN");
		column.add("ĐIỂM GK");
		column.add("ĐIỂM CK");
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
            //load All classes
            Query query = session.createQuery("from Classes");
            List<Classes> classes= new ArrayList<>(query.list());
            for(Classes i :classes) {
            	choose_classes.add(i.getClassID());
            }
            query = session.createQuery("from CurrentCourse where scheduleID = :id");
            String scheduleID=choose_classes.get(0)+"-"+choose_year.get(0)+"-"+choose_term.get(0);
            query.setParameter("id", scheduleID);
            List<CurrentCourse> schedules= new ArrayList<>(query.list());
            if(!schedules.isEmpty()) {
	           	for(CurrentCourse x: schedules) {
	          		choose_course.add(x.getCurrentCourseID());
	           	}
           	}else {
           		ratio_pass.setText("Đậu: " + "0%" );
                ratio_fail.setText("Rớt: " + "0%" );
                
            	return;
            }
            CurrentCourse cur= schedules.get(0);
            query = session.createQuery("from Transcript where currentCourse= :id");
            query.setParameter("id", cur.getCurrentCourseID());
            transcripts = new ArrayList<>(query.list());
            int passRatio=0;
            for(Integer i=0;i< transcripts.size();i++) {
            	Transcript x = transcripts.get(i);
            	Vector<String> t2 =new Vector<>();
            	passRatio+=checkPass(x);
            	t2.add(i.toString());
            	t2.add(x.getStudent().getStudentID());
           		t2.add(x.getStudent().getFullname());
           		t2.add(Float.toString(x.getMidtermMark()));
           		t2.add(Float.toString(x.getFinaltermMark()));
            	t2.add(Float.toString(x.getOtherMark()));
            	t2.add(Float.toString(x.getTotalMark()));
            	data.add(t2);
            }
           	int pass=Math.round((float)passRatio/transcripts.size()*100);         	
            ratio_pass.setText("Đậu: " + pass +"%" );
            ratio_fail.setText("Rớt: " + (100-pass) +"%" );
        	// commit transaction
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	private int checkPass(Transcript x) {
		if(x.getTotalMark()>=5.0f) {
			return 1;
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	private void loadCourse() {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	// start a transaction
            transaction = session.beginTransaction();  
            //Default RatioBox
            comboBox_pass.setSelectedIndex(0);
            comboBox_pass.repaint();
            List<Transcript>transcripts;
            
            Query query = session.createQuery("from CurrentCourse where scheduleID = :id");
            String scheduleID=choose_classes.get(0)+"-"+comboBox_year.getSelectedItem()+"-"+comboBox_term.getSelectedItem();
            query.setParameter("id", scheduleID);
            List<CurrentCourse> schedules= new ArrayList<>(query.list());
           	choose_course.clear();
	        if(!schedules.isEmpty()) {
	        	for(CurrentCourse x: schedules) {
	           		choose_course.add(x.getCurrentCourseID());
	           	}
	           	comboBox_course.setSelectedItem(choose_course.get(0));
	            	comboBox_course.repaint();
            }else {
            	choose_course.add(" ");
            	comboBox_course.setSelectedItem(choose_course.get(0));
           		comboBox_course.repaint();
           		data.clear();
           		ratio_pass.setText("Đậu: " + "0%" );
                ratio_fail.setText("Rớt: " + "0%" );
                
                ratio_pass.repaint();
                ratio_fail.repaint();
           		return;
           	}
            	
            CurrentCourse cur= schedules.get(0);
           	query = session.createQuery("from Transcript where currentCourse= :id");
           	query.setParameter("id", cur.getCurrentCourseID());
           	transcripts = new ArrayList<>(query.list());
           	data.clear();
           	int passRatio=0;
           	if(!transcripts.isEmpty()) {
	           	for(Integer i=0;i< transcripts.size();i++) {
	           		Transcript x = transcripts.get(i);
	           		Vector<String> t2 =new Vector<>();
	           		passRatio+=checkPass(x);
	           		t2.add(i.toString());
	           		t2.add(x.getStudent().getStudentID());
	           		t2.add(x.getStudent().getFullname());
	           		t2.add(Float.toString(x.getMidtermMark()));
	           		t2.add(Float.toString(x.getFinaltermMark()));
	           		t2.add(Float.toString(x.getOtherMark()));
	           		t2.add(Float.toString(x.getTotalMark()));
	           		data.add(t2);
	           	}
           	}   
           	int pass=Math.round((float)passRatio/transcripts.size()*100);         	
            ratio_pass.setText("Đậu: " + pass +"%" );
            ratio_fail.setText("Rớt: " + (100-pass) +"%" );
        	// commit transaction
            transaction.commit();
        }catch (Exception e) {
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
            //load default ratio
            comboBox_pass.setSelectedIndex(0);
            comboBox_pass.repaint();
            List<Transcript>transcripts;
            Query query = session.createQuery("from Transcript where currentCourse= :id");
            query.setParameter("id", comboBox_course.getSelectedItem());
            transcripts = new ArrayList<>(query.list());
            data.clear();
            int passRatio=0;
            if(!transcripts.isEmpty()) {
	           	for(Integer i=0;i< transcripts.size();i++) {
	           		Transcript x = transcripts.get(i);
	           		Vector<String> t2 =new Vector<>();
	           		passRatio+=checkPass(x);
	           		t2.add(i.toString());
	           		t2.add(x.getStudent().getStudentID());
	           		t2.add(x.getStudent().getFullname());
	           		t2.add(Float.toString(x.getMidtermMark()));
	           		t2.add(Float.toString(x.getFinaltermMark()));
	           		t2.add(Float.toString(x.getOtherMark()));
	           		t2.add(Float.toString(x.getTotalMark()));
	           		data.add(t2);
	           	}
           	}else {
           		ratio_pass.setText("Đậu: " + "0%" );
                ratio_fail.setText("Rớt: " + "0%" );
                
                ratio_pass.repaint();
                ratio_fail.repaint();
                return;
           	}
        	// commit transaction
            transaction.commit();
           	int pass=Math.round((float)passRatio/transcripts.size()*100);         	
            ratio_pass.setText("Đậu: " + pass +"%" );
            ratio_fail.setText("Rớt: " + (100-pass) +"%" );
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private Vector<String> createRow(Transcript x,Integer index) {
		Vector<String> res =new Vector<>();
		res.add(index.toString());
		res.add(x.getStudent().getStudentID());
		res.add(x.getStudent().getFullname());
		res.add(Float.toString(x.getMidtermMark()));
		res.add(Float.toString(x.getFinaltermMark()));
		res.add(Float.toString(x.getOtherMark()));
		res.add(Float.toString(x.getTotalMark()));
		return res;
	}
	@SuppressWarnings("unchecked")
	private void loadRatio() {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	// start a transaction
            transaction = session.beginTransaction();  
            List<Transcript>transcripts;
            	Query query = session.createQuery("from Transcript where currentCourse= :id");
            	query.setParameter("id", comboBox_course.getSelectedItem());
            	transcripts = new ArrayList<>(query.list());
            	data.clear();
            	if(!transcripts.isEmpty()) {
	            	for(Integer i=0;i< transcripts.size();i++) {
	            		Transcript x = transcripts.get(i);
	            		Vector<String> t2 =null;
	            		//0: All, 1:Pass, 2:Fail
	            		if(comboBox_pass.getSelectedIndex()==0) {
	            			t2=createRow(x,i);
	            		}else {
	            			if(comboBox_pass.getSelectedIndex()==1) {
	            				if(x.getTotalMark()>=5.0f) {
	            					t2=createRow(x,i);
	            				}else {
	            					continue;
	            				}
	            			}else {
	            				if(x.getTotalMark()<5.0f) {
	            					t2=createRow(x,i);
	            				}else {
	            					continue;
	            				}
	            			}
	            		}
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
	private void filterListener() {
		comboBox_year.addActionListener (new FilterListener());
		comboBox_term.addActionListener(new FilterListener());
		comboBox_course.addActionListener(new FilterListener2());
		comboBox_pass.addActionListener(new FilterListener3());
	}
	class FilterListener implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			table.repaint();			
			loadCourse();
		}
	}
	class FilterListener2 implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			table.repaint();
			loadData();
		}

	}
	class FilterListener3 implements ActionListener{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			table.repaint();
			loadRatio();
		}
	}
}
