package Adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Object.Student;
import main.UI_DashBoard;
import main.UI_Schedule;
import main.UI_SignIn;

public class MyListener extends MouseAdapter{
	
	private Student curStudent;
	private JFrame frame;
	public MyListener(Student student,JFrame frame){
		this.curStudent=student;
		this.frame=frame;
	}
	
	 public void mouseClicked(MouseEvent e)  
	 {  
		 JLabel choose =(JLabel) e.getSource();
		 switch(choose.getText()) {
		 	case "Dashboard" : UI_DashBoard ui3 = new UI_DashBoard(curStudent);ui3.setVisible(true);frame.dispose();break;
		 	case "THỜI KHÓA BIỂU": case"Thời khóa biểu": UI_Schedule ui= new UI_Schedule(curStudent); ui.setVisible(true);frame.dispose();break;
		 	case "Đăng xuất": UI_SignIn ui2= new UI_SignIn();ui2.setVisible(true);frame.dispose();break;
		 };
	 }  
}