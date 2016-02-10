package models;


import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST_ATTR", catalog = "tester")
public class TestAttr extends GenericModel {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name="pk_sequence", sequenceName="test_attr_id_seq", allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID", nullable = false)
	private Test test;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "VAL", nullable = false, precision = 18, scale = 0)
	private long val;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "testAttr")
	private Set<TestResultAttr> testResultAttrs = new HashSet<TestResultAttr>(0);
	@Column(name = "SORT_ORDER")
	private Integer sortOrder;

	public TestAttr() {
	}

	public TestAttr(long id, Test test, String name, long val) {
		this.id = id;
		this.test = test;
		this.name = name;
		this.val = val;
	}

	public TestAttr(long id, Test test, String name, long val, Set<TestResultAttr> testResultAttrs) {
		this.id = id;
		this.test = test;
		this.name = name;
		this.val = val;
		this.testResultAttrs = testResultAttrs;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getVal() {
		return this.val;
	}

	public void setVal(long val) {
		this.val = val;
	}

	public Set<TestResultAttr> getTestResultAttrs() {
		return this.testResultAttrs;
	}

	public void setTestResultAttrs(Set<TestResultAttr> testResultAttrs) {
		this.testResultAttrs = testResultAttrs;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
}
