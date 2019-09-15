package com.travel.dao;
import static com.travel.util.common.Constants.FINANCETABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.travel.dao.provider.FinanceDynaSqlProvider;
import com.travel.domain.Finance;

public interface FinanceDao {
	//≤È—Ø
    @Select("select * from "+FINANCETABLE+"  where id = #{id} AND password = #{password}")
    Finance finance_login(@Param("id") String id,@Param("password") String password);
    
    @Select("select * from "+FINANCETABLE+"  where id = #{id} ")
    Finance get_finance(@Param("id") String id);
    
    @SelectProvider(type=FinanceDynaSqlProvider.class,method="insert_finance")
	void insert_finance(Finance finance);
	
	@SelectProvider(type=FinanceDynaSqlProvider.class,method="update_finance")
	void update_finance(Finance finance);
	
	@Delete("delete from "+FINANCETABLE+" where id = #{id} ")
	void delete_finance(String id);
	
	@Select("select * from "+FINANCETABLE+" ")
	List<Finance> get_all_finance_list();
}
