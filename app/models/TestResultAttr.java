package models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;


@Entity
@Table(name = "TEST_RESULT_ATTR", catalog = "tester")
public class TestResultAttr extends GenericModel {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ATTR_ID", nullable = false)
	private TestAttr testAttr;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_RESULT_ID", nullable = false)
	private TestResult testResult;
	@Column(name = "VAL", nullable = false, precision = 18, scale = 3)	
	private BigDecimal val;

	public TestResultAttr() {
	}

	public TestResultAttr(TestAttr testAttr, TestResult testResult, BigDecimal val) {	
		this.testAttr = testAttr;
		this.testResult = testResult;
		this.val = val;
	}	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TestAttr getTestAttr() {
		return this.testAttr;
	}

	public void setTestAttr(TestAttr testAttr) {
		this.testAttr = testAttr;
	}

	public TestResult getTestResult() {
		return this.testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public BigDecimal getVal() {
		return this.val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

}
