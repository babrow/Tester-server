package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "ACCOUNT", catalog = "tester")
public class Account extends GenericModel {

	@Id	
	@Column(name = "ID", unique = true, nullable = false)	
	private long id;
	@Column(name = "EMAIL", nullable = false)	
	private String email;
	@Column(name = "PASSWORD", nullable = false)	
	private String password;
	@Column(name = "F_NAME", nullable = false)	
	private String FName;
	@Column(name = "L_NAME", nullable = false)
	private String LName;
	@Column(name = "S_NAME", nullable = false)	
	private String SName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")	
	private Set<TestResult> testResults = new HashSet<TestResult>(0);

	public Account() {
	}

	public Account(long id, String email, String password, String FName, String LName, String SName) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.FName = FName;
		this.LName = LName;
		this.SName = SName;
	}

	public Account(long id, String email, String password, String FName, String LName, String SName,
			Set<TestResult> testResults) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.FName = FName;
		this.LName = LName;
		this.SName = SName;
		this.testResults = testResults;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFName() {
		return this.FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	public String getLName() {
		return this.LName;
	}

	public void setLName(String LName) {
		this.LName = LName;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public Set<TestResult> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(Set<TestResult> testResults) {
		this.testResults = testResults;
	}
	
	public String getFIO() {
		return StringUtils.join(new String[] {getFName(), getLName(), getSName()}, " ");
	}

}
