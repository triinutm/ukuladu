package hibernate;

// default package
// Generated May 12, 2013 3:55:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * Store generated by hbm2java
 */
public class Store implements java.io.Serializable {

	private long store;
	private String name;

	public Store() {
	}

	public Store(long store) {
		this.store = store;
	}

	public Store(long store, String name) {
		this.store = store;
		this.name = name;
	}

	public long getStore() {
		return this.store;
	}

	public void setStore(long store) {
		this.store = store;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
