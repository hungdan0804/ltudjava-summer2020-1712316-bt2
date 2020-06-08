package Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="checkexaminationpaper",schema="project1712316")
public class CheckExaminationPaper implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int cepID;
	protected String title;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date startingDate;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endingDate;
	protected String year;
	protected String term;
	
	@OneToMany(targetEntity=CheckExaminationPaperInfo.class, mappedBy="cep", fetch=FetchType.EAGER)
	protected List<CheckExaminationPaperInfo> cepInfos=new ArrayList<>();
		
	
	public CheckExaminationPaper( String title, Date startingDate, Date endingDate, String year,
			String term) {
		super();
		this.title = title;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.year = year;
		this.term = term;
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


	public CheckExaminationPaper() {
		super();
	}
	public int getCepID() {
		return cepID;
	}
	public void setCepID(int cepID) {
		this.cepID = cepID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public List<CheckExaminationPaperInfo> getCepInfos() {
		return cepInfos;
	}
	public void setCepInfos(List<CheckExaminationPaperInfo> cepInfos) {
		this.cepInfos = cepInfos;
	}
	
	
	
}
