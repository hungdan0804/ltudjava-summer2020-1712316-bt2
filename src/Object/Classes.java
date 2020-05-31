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
@Table(name="classes",schema="project_demo")
public class Classes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	protected String classID;
	@OneToMany(targetEntity=Student.class, mappedBy="classes", fetch=FetchType.EAGER)
	protected List<Student> students = new ArrayList<>();
	
	
	
	public Classes() {
		
	}
	
	
	public Classes(String _classID) {
		super();
		this.classID = _classID;
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
