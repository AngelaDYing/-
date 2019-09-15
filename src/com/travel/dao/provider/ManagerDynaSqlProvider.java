package com.travel.dao.provider;

import static com.travel.util.common.Constants.NOTICETABLE;
import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import org.apache.ibatis.jdbc.SQL;


import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public class ManagerDynaSqlProvider {
	public String new_vote_start(Vote_start vote_start){
		
		return new SQL(){
			{
				INSERT_INTO(VOTESTARTTABLE);
				if(vote_start.getManager_id() != null ){
					VALUES("manager_id", "#{manager_id}");
				}
				if(vote_start.getDate()!=null){
					VALUES("date","#{date}");
				}
				if(vote_start.getStatus()==0||vote_start.getStatus()==1){
					VALUES("status","#{status}");
				}
				if(vote_start.getItem()!=null){
					VALUES("item","#{item}");
				}
			}
		}.toString();
	}	
	
public String new_vote(Vote vote){
		
		return new SQL(){
			{
				INSERT_INTO(VOTETABLE);
				if(vote.getName() != null ){
					VALUES("name", "#{name}");
				}
				if(Integer.toString(vote.getVote_start_id())!=null){
					VALUES("vote_start_id","#{vote_start_id}");
				}
				if(vote.getSum()>=0){
					VALUES("sum","#{sum}");
				}
			}
		}.toString();
	}
public String update_vote_start(Vote_start vote_start){
	
	return new SQL(){
		{
			UPDATE(VOTESTARTTABLE);
			if(vote_start.getStatus()>=0 ){
				SET("status = #{status}");
			}
			if(vote_start.getItem()!=null){
				SET("item = #{item}");
			}
			
			
			WHERE(" id = #{id} ");
		}
	}.toString();
}
public String update_vote(Vote vote){
	
	return new SQL(){
		{
			UPDATE(VOTETABLE);
			if(vote.getName()!=null ){
				SET("name = #{name}");
			}
			if(vote.getSum()>=0){
				SET("sum = #{sum}");
			}
			
			
			WHERE(" vote_start_id = #{vote_start_id} ");
		}
	}.toString();
}
public String insert_manager(Manager manager){
	
	return new SQL(){
		{
			INSERT_INTO(MANAGERTABLE);
			if(manager.getId()!=null) {
				VALUES("id", "#{id}");
			}
			if(manager.getName() != null ){
				VALUES("name", "#{name}");
			}
			if(manager.getPassword() != null ){
				VALUES("password", "#{password}");
			}
			if(manager.getSex() != null ){
				VALUES("sex", "#{sex}");
			}
			if(manager.getBirthday()!=null){
				VALUES("birthday","#{birthday}");
			}
			if(manager.getPhone()!= null ){
				VALUES("phone","#{phone}");
			}
			if(manager.getAddress()!= null ){
				VALUES("address","#{address}");
			}
			
		}
	}.toString();
}	

public String update_manager(Manager manager){

return new SQL(){
	{
		UPDATE(MANAGERTABLE);
		if(manager.getName() != null ){
			SET("name = #{name}");
		}
		if(manager.getPassword() != null ){
			SET("password = #{password}");
		}
		if(manager.getSex() != null ){
			SET("sex = #{sex}");
		}
		if(manager.getBirthday()!=null){
			SET("birthday = #{birthday}");
		}
		if(manager.getPhone()!= null ){
			SET("phone = #{phone}");
		}
		if(manager.getAddress()!= null ){
			SET("address = #{address}");
		}
	WHERE(" id = #{id} ");
		
	}
}.toString();
}

}
