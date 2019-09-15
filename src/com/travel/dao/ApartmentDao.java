package com.travel.dao;

import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import static com.travel.util.common.Constants.APARTMENTTABLE;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.ManagerDynaSqlProvider;
import com.travel.domain.Admin;
import com.travel.domain.Apartment;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public interface ApartmentDao {
       
	//≤È—Ø
	@Select("select * from "+APARTMENTTABLE+"  where id = #{id}")
	Apartment get_apartment(@Param("id") String id);
	
	@Select("select * from "+APARTMENTTABLE+" ")
	List<Apartment> get_apartment_list();
	
	@Select("select * from "+APARTMENTTABLE +"  where manager_id = #{manager_id}")
	Apartment get_apartment_manager(@Param("manager_id") String manager_id);
	
}
