package com.travel.domain;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
public class Vote_start implements Serializable {
    int id;
    String manager_id;
    Timestamp date;
    int status;//ͶƱ״̬��0��ʾ�ѽ�����1��ʾδ��������Ͷ
    String item;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

    
}
