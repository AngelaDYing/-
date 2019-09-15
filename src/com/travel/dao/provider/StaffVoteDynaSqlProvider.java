package com.travel.dao.provider;

import static com.travel.util.common.Constants.NOTICETABLE;
import static com.travel.util.common.Constants.STAFFVOTETABLE;
import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import org.apache.ibatis.jdbc.SQL;

import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.StaffVote;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public class StaffVoteDynaSqlProvider {
      
	public String new_staff_vote(StaffVote staffvote){
		
		return new SQL(){
			{
				INSERT_INTO(STAFFVOTETABLE);
				if(staffvote.getStaff_id() != null ){
					VALUES("staff_id", "#{staff_id}");
				}
				if(Integer.toString(staffvote.getVote_start_id())!=null){
					VALUES("vote_start_id","#{vote_start_id}");
				}
			}
		}.toString();
	}	
}
