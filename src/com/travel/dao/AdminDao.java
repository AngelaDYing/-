package com.travel.dao;

import static com.travel.util.common.Constants.ADMINTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.AdminDynaSqlProvider;
import com.travel.domain.Admin;

public interface AdminDao {
	//≤È—Ø
	@Select("select * from "+ADMINTABLE+"  where id = #{id} AND password = #{password}")
	Admin admin_login(@Param("id") String id,@Param("password") String password);
	
	@Select("select * from "+ADMINTABLE+"  where id = #{id}")
	Admin get_admin(@Param("id") String id);
	
	@Delete(" delete from "+ADMINTABLE+" where id = #{id} ")
	void delete_admin(String id);
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="insert_admin")
	void insert_admin(Admin admin);
	
	@Select("select * from "+ADMINTABLE+"  ")
	List<Admin> get_all_admin_list();
}
