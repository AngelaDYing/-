package com.travel.dao.provider;
import static com.travel.util.common.Constants.NOTICETABLE;
import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import static com.travel.util.common.Constants.FINANCETABLE;
import org.apache.ibatis.jdbc.SQL;

import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public class FinanceDynaSqlProvider {

	public String insert_finance(Finance finance){
		
		return new SQL(){
			{
				INSERT_INTO(FINANCETABLE);
				if(finance.getId()!=null) {
					VALUES("id", "#{id}");
				}
				if(finance.getName() != null ){
					VALUES("name", "#{name}");
				}
				if(finance.getPassword() != null ){
					VALUES("password", "#{password}");
				}
				if(finance.getSex() != null ){
					VALUES("sex", "#{sex}");
				}
				if(finance.getBirthday()!=null){
					VALUES("birthday","#{birthday}");
				}
				if(finance.getPhone()!= null ){
					VALUES("phone","#{phone}");
				}
				if(finance.getAddress()!= null ){
					VALUES("address","#{address}");
				}
				
			}
		}.toString();
	}	


	public String update_finance(Finance finance){
		
		return new SQL(){
			{
				UPDATE(FINANCETABLE);
				if(finance.getName() != null ){
					SET("name = #{name}");
				}
				if(finance.getPassword() != null ){
					SET("password = #{password}");
				}
				if(finance.getSex() != null ){
					SET("sex = #{sex}");
				}
				if(finance.getBirthday()!=null){
					SET("birthday = #{birthday}");
				}
				if(finance.getPhone()!= null ){
					SET("phone = #{phone}");
				}
				if(finance.getAddress()!= null ){
					SET("address = #{address}");
				}
			WHERE(" id = #{id} ");
				
			}
		}.toString();
	}
}
