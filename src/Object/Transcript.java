package Object;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transcript",schema="project1712316")
public class Transcript implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	protected String transcriptID;
	protected String currentCourse;
	
	@ManyToOne
	@JoinColumn (name="studentID")
	protected Student student;
	
	protected float midtermMark;
	protected float finaltermMark;
	protected float otherMark;
	protected float totalMark;
	
	public Transcript() {
		
	}
	
	

	public Transcript(String transcriptID, String currentCourse, Student student, float midtermMark, float finaltermMark,
			float otherMark, float totalMark) {
		super();
		this.transcriptID = transcriptID;
		this.currentCourse = currentCourse;
		this.student = student;
		this.midtermMark = midtermMark;
		this.finaltermMark = finaltermMark;
		this.otherMark = otherMark;
		this.totalMark = totalMark;
	}



	public String getTranscriptID() {
		return transcriptID;
	}

	public void setTranscriptID(String transcriptID) {
		this.transcriptID = transcriptID;
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

	public float getMidtermMark() {
		return midtermMark;
	}

	public void setMidtermMark(float midtermMark) {
		this.midtermMark = midtermMark;
	}

	public float getFinaltermMark() {
		return finaltermMark;
	}

	public void setFinaltermMark(float finaltermMark) {
		this.finaltermMark = finaltermMark;
	}

	public float getOtherMark() {
		return otherMark;
	}

	public void setOtherMark(float otherMark) {
		this.otherMark = otherMark;
	}

	public float getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(float totalMark) {
		this.totalMark = totalMark;
	}
	
	
}
