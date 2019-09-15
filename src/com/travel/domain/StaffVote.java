package com.travel.domain;
import java.io.Serializable;
public class StaffVote implements Serializable{
      String staff_id;
      int vote_start_id;
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public int getVote_start_id() {
		return vote_start_id;
	}
	public void setVote_start_id(int vote_start_id) {
		this.vote_start_id = vote_start_id;
	}
      
}
