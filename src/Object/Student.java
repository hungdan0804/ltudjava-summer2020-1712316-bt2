package Object;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student",schema="project_demo")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	protected String studentID;
	protected String fullname;
	protected String gender;
	protected String idCard;
	protected String classes;
	protected int role;
	protected String password;
	protected String address;
	protected String city;
	protected String district;
	protected String year;
	
	public Student() {
		
	}
	public Student (String studentID) {
		this.studentID=studentID;
	}
	
	public Student(String _studentID, String _fullname, String _gender, String _IDCard, String _class, String _password,
			String _address, String _city, String _district,String year) {
		super();
		this.studentID=_studentID;
		this.fullname = _fullname;
		this.gender = _gender;
		this.idCard = _IDCard;
		this.classes = _class;
		this.password = _password;
		this.address = _address;
		this.city = _city;
		this.district = _district;
		this.role=1;
		this.year=year;
	}
	
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
}
