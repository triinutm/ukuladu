package hibernate;

// default package
// Generated May 12, 2013 3:55:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * UnitType generated by hbm2java
 */
public class UnitType implements java.io.Serializable {

	private long unitType;
	private String typeName;
	private String longName;

	public UnitType() {
	}

	public UnitType(long unitType) {
		this.unitType = unitType;
	}

	public UnitType(long unitType, String typeName, String longName) {
		this.unitType = unitType;
		this.typeName = typeName;
		this.longName = longName;
	}

	public long getUnitType() {
		return this.unitType;
	}

	public void setUnitType(long unitType) {
		this.unitType = unitType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLongName() {
		return this.longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

}