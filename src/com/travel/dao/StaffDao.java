package com.travel.dao;
import static com.travel.util.common.Constants.STAFFTABLE;

import java.util.List;
import java.sql.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.StaffDynaSqlProvider;
import com.travel.domain.Staff;
import com.travel.domain.Vote;

public interface StaffDao {
	//查询
	@Select("select * from "+STAFFTABLE+"  where id = #{id} AND password = #{password}")
	Staff staff_login(@Param("id") String id,@Param("password") String password);
	
	@Select("select * from "+STAFFTABLE+"  where id = #{id}")
	Staff get_staff(@Param("id") String id);
	
	@SelectProvider(type=StaffDynaSqlProvider.class,method="update_vote_sum")
	void update_vote_sum(Vote v);
	
	//添加staff
	@SelectProvider(type=StaffDynaSqlProvider.class,method="insert_staff")
	void insert_staff(Staff staff);
	//修改staff信息
	@SelectProvider(type=StaffDynaSqlProvider.class,method="update_staff")
	void update_staff(Staff staff);
	// 根据id删除员工
	@Delete(" delete from "+STAFFTABLE+" where id = #{id} ")
	void delete_staff(String id);
	
	@Select("select * from "+STAFFTABLE+"  ")
	List<Staff> get_all_staff_list();
}
