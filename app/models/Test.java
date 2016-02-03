package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST", catalog = "tester")
public class Test extends GenericModel {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name="pk_sequence", sequenceName="test_id_seq", allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_sequence")
	private long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "DESCRIPTION")	
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")	
	private Set<TestResult> testResults = new HashSet<TestResult>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")	
	private Set<TestAttr> testAttrs = new HashSet<TestAttr>(0);

	public Test() {
	}

	public Test(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Test(long id, String name, String description, Set<TestResult> testResults, Set<TestAttr> testAttrs) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.testResults = testResults;
		this.testAttrs = testAttrs;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<TestResult> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(Set<TestResult> testResults) {
		this.testResults = testResults;
	}

	public Set<TestAttr> getTestAttrs() {
		return this.testAttrs;
	}

	public void setTestAttrs(Set<TestAttr> testAttrs) {
		this.testAttrs = testAttrs;
	}

}
