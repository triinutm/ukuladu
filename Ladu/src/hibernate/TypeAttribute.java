package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

/**
 * TypeAttribute generated by hbm2java
 */
public class TypeAttribute implements java.io.Serializable {

	private long typeAttribute;
	private ItemType itemType;
	private ItemAttributeType itemAttributeType;
	private Long orderby;
	private String required;
	private String createdByDefault;

	public TypeAttribute() {
	}

	public TypeAttribute(long typeAttribute) {
		this.typeAttribute = typeAttribute;
	}

	public TypeAttribute(long typeAttribute, ItemType itemType,
			ItemAttributeType itemAttributeType, Long orderby, String required,
			String createdByDefault) {
		this.typeAttribute = typeAttribute;
		this.itemType = itemType;
		this.itemAttributeType = itemAttributeType;
		this.orderby = orderby;
		this.required = required;
		this.createdByDefault = createdByDefault;
	}

	public long getTypeAttribute() {
		return this.typeAttribute;
	}

	public void setTypeAttribute(long typeAttribute) {
		this.typeAttribute = typeAttribute;
	}

	public ItemType getItemType() {
		return this.itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemAttributeType getItemAttributeType() {
		return this.itemAttributeType;
	}

	public void setItemAttributeType(ItemAttributeType itemAttributeType) {
		this.itemAttributeType = itemAttributeType;
	}

	public Long getOrderby() {
		return this.orderby;
	}

	public void setOrderby(Long orderby) {
		this.orderby = orderby;
	}

	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getCreatedByDefault() {
		return this.createdByDefault;
	}

	public void setCreatedByDefault(String createdByDefault) {
		this.createdByDefault = createdByDefault;
	}

}
