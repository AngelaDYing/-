package com.travel.domain;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Notice implements Serializable{
   String id;
   String item;
   Timestamp date;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}

}
