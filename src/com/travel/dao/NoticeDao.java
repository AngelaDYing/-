package com.travel.dao;

import static com.travel.util.common.Constants.MANAGERTABLE;
import static com.travel.util.common.Constants.VOTESTARTTABLE;
import static com.travel.util.common.Constants.VOTETABLE;
import static com.travel.util.common.Constants.NOTICETABLE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.ManagerDynaSqlProvider;
import com.travel.dao.provider.NoticeDynaSqlProvider;
import com.travel.domain.Admin;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;
public interface NoticeDao {
      //≤È—Ø
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="new_notice")
	void new_notice(Notice notice);
	
	@Select("select * from "+NOTICETABLE+"  where id = #{id} AND date = #{date}")
	Notice get_notice(@Param("id") String id,@Param("date") Timestamp date);
	
	@Delete("delete from "+NOTICETABLE+" where id = #{id} AND date = #{date}")
	void delete_notice(@Param("id")String id,@Param("date")Timestamp date);
	
	@Select("select * from "+NOTICETABLE+"  where id = #{id}")
	List<Notice> get_notice_list(@Param("id") String manager_id);
}
