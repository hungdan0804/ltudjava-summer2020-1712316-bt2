package Object;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="checkexaminationpaperinfo",schema="project_demo")
public class CheckExaminationPaperInfo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "integer auto_increment") 
	protected int cepInfoID;
	@ManyToOne
	@JoinColumn (name="studentID")
	protected Student student;
	@ManyToOne
	@JoinColumn(name = "currentCourse")
	protected CurrentCourse currentCourse;
	protected int cep;
	protected String columnUpdate;
	protected float wantedMark;
	protected String reason;
	
	public CheckExaminationPaperInfo() {
		super();
	}
	public CheckExaminationPaperInfo(int cepInfoID, Student student, CurrentCourse currentCourse, int cep,
			String columnUpdate, float wantedMark, String reason) {
		super();
		this.cepInfoID = cepInfoID;
		this.student = student;
		this.currentCourse = currentCourse;
		this.cep = cep;
		this.columnUpdate = columnUpdate;
		this.wantedMark = wantedMark;
		this.reason = reason;
	}
	public int getCepInfoID() {
		return cepInfoID;
	}
	public void setCepInfoID(int cepInfoID) {
		this.cepInfoID = cepInfoID;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public CurrentCourse getCurrentCourse() {
		return currentCourse;
	}
	public void setCurrentCourse(CurrentCourse currentCourse) {
		this.currentCourse = currentCourse;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public String getColumnUpdate() {
		return columnUpdate;
	}
	public void setColumnUpdate(String columnUpdate) {
		this.columnUpdate = columnUpdate;
	}
	public float getWantedMark() {
		return wantedMark;
	}
	public void setWantedMark(float wantedMark) {
		this.wantedMark = wantedMark;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
