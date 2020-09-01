package org.film.house.cinema.bean;

import java.sql.Timestamp;



public abstract class BaseBean2 implements DropdownListBean2, Comparable<BaseBean2>{
	
	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDatetime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}
	
	public int compareTo(BaseBean2 next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}
	
}
