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


@Entity
@Table(name="currentcourse",schema="project_demo")
public class CurrentCourse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "currentCourseID")
	protected String currentCourseID;
	protected String location;
	
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
	public CurrentCourse(String _currentCourseID,String _location) {
		super();
		this.currentCourseID = _currentCourseID;
		this.location = _location;
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
}
