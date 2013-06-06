package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * CustomerPriceList generated by hbm2java
 */
public class CustomerPriceList implements java.io.Serializable {

	private long customerPriceList;
	private PriceList priceList;
	private Customer customer;

	public CustomerPriceList() {
	}

	public CustomerPriceList(long customerPriceList) {
		this.customerPriceList = customerPriceList;
	}

	public CustomerPriceList(long customerPriceList, PriceList priceList,
			Customer customer) {
		this.customerPriceList = customerPriceList;
		this.priceList = priceList;
		this.customer = customer;
	}

	public long getCustomerPriceList() {
		return this.customerPriceList;
	}

	public void setCustomerPriceList(long customerPriceList) {
		this.customerPriceList = customerPriceList;
	}

	public PriceList getPriceList() {
		return this.priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}