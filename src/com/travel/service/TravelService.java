package com.travel.service;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.travel.domain.Admin;
import com.travel.domain.Apartment;
import com.travel.domain.Business;
import com.travel.domain.Expense;
import com.travel.domain.Staff;
import com.travel.domain.StaffVote;
import com.travel.domain.Vote;
import com.travel.domain.Vote_start;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;

public interface TravelService {
    //管理员表
	Admin admin_login(String id,String password);
	Admin get_admin(String id);
	void insert_admin(Admin admin);
	void delete_admin(String id);
	List<Admin> get_all_admin_list();
	
	//普通员工表
	Staff staff_login(String id,String password);
	Staff get_staff(String id);
	StaffVote get_staff_vote( String staff_id,int vote_start_id);
	void insert_staff(Staff staff);//添加员工
	void delete_staff(String id);//删除员工
	void update_staff(Staff staff);//修改员工信息
	List<Staff>get_all_staff_list();
	List<Business> apartment_id_business(String apartment_id);
	List<Business> apartment_id_nowbusiness(String apartment_id);
	
	
	//投票选项表
	void update_vote_sum(Vote v);
	
	//投票记录表
	void new_staff_vote(StaffVote staffvote);
	
	//财务人员表
	Finance finance_login(String id,String password);
	Finance get_finance(String id);
	void insert_finance(Finance finance);//添加财务
	void delete_finance(String id);//删除财务
	void update_finance(Finance finance);//修改财务信息
	List<Finance> get_all_finance_list();   
	
	//经理表
	Manager manager_login(String id,String password);
	Manager get_manager(String id);
	void new_vote_start(Vote_start vote_start);
	void new_vote(Vote vote);
	Vote_start get_vote_start(String manager_id,Date date);
	List<Vote_start> get_vote_start_list(String manager_id);
	Vote_start get_vote_start_info(int id);
	List<Vote> get_votes(int vote_start_id);
	void update_vote_start(Vote_start vote_start);
	void update_vote(Vote vote);
	void delete_vote(int vote_start_id);
	void delete_vote_start(int id);
	void insert_manager(Manager manager);
	void delete_manager(String id);
	void update_manager(Manager manager);
	List<Manager> get_all_manager_list();
	Apartment get_apartment(String id);
	Apartment get_apartment_manager(String manager_id);
	
	//公告表
	void new_notice(Notice notice);
	Notice get_notice(String id,Timestamp date);
	void delete_notice(String id,Timestamp date);
	List<Notice> get_notice_list(String manager_id);
	
	//部门表
	List<Apartment> get_apartment_list();


	//出差申请
		//新建出差申请表
		void insert_Business(Business job);
		//查找某个员工已批准或不批准的申请表
		List<Business> get_Business_pastList(String id);
		//查找某个员工申请状态中的申请表
		List<Business> get_Business_nowList(String id);
		//查找全部的出差申请表
		List<Business> get_All_Business();
		List<Business> get_Allnow_Business();
		//搜索某条申请表
		Business get_Business_LikeList(String id,Timestamp content);
		//员工修改出差申请表
		void update_Business(Business job);
		//经理修改出差申请表
		void update_Business_status(Business job);
		//删除出差申请表
		void delete_Business(String is,Timestamp date);


//报销申请
	//新建报销申请表
	void insert_Expense(Expense job);
	//查找某个员工已批准或不批准的申请表
	List<Expense> get_Expense_pastList(String id);
	//查找某个员工申请状态中的申请表
	List<Expense> get_Expense_nowList(String id);
	//查找全部的出差申请表
	List<Expense> get_All_Expense();
	//查找未批准的全部申请表
	List<Expense> get_now_List();
	//搜索某条申请表
	Expense get_Expense_LikeList(String id,Timestamp date);
	//员工修改出差申请表
	void update_Expense(Expense job);
	//经理修改出差申请表
	void update_Expense_status(Expense job);
	//删除出差申请表
	void delete_Expense(String id,Timestamp date);
}
