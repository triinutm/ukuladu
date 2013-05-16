package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Customer generated by hbm2java
 */
public class Customer implements java.io.Serializable {

	private long customer;
	private Long subjectFk;
	private Long subjectTypeFk;
	private Set customerPriceLists = new HashSet(0);

	public Customer() {
	}

	public Customer(long customer) {
		this.customer = customer;
	}

	public Customer(long customer, Long subjectFk, Long subjectTypeFk,
			Set customerPriceLists) {
		this.customer = customer;
		this.subjectFk = subjectFk;
		this.subjectTypeFk = subjectTypeFk;
		this.customerPriceLists = customerPriceLists;
	}

	public long getCustomer() {
		return this.customer;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public Long getSubjectFk() {
		return this.subjectFk;
	}

	public void setSubjectFk(Long subjectFk) {
		this.subjectFk = subjectFk;
	}

	public Long getSubjectTypeFk() {
		return this.subjectTypeFk;
	}

	public void setSubjectTypeFk(Long subjectTypeFk) {
		this.subjectTypeFk = subjectTypeFk;
	}

	public Set getCustomerPriceLists() {
		return this.customerPriceLists;
	}

	public void setCustomerPriceLists(Set customerPriceLists) {
		this.customerPriceLists = customerPriceLists;
	}

}
