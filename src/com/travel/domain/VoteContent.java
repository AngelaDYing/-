package com.travel.domain;
import java.io.Serializable;
import java.util.List;
public class VoteContent implements Serializable{
   Vote_start vote_start;
   List<Vote> votes;
public Vote_start getVote_start() {
	//System.out.println("get vote_start");
	return vote_start;
}
public void setVote_start(Vote_start vote_start) {
	this.vote_start = vote_start;
}
public List<Vote> getVotes() {
	return votes;
}
public void setVotes(List<Vote> votes) {
	this.votes = votes;
}
   
   
}
