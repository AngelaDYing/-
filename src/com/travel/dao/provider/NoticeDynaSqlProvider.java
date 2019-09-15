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
public class NoticeDynaSqlProvider {
      
	public String new_notice(Notice notice){
		
		return new SQL(){
			{
				INSERT_INTO(NOTICETABLE);
				if(notice.getId() != null ){
					VALUES("id", "#{id}");
				}
				if(notice.getDate()!=null){
					VALUES("date","#{date}");
				}
				if(notice.getItem()!=null){
					VALUES("item","#{item}");
				}
				
			}
		}.toString();
	}
}
