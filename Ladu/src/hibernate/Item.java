package hibernate;

// Generated May 16, 2013 2:51:38 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Item generated by hbm2java
 */
public class Item implements java.io.Serializable {

	private long item;
	private Item item_1;
	private ItemType itemType;
	private UnitType unitType;
	private Enterprise enterprise;
	private String name;
	private BigDecimal storePrice;
	private BigDecimal salePrice;
	private String producer;
	private String description;
	private String producerCode;
	private String singleItem;
	private String serialNo;
	private Date created;
	private Set itemPriceLists = new HashSet(0);
	private Set itemAttributes = new HashSet(0);
	private Set items = new HashSet(0);
	private Set itemStores = new HashSet(0);

	public Item() {
	}

	public Item(long item) {
		this.item = item;
	}

	public Item(long item, Item item_1, ItemType itemType, UnitType unitType,
			Enterprise enterprise, String name, BigDecimal storePrice,
			BigDecimal salePrice, String producer, String description,
			String producerCode, String singleItem, String serialNo,
			Date created, Set itemPriceLists, Set itemAttributes, Set items,
			Set itemStores) {
		this.item = item;
		this.item_1 = item_1;
		this.itemType = itemType;
		this.unitType = unitType;
		this.enterprise = enterprise;
		this.name = name;
		this.storePrice = storePrice;
		this.salePrice = salePrice;
		this.producer = producer;
		this.description = description;
		this.producerCode = producerCode;
		this.singleItem = singleItem;
		this.serialNo = serialNo;
		this.created = created;
		this.itemPriceLists = itemPriceLists;
		this.itemAttributes = itemAttributes;
		this.items = items;
		this.itemStores = itemStores;
	}

	public long getItem() {
		return this.item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	public Item getItem_1() {
		return this.item_1;
	}

	public void setItem_1(Item item_1) {
		this.item_1 = item_1;
	}

	public ItemType getItemType() {
		return this.itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public UnitType getUnitType() {
		return this.unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStorePrice() {
		return this.storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProducerCode() {
		return this.producerCode;
	}

	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}

	public String getSingleItem() {
		return this.singleItem;
	}

	public void setSingleItem(String singleItem) {
		this.singleItem = singleItem;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Set getItemPriceLists() {
		return this.itemPriceLists;
	}

	public void setItemPriceLists(Set itemPriceLists) {
		this.itemPriceLists = itemPriceLists;
	}

	public Set getItemAttributes() {
		return this.itemAttributes;
	}

	public void setItemAttributes(Set itemAttributes) {
		this.itemAttributes = itemAttributes;
	}

	public Set getItems() {
		return this.items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

	public Set getItemStores() {
		return this.itemStores;
	}

	public void setItemStores(Set itemStores) {
		this.itemStores = itemStores;
	}

}
