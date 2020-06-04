package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.toedter.calendar.JDateChooser;

import MyListener.MyListener;
import Object.CheckExaminationPaper;
import Object.Student;
import Util.HibernateUtil;
import Util.RoundedButton;
import Util.RoundedLabel;

import java.awt.GridBagLayout;


public class UI_CEP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content;
	private Student curStudent;
	private JLabel Dashboard;
	private JLabel sign_out;
    private JLabel schedule;
    private JLabel profile;
    private JLabel transcripts;
    private JLabel list_cep;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<Integer> data_id = new ArrayList<>();
    private Vector<Integer> data_term = new Vector<>();
    private GridBagLayout gbl = new GridBagLayout();
    private JScrollPane scrollbar;
    private JPanel panel = new JPanel();
    private JLabel [] textPane;
    private JButton btn_create;
    private Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
    
    
	public UI_CEP(Student student) {
		this.curStudent=student;
		initializeData();
		setResizable(false);
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
		
		JLabel cep = new JLabel("Ph\u00FAc kh\u1EA3o \u0111i\u1EC3m");
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
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN PHÚC KHẢO");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(content.getWidth()/20, content.getHeight()/18, content.getWidth(), 20);
		content.add(lblNewLabel);
		panel.setBackground(Color.WHITE);
		
		scrollbar = new JScrollPane(panel);
		gbl.columnWidths = new int[]{0, 0, 0, 0};
	    gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gbl.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
	    gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panel.setLayout(gbl);
	    scrollbar.setBounds(content.getWidth()/20, content.getHeight()/5, content.getWidth()*9/10, content.getHeight()*3/4);
	    scrollbar.setBorder(null);
	    content.add(scrollbar);
	    if(curStudent.getRole()==0) {
	    	btn_create = new RoundedButton();
	    	btn_create.setText("TẠO ĐỢT PHÚC KHẢO");
	    	btn_create.setForeground(Color.WHITE);
	    	btn_create.setBackground(new Color(22,72,159));
	    	btn_create.setFont(new Font("Arial", Font.BOLD, 12));
	    	btn_create.setBounds(content.getWidth()/20+content.getWidth()*9/10-200,content.getHeight()/7, 200, 23);
			content.add(btn_create);	
		}
	    
	    
		clickListener();
		listLabel();
	}
	
	private void initializeData() {
		
		data_term.add(1);
		data_term.add(2);
		data_term.add(3);
		
		
	}
	
	@SuppressWarnings("rawtypes")
	private JLabel[] generateTextPane(int arraySize, ArrayList arrayList) {
	        JLabel [] textPane = new JLabel[arraySize];
	        for(int i=0;i<textPane.length;i++) {
	            textPane[i]=new RoundedLabel();
	            textPane[i].setText((String) arrayList.get(i));
	            textPane[i].setFont(new Font("Arial", Font.BOLD, 14));
	            textPane[i].setBorder(new EmptyBorder(0,10,0,0));
	            textPane[i].addMouseListener(new MouseAdapter() {
	            	public void mouseClicked(MouseEvent e) {
	            		if(curStudent.getRole()==1) {
	            			String title = ((JLabel)e.getSource()).getText();
	            			UI_CEP_FORM ui = new UI_CEP_FORM(curStudent,title);
		            		ui.setVisible(true);
		            		dispose();
	            		}
	            	}
	            });
	        }
	    return textPane;
	}
	
	private void clickListener(){
		Dashboard.addMouseListener(new MyListener(curStudent,this));
		sign_out.addMouseListener(new MyListener(curStudent,this));
		schedule.addMouseListener(new MyListener(curStudent,this));
		transcripts.addMouseListener(new MyListener(curStudent,this));
		profile.addMouseListener(new MyListener(curStudent,this));
		list_cep.addMouseListener(new MyListener(curStudent,this));
		if(curStudent.getRole()==0) {
			btn_create.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String title = null;
					Date end =null;
					Date start = null;		
					JDateChooser jd = new JDateChooser();
					String message ="Chọn ngày bắt đầu: \n";
					Object[] params = {message,jd};
					
					Vector<String> curYear= getCurrentYear();
					JComboBox<String> jcb = new JComboBox<String>(curYear);			
					JOptionPane.showMessageDialog( null, jcb, "Chọn năm học", JOptionPane.QUESTION_MESSAGE);
					JComboBox<Integer> jcb2 = new JComboBox<Integer>(data_term);			
					JOptionPane.showMessageDialog( null, jcb2, "Chọn học kỳ", JOptionPane.QUESTION_MESSAGE);
					
					JOptionPane.showConfirmDialog(null,params,"Ngày bắt đầu", JOptionPane.PLAIN_MESSAGE);
					start=((JDateChooser)params[1]).getDate();
					if(start == null) {
						return;
					}
					jd.setDate(new Date(start.getTime() + 1 * 24 * 60 * 60 * 1000));
					jd.setMinSelectableDate(new Date(start.getTime() + 1 * 24 * 60 * 60 * 1000));
					
					message = "Chọn ngày kết thúc: \n";
					params[0]=message;
					JOptionPane.showConfirmDialog(null,params,"Ngày kết thúc", JOptionPane.PLAIN_MESSAGE); 
					end=((JDateChooser)params[1]).getDate();
					
					JTextArea textArea = new JTextArea();
					textArea.setColumns(10);
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					textArea.setSize(textArea.getPreferredSize().width, 1);
					JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Tựa đề",
					        JOptionPane.PLAIN_MESSAGE);
					title = textArea.getText();
					if(start!=null && end!=null && !title.isEmpty()) {
						int choice = JOptionPane.showConfirmDialog(contentPane,"Ngày bắt đầu: "+start.toString()+"\nNgày kết thúc: "+end.toString()+"\nTựa đề: "+title+"\nNăm học: "+jcb.getSelectedItem()+"\nHọc kì: "+jcb2.getSelectedItem()+"\nBấm yes để tạo !!!", "Tạo phúc khảo",
					            JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION){
							CheckExaminationPaper c = new CheckExaminationPaper(title,start,end,jcb.getSelectedItem().toString(),jcb2.getSelectedItem().toString());
							Transaction transaction = null;
							try (Session session = HibernateUtil.getSessionFactory().openSession()) {
								// start a transaction
					            transaction = session.beginTransaction();
					            session.save(c);
					            //import student for every current course 
								transaction.commit();
								listLabel();
							}catch (Exception e2) {
					            e2.printStackTrace();
					        }
					    }
					}else {
						JOptionPane.showMessageDialog(contentPane,"Khởi tạo không thành công !!!");
					}
				}
				
			});
		}
	}
	
	private String getLastName(String fullname) {
		String []str = fullname.split(" ");
		return str[str.length-1];
	}
	private Vector<String> getCurrentYear() {
		Vector<String> res = new Vector<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		res.add(year-1+"-"+year);
		res.add(year+"-"+(year+1));
		return res;
	}
	
	@SuppressWarnings("unchecked")
	private void listLabel() {
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
            transaction = session.beginTransaction();
            Calendar cal = Calendar.getInstance();
            Date now = cal.getTime();
            Query query = session.createQuery("from CheckExaminationPaper where startingDate <= :now1 and endingDate >= :now2");
            query.setParameter("now1",now);
            query.setParameter("now2",now);
            List<CheckExaminationPaper> list= new ArrayList<>(query.list());
            if(!list.isEmpty()) {
            	data.clear();
            	for(CheckExaminationPaper cur:list) {
            		data.add(cur.getTitle());
            		data_id.add(cur.getCepID());
            	}
            }else {
            	return;
            }
            //import student for every current course 
			transaction.commit();
		}catch (Exception e2) {
            e2.printStackTrace();
        }
        
        textPane = generateTextPane(data.size(), data);
      
        for(int i=0;i<textPane.length;i++) {
            panel.add(textPane[i],new GridBagConstraints( 0,i,0,1, 
            		1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 20, 0), 0, 15));
            
            panel.revalidate();
        }
        scrollbar.repaint();
	}
}
