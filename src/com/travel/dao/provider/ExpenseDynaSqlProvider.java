package com.travel.dao.provider;
import static com.travel.util.common.Constants.EXPENSETABLE;
import org.apache.ibatis.jdbc.SQL;
import com.travel.domain.Expense;

public class ExpenseDynaSqlProvider {
	// 动态插入
	public String insert_Expense(Expense job){
		
		return new SQL(){
			{
				INSERT_INTO(EXPENSETABLE);
				if(job.getId()!=null) {
					VALUES("id", "#{id}");
				}
				if(job.getDate() != null ){
					VALUES("date", "#{date}");
				}
				if(job.getPicture() != null ){
					VALUES("picture", "#{picture}");
				}
				if(job.getItem() != null ){
					VALUES("item", "#{item}");
				}
				if(job.getDetail()!=null){
					VALUES("detail","#{detail}");
				}
				if(job.getMoney()!= 0 ){
					VALUES("money","#{money}");
				}
				if(job.getMember()!= 0 ){
					VALUES("member","#{member}");
				}
				VALUES("status","#{status}");
				
			}
		}.toString();
	}	
	// 动态更新
	public String update_Expense(Expense job){
		
		return new SQL(){
			{
				UPDATE(EXPENSETABLE);
				if(job.getDate() != null ){
					SET("date = #{date}");
				}
				if(job.getPicture() != null ){
					SET("picture=#{picture}");
				}
				if(job.getItem() != null ){
					SET("item=#{item}");
				}
				if(job.getDetail()!=null){
					SET("detail= #{detail}");
				}
				if(job.getMoney()!= 0 ){
					SET("money= #{money}");
				}
			WHERE(" id = #{id} AND date=#{date}");
				
			}
		}.toString();
	}
	public String update_Expense_status(Expense job){
		
		return new SQL(){
			{
				UPDATE(EXPENSETABLE);
				
				
					SET("status= #{status}");
				
			WHERE(" id = #{id} and date=#{date}");
				
			}
		}.toString();
	}

}
