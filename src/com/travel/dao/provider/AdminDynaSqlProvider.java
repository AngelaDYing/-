package com.travel.dao.provider;
import static com.travel.util.common.Constants.ADMINTABLE;
import static com.travel.util.common.Constants.NOTICETABLE;
import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import static com.travel.util.common.Constants.FINANCETABLE;
import org.apache.ibatis.jdbc.SQL;

import com.travel.domain.Admin;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;
public class AdminDynaSqlProvider {
	public String insert_admin(Admin admin){
		
		return new SQL(){
			{
				INSERT_INTO(ADMINTABLE);
				if(admin.getId()!=null) {
					VALUES("id", "#{id}");
				}
				if(admin.getName() != null ){
					VALUES("name", "#{name}");
				}
				if(admin.getPassword() != null ){
					VALUES("password", "#{password}");
				}
			}
		}.toString();
	}	
}
