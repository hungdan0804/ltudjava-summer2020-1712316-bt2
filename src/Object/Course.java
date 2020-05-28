package Object;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course",schema="project_demo")
public class Course implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "courseID")
	protected String courseID;
	
	@Column(name = "courseName")
	protected String courseName;
	

	public Course() {
		
	}
	public Course(String courseID,String courseName) {
		this.courseID=courseID;
		this.courseName=courseName;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
}
