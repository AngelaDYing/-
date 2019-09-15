package com.travel.dao;

import static com.travel.util.common.Constants.BUSINESSTABLE;
import static com.travel.util.common.Constants.STAFFTABLE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.BusinessDynaSqlProvider;
import com.travel.domain.Business;

public interface BusinessDao {
	//查找全部申请表
	@Select("select * from "+BUSINESSTABLE+" ")
	List<Business> get_All_Business();
	//查看全部员工未被批准的申请表
	@Select("select * from "+BUSINESSTABLE+" where status=0")
	List<Business> get_Allnow_Business();
	//查找某个员工已批准或不批准的申请表
	@Select("select * from "+BUSINESSTABLE+" where id = #{id} AND status=1 OR status=2")
	List<Business> get_Business_pastList(String id);
	//查找某个员工申请状态中的申请表
	@Select("select * from "+BUSINESSTABLE+" where id = #{id} AND status = 0")
	List<Business> get_Business_nowList(String id);
	//查找某条申请表
	@Select("select * from "+BUSINESSTABLE+"  where id = #{id} AND date = #{date}")
	Business get_Business_LikeList(@Param("id")String id,@Param("date")Timestamp date);
	//新建申请表
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="insert_Business")
	void insert_Business(Business job);
	//员工修改申请表，无法修改状态
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="update_Business")
	void update_Business(Business job);
	//经理修改申请表，只能修改状态
	@SelectProvider(type=BusinessDynaSqlProvider.class,method="update_Business_status")
	void update_Business_status(Business job);
	// 根据日期删除申请表
	@Delete(" delete from "+BUSINESSTABLE+" where id = #{id} AND date = #{date} ")
	void delete_Business(@Param("id")String id,@Param("date")Timestamp date);
	
	//以下是原来用的方法，现在不用了
	//多表查询，查找某个经理的部门的所有员工申请表
	//@Select("SELECT * from" +BUSINESSTABLE +"INNER JOIN" + STAFFTABLE + "")
	@Select("<script> SELECT  *  from  BUSINESSTABLE t" +
            " INNER JOIN STAFFTABLE t1 on t1.id = t.id "
            + " <if test=\"id != null and '' != id\"> and t1.apartment_id = #{apartment_id} </if> "
            + " </script>")
	List<Business> apartment_id_business(String apartment_id);
	
	//多表查询，查找某个经理的部门的申请中的员工申请表
		//@Select("SELECT * from" +BUSINESSTABLE +"INNER JOIN" + STAFFTABLE + "")
		@Select("<script> SELECT  *  from  BUSINESSTABLE t" +
	            " INNER JOIN STAFFTABLE t1 on t1.id = t.id "
	            + " <if test=\"id != null and '' != id\"> and t1.apartment_id = #{apartment_id} </if> and status = 0 "
	            + " </script>")
		List<Business> apartment_id_nowbusiness(String apartment_id);
	

	

	

}


