package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * PriceListStatusType generated by hbm2java
 */
public class PriceListStatusType implements java.io.Serializable {

	private long priceListStatusType;
	private String typeName;
	private Set priceLists = new HashSet(0);

	public PriceListStatusType() {
	}

	public PriceListStatusType(long priceListStatusType) {
		this.priceListStatusType = priceListStatusType;
	}

	public PriceListStatusType(long priceListStatusType, String typeName,
			Set priceLists) {
		this.priceListStatusType = priceListStatusType;
		this.typeName = typeName;
		this.priceLists = priceLists;
	}

	public long getPriceListStatusType() {
		return this.priceListStatusType;
	}

	public void setPriceListStatusType(long priceListStatusType) {
		this.priceListStatusType = priceListStatusType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getPriceLists() {
		return this.priceLists;
	}

	public void setPriceLists(Set priceLists) {
		this.priceLists = priceLists;
	}

}
