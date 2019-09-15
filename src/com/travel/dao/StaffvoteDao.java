package com.travel.dao;
import static com.travel.util.common.Constants.STAFFTABLE;
import static com.travel.util.common.Constants.STAFFVOTETABLE;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;


import com.travel.dao.provider.StaffDynaSqlProvider;
import com.travel.dao.provider.StaffVoteDynaSqlProvider;
import com.travel.domain.Staff;
import com.travel.domain.StaffVote;

public interface StaffvoteDao {

	//≤È—Ø
	@Select("select * from "+STAFFVOTETABLE+"  where staff_id = #{staff_id} AND vote_start_id = #{vote_start_id}")
	StaffVote get_staff_vote (@Param("staff_id") String staff_id,@Param("vote_start_id") int vote_start_id);
	
	@SelectProvider(type=StaffVoteDynaSqlProvider.class,method="new_staff_vote")
	void new_staff_vote(StaffVote staffvote);
}
