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
	//����ȫ�������
		@Select("select * from "+EXPENSETABLE+" where  status=1 OR status=2")
		List<Expense> get_All_Expense();
		//����ȫ��δ�����
		@Select("select * from "+EXPENSETABLE+" where  status=0 ")
		List<Expense> get_now_List();
		//����ĳ��Ա������׼����׼�������
		@Select("select * from "+EXPENSETABLE+" where id = #{id} AND status=1 OR status=2")
		List<Expense> get_Expense_pastList(String id);
		//����ĳ��Ա������״̬�е������
		@Select("select * from "+EXPENSETABLE+" where id = #{id} AND status = 0")
		List<Expense> get_Expense_nowList(String id);
		//����ĳ�������
		@Select("select * from "+EXPENSETABLE+"  where id = #{id} AND date = #{date}")
		Expense get_Expense_LikeList(@Param("id")String id,@Param("date")Timestamp date);
		//�½������
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="insert_Expense")
		void insert_Expense(Expense job);
		//Ա���޸�������޷��޸�״̬
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="update_Expense")
		void update_Expense(Expense job);
		//�����޸������ֻ���޸�״̬
		@SelectProvider(type=ExpenseDynaSqlProvider.class,method="update_Expense_status")
		void update_Expense_status(Expense job);
		// ��������ɾ�������
		@Delete(" delete from "+EXPENSETABLE+" where id = #{id} and date = #{date} ")
		void delete_Expense(@Param("id")String id,@Param("date")Timestamp date);

}
