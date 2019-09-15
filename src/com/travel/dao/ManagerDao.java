package com.travel.dao;

import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.ManagerDynaSqlProvider;
import com.travel.domain.Admin;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;

public interface ManagerDao {
	//≤È—Ø
	@Select("select * from "+MANAGERTABLE+"  where id = #{id} AND password = #{password}")
	Manager manager_login(@Param("id") String id,@Param("password") String password);
	
	@Select("select * from "+MANAGERTABLE+"  where id = #{id} ")
	Manager get_manager(@Param("id") String id);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="new_vote_start")
	void new_vote_start(Vote_start vote_start);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="new_vote")
	void new_vote(Vote vote);
	
	@Select("select * from "+VOTESTARTTABLE+"  where manager_id = #{manager_id} AND date = #{date}")
	Vote_start get_vote_start(@Param("manager_id") String manager_id,@Param("date") Date date);
	
	@Select("select * from "+VOTESTARTTABLE+"  where manager_id = #{manager_id} ")
	List<Vote_start> get_vote_start_list(@Param("manager_id")String manager_id);
	
	@Select("select * from "+VOTESTARTTABLE+"  where id = #{id}")
	Vote_start get_vote_start_info(@Param("id")int id);
	
	@Select("select * from "+VOTETABLE+"  where vote_start_id = #{vote_start_id}")
	List<Vote> get_votes(@Param("vote_start_id") int vote_start_id);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="update_vote_start")
	void update_vote_start(Vote_start vote_start);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="update_vote")
	void update_vote(Vote vote);
	
	@Delete("delete from "+VOTETABLE+" where vote_start_id = #{vote_start_id} ")
	void delete_vote(@Param("vote_start_id")int vote_start_id);
	
	@Delete("delete from "+VOTESTARTTABLE+" where id = #{id} ")
	void delete_vote_start(@Param("id")int id);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="insert_manager")
	void insert_manager(Manager manager);
	
	@SelectProvider(type=ManagerDynaSqlProvider.class,method="update_manager")
	void update_manager(Manager manager);
	
	@Delete(" delete from "+MANAGERTABLE+" where id = #{id} ")
	void delete_manager(String id);
	
	@Select("select * from "+MANAGERTABLE+" ")
	List<Manager> get_all_manager_list();

}

