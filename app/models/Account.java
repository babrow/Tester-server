package models;

import org.apache.commons.lang.StringUtils;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT", catalog = "tester")
public class Account extends GenericModel {

	@Id	
	@Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name="pk_sequence", sequenceName="account_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
	private long id;
	@Column(name = "EMAIL", nullable = false)	
	private String email;
	@Column(name = "PASSWORD", nullable = false)	
	private String password;
	@Column(name = "F_NAME")
	private String FName;
	@Column(name = "L_NAME")
	private String LName;
	@Column(name = "S_NAME")
	private String SName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")	
	@Transient
	private Set<TestResult> testResults = new HashSet<TestResult>(0);
    @Column(name = "IS_ADMIN")
    private boolean isAdmin;

	public Account() {
	}

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
        this.isAdmin = false;
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

	public String toString() {
		return getEmail();
	}

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
