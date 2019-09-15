package com.travel.dao;

import static com.travel.util.common.Constants.BUSINESSTABLE;
import static com.travel.util.common.Constants.EXPENSETABLE;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.ExpenseDynaSqlProvider;
import com.travel.domain.Business;
import com.travel.domain.Expense;

public interface ExpenseDao {
	//查找全部申请表
		@Select("select * from "+EXPENSETABLE+" where  status=1 OR status=2")
		List<Expense> get_All_Expense();
		//查找全部未申请表
		@Select("select * from "+EXPENSETABLE+" where  status=0 ")
		List<Expense> get_now_List();
		//查找某个员工已批准或不批准的申请表
		@Select("select * from "+EXPENSETABLE+" where id = #{id} AND status=1 OR status=2")
		List<Expense> get_Expense_pastList(String id);
		//查找某个员工申请状态中的申请表
		@Select("select * from "+EXPENSETABLE+" where id = #{id} AND status = 0")
		List<Expense> get_Expense_nowList(String id);
		//查找某条申请表
		@Select("select * from "+EXPENSETABLE+"  where id = #{id} AND date = #{date}")
		Expense get_Expense_LikeList(@Param("id")String id,@Param("date")Timestamp date);
		//新建申请表
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="insert_Expense")
		void insert_Expense(Expense job);
		//员工修改申请表，无法修改状态
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="update_Expense")
		void update_Expense(Expense job);
		//财务修改申请表，只能修改状态
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="update_Expense_status")
		void update_Expense_status(Expense job);
		// 根据日期删除申请表
		@Delete(" delete from "+EXPENSETABLE+" where id = #{id} and date = #{date} ")
		void delete_Expense(@Param("id")String id,@Param("date")Timestamp date);

}
