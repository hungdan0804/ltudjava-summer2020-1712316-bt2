package Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="classes",schema="project1712316")
public class Classes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	protected String classID;
	protected String schoolyear;
	@OneToMany(targetEntity=Student.class, mappedBy="classes", fetch=FetchType.EAGER)
	protected List<Student> students = new ArrayList<>();
	
	
	
	public Classes() {
		
	}
	public Classes(String _classID) {
		super();
		this.classID = _classID;
	}
	
	public Classes(String _classID,String year) {
		super();
		this.classID = _classID;
		this.schoolyear=year;
	}

	
	
	

	public String getSchoolyear() {
		return schoolyear;
	}


	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}


	public String getClassID() {
		return classID;
	}


	public void setClassID(String classID) {
		this.classID = classID;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	
	
}
