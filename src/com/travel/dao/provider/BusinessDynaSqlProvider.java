package com.travel.dao.provider;
import static com.travel.util.common.Constants.BUSINESSTABLE;
import org.apache.ibatis.jdbc.SQL;
import com.travel.domain.Business;
public class BusinessDynaSqlProvider {
	// 动态插入
				public String insert_Business(Business job){
					
					return new SQL(){
						{
							INSERT_INTO(BUSINESSTABLE);
							if(job.getId()!=null) {
								VALUES("id", "#{id}");
							}
							if(job.getDate() != null ){
								VALUES("date", "#{date}");
							}
							if(job.getStart_date() != null ){
								VALUES("start_date", "#{start_date}");
							}
							if(job.getDays()!= 0 ){
								VALUES("days","#{days}");
							}
							if(job.getDetail()!=null){
								VALUES("detail","#{detail}");
							}
							if(job.getMoney()!= 0 ){
								VALUES("money","#{money}");
							}
							VALUES("status","#{status}");						
							
						}
					}.toString();
				}	
				// 动态更新
				public String update_Business(Business job){
					
					return new SQL(){
						{
							UPDATE(BUSINESSTABLE);
							if(job.getDate() != null ){
								SET("date = #{date}");
							}
							if(job.getStart_date() != null ){
								SET("start_date= #{start_date}");
							}
							if(job.getDays()!= 0 ){
								SET("days= #{days}");
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
				public String update_Business_status(Business job){
					
					return new SQL(){
						{
							UPDATE(BUSINESSTABLE);
							
							if(job.getStatus()!= 0 ){
								SET("status= #{status}");
							}
							WHERE(" id = #{id} AND date=#{date}");
						}
					}.toString();
				}
				
}
