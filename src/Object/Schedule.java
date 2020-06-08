package Object;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="schedule",schema="project1712316")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	protected String scheduleID;
	protected String year;
	protected String term;
	
	
	@OneToMany(targetEntity=CurrentCourse.class, mappedBy="scheduleID", fetch=FetchType.EAGER)
	protected List<CurrentCourse> currentCourses;
	
	public Schedule(String scheduleID, String year, String term) {
		super();
		this.scheduleID = scheduleID;
		this.year = year;
		this.term = term;
	}
	public Schedule() {
		super();
	}
	
	public String getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}



	public List<CurrentCourse> getCurrentCourses() {
		return currentCourses;
	}



	public void setCurrentCourses(List<CurrentCourse> currentCourses) {
		this.currentCourses = currentCourses;
	}



	public String getYear() {
		return year;
	}

	

	public void setYear(String year) {
		this.year = year;
	}



	public String getTerm() {
		return term;
	}



	public void setTerm(String term) {
		this.term = term;
	}
	
}
