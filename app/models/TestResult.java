package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST_RESULT", catalog = "tester")
public class TestResult extends GenericModel {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name="pk_sequence", sequenceName="test_result_id_seq", allocationSize=1, initialValue = 10)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)	
	private Account account;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID", nullable = false)	
	private Test test;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESULT_DATE", nullable = false, length = 23)	
	private Date resultDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "testResult")	
	private Set<TestResultAttr> testResultAttrs = new HashSet<TestResultAttr>(0);

	public TestResult() {
	}

	public TestResult(long id, Account account, Test test, Date resultDate) {
		this.id = id;
		this.account = account;
		this.test = test;
		this.resultDate = resultDate;
	}

	public TestResult(long id, Account account, Test test, Date resultDate, Set<TestResultAttr> testResultAttrs) {
		this.id = id;
		this.account = account;
		this.test = test;
		this.resultDate = resultDate;
		this.testResultAttrs = testResultAttrs;
	}
	
	public TestResult(Account account, Test test, Date resultDate) {
		this.account = account;
		this.test = test;
		this.resultDate = resultDate;		
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Date getResultDate() {
		return this.resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public Set<TestResultAttr> getTestResultAttrs() {
		return this.testResultAttrs;
	}

	public void setTestResultAttrs(Set<TestResultAttr> testResultAttrs) {
		this.testResultAttrs = testResultAttrs;
	}

}
