package com.travel.domain;
import java.io.Serializable;
import java.util.Date;
public class Vote implements Serializable{
    String name;//ͶƱѡ������
    int vote_start_id;
    int sum;//��ѡ��ͶƱ������

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVote_start_id() {
		return vote_start_id;
	}
	public void setVote_start_id(int vote_start_id) {
		this.vote_start_id = vote_start_id;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
    
}
