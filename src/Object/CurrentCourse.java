package Object;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="currentcourse",schema="project_demo")
public class CurrentCourse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	protected String scheduleID;
	@Id
	protected String currentCourseID;
	protected String location;
	protected String startingTime;
	
	@ManyToOne
	@JoinColumn(name = "course")
	protected Course course;
	
	@ManyToOne
	@JoinColumn(name = "classes")
	protected Classes classes;
	
	public CurrentCourse() {
		
	}
	public CurrentCourse(String _currentCourseID,Course course,Classes classes,String _location,String _startingTime,String scheduleID) {
		super();
		this.currentCourseID = _currentCourseID;
		this.location = _location;
		this.startingTime=_startingTime;
		this.course=course;
		this.classes=classes;
		this.scheduleID=scheduleID;
	}
	
	
	
	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getCurrentCourseID() {
		return currentCourseID;
	}

	public void setCurrentCourseID(String currentCourseID) {
		this.currentCourseID = currentCourseID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}
	
}
