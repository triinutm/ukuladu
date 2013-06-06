package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Enterprise generated by hbm2java
 */
public class Enterprise implements java.io.Serializable {

	private long enterprise;
	private Clob name;
	private Clob fullName;
	private Long createdBy;
	private Long updatedBy;
	private Date created;
	private Date updated;
	private Set employees = new HashSet(0);
	private Set items = new HashSet(0);

	public Enterprise() {
	}

	public Enterprise(long enterprise) {
		this.enterprise = enterprise;
	}

	public Enterprise(long enterprise, Clob name, Clob fullName,
			Long createdBy, Long updatedBy, Date created, Date updated,
			Set employees, Set items) {
		this.enterprise = enterprise;
		this.name = name;
		this.fullName = fullName;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.created = created;
		this.updated = updated;
		this.employees = employees;
		this.items = items;
	}

	public long getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(long enterprise) {
		this.enterprise = enterprise;
	}

	public Clob getName() {
		return this.name;
	}

	public void setName(Clob name) {
		this.name = name;
	}

	public Clob getFullName() {
		return this.fullName;
	}

	public void setFullName(Clob fullName) {
		this.fullName = fullName;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getItems() {
		return this.items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

}