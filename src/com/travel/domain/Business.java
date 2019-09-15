package com.travel.domain;
//travel.domainÊÇpackageµÄÃû³Æ
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

public class Business implements Serializable{
	String id;
	Timestamp date;
	Date start_date;
	int days; 
	String detail;
	float money;
	int status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_time) {
		this.start_date = start_time;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
