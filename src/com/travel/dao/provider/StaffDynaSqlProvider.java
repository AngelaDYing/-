package com.travel.dao.provider;

import static com.travel.util.common.Constants.NOTICETABLE;
import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import static com.travel.util.common.Constants.STAFFTABLE;
import org.apache.ibatis.jdbc.SQL;


import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Staff;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public class StaffDynaSqlProvider {

	public String update_vote_sum(Vote v){
		
		return new SQL(){
			{
				UPDATE(VOTETABLE);
			    SET("sum = #{sum}");
				WHERE(" name = #{name} AND vote_start_id=#{vote_start_id}");
			}
		}.toString();
	}
	
public String insert_staff(Staff staff){
		
		return new SQL(){
			{
				INSERT_INTO(STAFFTABLE);
				if(staff.getId()!=null) {
					VALUES("id", "#{id}");
				}
				if(staff.getName() != null ){
					VALUES("name", "#{name}");
				}
				if(staff.getPassword() != null ){
					VALUES("password", "#{password}");
				}
				if(staff.getSex() != null ){
					VALUES("sex", "#{sex}");
				}
				if(staff.getBirthday()!=null){
					VALUES("birthday","#{birthday}");
				}
				if(staff.getPhone()!= null ){
					VALUES("phone","#{phone}");
				}
				if(staff.getAddress()!= null ){
					VALUES("address","#{address}");
				}
				if(staff.getApartment_id()!= null ){
					VALUES("apartment_id","#{apartment_id}");
				}
				
			}
		}.toString();
	}	
	// ¶¯Ì¬¸üÐÂ
	public String update_staff(Staff staff){
		
		return new SQL(){
			{
				UPDATE(STAFFTABLE);
				if(staff.getName() != null ){
					SET("name = #{name}");
				}
				if(staff.getPassword() != null ){
					SET("password = #{password}");
				}
				if(staff.getSex() != null ){
					SET("sex =#{sex}");
				}
				if(staff.getBirthday()!=null){
					SET("birthday = #{birthday}");
				}
				if(staff.getPhone()!= null ){
					SET("phone = #{phone}");
				}
				if(staff.getAddress()!= null ){
					SET("address = #{address}");
				}
				if(staff.getApartment_id()!= null ){
					SET("apartment_id = #{apartment_id}");
				}
			WHERE(" id = #{id} ");
				
			}
		}.toString();
	}
	
}
