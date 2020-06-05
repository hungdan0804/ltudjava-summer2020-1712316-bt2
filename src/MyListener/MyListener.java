package MyListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Object.Student;
import main.UI_CEP;
import main.UI_Classes;
import main.UI_DashBoard;
import main.UI_Profile;
import main.UI_Schedule;
import main.UI_SignIn;
import main.UI_TABLE_CEP_INFO_1;
import main.UI_TABLE_CEP_INFO_STUDENT;
import main.UI_Transcript;
import main.UI_TranscriptStudent;

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
		 	case "BẢNG ĐIỂM": case "Bảng điểm": checkRoleTranscript();break;
		 	case "THÔNG TIN CÁ NHÂN": case "Thông tin cá nhân": UI_Profile ui4 = new UI_Profile(curStudent);ui4.setVisible(true);frame.dispose();break;
		 	case "PHÚC KHẢO ĐIỂM": case "Phúc khảo điểm": UI_CEP ui5= new UI_CEP(curStudent);ui5.setVisible(true);frame.dispose();break;
		 	case "HỒ SƠ CẦN DUYỆT": case "Hồ sơ cần duyệt": checkRoleListCep();break;
		 	case "DANH SÁCH LỚP": case "Danh sách lớp": checkRoleClasses();break;
		 }
	 }

	private void checkRoleClasses() {
		// TODO Auto-generated method stub
		if(curStudent.getRole()==0) {
			UI_Classes ui = new UI_Classes(curStudent); 
			ui.setVisible(true);
			frame.dispose();
		}
	}

	private void checkRoleTranscript() {
		// TODO Auto-generated method stub
		JFrame ui;
		if(curStudent.getRole()==0) {
			 ui=new UI_Transcript(curStudent);
		}else {
			ui=new UI_TranscriptStudent(curStudent);
		}
		ui.setVisible(true);
		frame.dispose();
	}
	private void checkRoleListCep() {
		// TODO Auto-generated method stub
		JFrame ui;
		if(curStudent.getRole()==1) {
			 ui=new UI_TABLE_CEP_INFO_STUDENT(curStudent);
		}else {
			ui=new UI_TABLE_CEP_INFO_1(curStudent);
		}
		ui.setVisible(true);
		frame.dispose();
	}  
}