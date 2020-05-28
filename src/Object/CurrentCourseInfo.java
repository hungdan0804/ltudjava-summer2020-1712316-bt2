package Object;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="currentcourseinfo",schema="project_demo")
public class CurrentCourseInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int currentCourseInfoID;
	protected String currentCourse;
	
	@ManyToOne
	@JoinColumn (name="studentID")
	protected Student student;

	public CurrentCourseInfo(int currentCourseInfoID, String currentCourse, Student student) {
		super();
		this.currentCourseInfoID = currentCourseInfoID;
		this.currentCourse = currentCourse;
		this.student = student;
	}
	public CurrentCourseInfo() {
	
	}

	public int getCurrentCourseInfoID() {
		return currentCourseInfoID;
	}
	public void setCurrentCourseInfoID(int currentCourseInfoID) {
		this.currentCourseInfoID = currentCourseInfoID;
	}
	public String getCurrentCourse() {
		return currentCourse;
	}
	public void setCurrentCourse(String currentCourse) {
		this.currentCourse = currentCourse;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
