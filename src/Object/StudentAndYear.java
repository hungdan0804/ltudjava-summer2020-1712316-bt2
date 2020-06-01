package Object;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="studentandyear",schema="project_demo")
public class StudentAndYear implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	protected String studentID;
	@Id
	protected String year;
	public StudentAndYear(String studentID, String year) {
		super();
		this.studentID = studentID;
		this.year = year;
	}
	public StudentAndYear() {
		
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
