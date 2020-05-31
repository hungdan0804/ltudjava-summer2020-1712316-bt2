package Object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="checkexaminationpaper",schema="project_demo")
public class CheckExaminationPaper implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "integer auto_increment") 
	protected int cepID;
	protected String title;
	protected Date startingDate;
	protected Date endingDate;
	
	@OneToMany(targetEntity=CheckExaminationPaperInfo.class, mappedBy="cep", fetch=FetchType.EAGER)
	protected List<CheckExaminationPaperInfo> cepInfos=new ArrayList<>();
	
	
	
	
	
	
	public CheckExaminationPaper(int cepID, String title, Date startingDate, Date endingDate,
			List<CheckExaminationPaperInfo> cepInfos) {
		super();
		this.cepID = cepID;
		this.title = title;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.cepInfos = cepInfos;
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
