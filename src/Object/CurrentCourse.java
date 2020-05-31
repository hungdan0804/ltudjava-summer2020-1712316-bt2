package Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;


@Entity
@Table(name="currentcourse",schema="project_demo")
public class CurrentCourse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "currentCourseID")
	protected String currentCourseID;
	protected String location;
	protected String startingTime;
	@NotNull
	protected String scheduleID;
	
	@ManyToOne
	@JoinColumn(name = "course")
	protected Course course;
	
	@ManyToOne
	@JoinColumn(name = "classes")
	protected Classes classes;
	
	@OneToMany(targetEntity=CurrentCourseInfo.class, mappedBy="currentCourse", fetch=FetchType.EAGER)
	protected List<CurrentCourseInfo> info = new ArrayList<>();
	
	@OneToMany(targetEntity=Transcript.class, mappedBy="currentCourse", fetch=FetchType.EAGER)
	protected List<Transcript> transcipts = new ArrayList<>();
	
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
	public List<Transcript> getTranscipts() {
		return transcipts;
	}
	public void setTranscipts(List<Transcript> transcipts) {
		this.transcipts = transcipts;
	}
	public List<CurrentCourseInfo> getInfo() {
		return info;
	}
	public void setInfo(List<CurrentCourseInfo> info) {
		this.info = info;
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
