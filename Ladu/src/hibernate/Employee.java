package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * Employee generated by hbm2java
 */
public class Employee implements java.io.Serializable {

	private long employee;
	private Person person;
	private Enterprise enterprise;
	private Long structUnitFk;
	private String active;

	public Employee() {
	}

	public Employee(long employee) {
		this.employee = employee;
	}

	public Employee(long employee, Person person, Enterprise enterprise,
			Long structUnitFk, String active) {
		this.employee = employee;
		this.person = person;
		this.enterprise = enterprise;
		this.structUnitFk = structUnitFk;
		this.active = active;
	}

	public long getEmployee() {
		return this.employee;
	}

	public void setEmployee(long employee) {
		this.employee = employee;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Long getStructUnitFk() {
		return this.structUnitFk;
	}

	public void setStructUnitFk(Long structUnitFk) {
		this.structUnitFk = structUnitFk;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
