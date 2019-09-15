package com.travel.service.impl;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.service.TravelService;
import com.travel.dao.AdminDao;
import com.travel.dao.ApartmentDao;
import com.travel.dao.FinanceDao;
import com.travel.dao.ManagerDao;
import com.travel.dao.NoticeDao;
import com.travel.dao.StaffDao;
import com.travel.dao.StaffvoteDao;
import com.travel.dao.BusinessDao;
import com.travel.dao.ExpenseDao;
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


@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("TravelService")
public class TravelServiceImpl implements TravelService{
	@Autowired
	private AdminDao admindao;
	@Autowired
	private StaffDao staffdao;
	@Autowired
	private ManagerDao managerdao;
	@Autowired
	private FinanceDao financedao;
	@Autowired
	private ApartmentDao apartmentdao;
	@Autowired
	private StaffvoteDao staffvotedao;
	@Autowired
	private NoticeDao noticedao;
	@Autowired
	private BusinessDao businessdao;
	@Autowired
	private ExpenseDao expensedao;

	//����Ա��
	@Transactional(readOnly=true)
	@Override
	public Admin admin_login(String id,String password) {
	     return admindao.admin_login(id, password);
	}
	@Override
	public Admin get_admin(String id) {
		// TODO Auto-generated method stub
		return admindao.get_admin(id);
	}

	@Override
	public void insert_admin(Admin admin) {
		// TODO Auto-generated method stub
		admindao.insert_admin(admin);
	}

	@Override
	public void delete_admin(String id) {
		// TODO Auto-generated method stub
		admindao.delete_admin(id);
	}
	@Override
	public List<Admin> get_all_admin_list() {
		// TODO Auto-generated method stub
		return admindao.get_all_admin_list();
	}
	
	//��ͨԱ����
	@Override
	public Staff staff_login(String id,String password) {
		 return staffdao.staff_login(id, password);
	}
	@Override
	public Staff get_staff(String id) {
		// TODO Auto-generated method stub
		return staffdao.get_staff(id);
	}
		
	@Override
	public StaffVote get_staff_vote( String staff_id,int vote_start_id) {
		 return staffvotedao.get_staff_vote(staff_id, vote_start_id);
	}

	@Override
	public void insert_staff(Staff staff) {
		// TODO Auto-generated method stub
		staffdao.insert_staff(staff);
	}
	
	@Override
	public void delete_staff(String id) {
		// TODO Auto-generated method stub
		staffdao.delete_staff(id);
	}
	@Override
	public void update_staff(Staff staff) {
		// TODO Auto-generated method stub
		staffdao.update_staff(staff);
	}
	@Override
	public List<Staff> get_all_staff_list() {
		// TODO Auto-generated method stub
		return staffdao.get_all_staff_list();
	}
	
	@Override
	public List<Business> apartment_id_business(String apartment_id) {
		return businessdao.apartment_id_business(apartment_id);
	}
	@Override
	public List<Business> apartment_id_nowbusiness(String apartment_id) {
		return businessdao.apartment_id_nowbusiness(apartment_id);
	}
	
	
	
	//ͶƱѡ���
	@Override
	public void update_vote_sum(Vote v) {
		staffdao.update_vote_sum(v);
	}
		
	//ͶƱ��¼��
	@Override
	public void new_staff_vote(StaffVote staffvote) {
		staffvotedao.new_staff_vote(staffvote);
		
	}
	
	//������Ա��
	@Override
	public Finance finance_login(String id,String password) {
		 return financedao.finance_login(id, password);
	}
	@Override
	public Finance get_finance(String id) {
		// TODO Auto-generated method stub
		return financedao.get_finance(id);
	}
	@Override
	public void insert_finance(Finance finance) {
		// TODO Auto-generated method stub
		financedao.insert_finance(finance);
	}
	
	@Override
	public void delete_finance(String id) {
		// TODO Auto-generated method stub
		financedao.delete_finance(id);
	}
	@Override
	public void update_finance(Finance finance) {
		// TODO Auto-generated method stub
		financedao.update_finance(finance);
	}
	@Override
	public List<Finance> get_all_finance_list() {
		// TODO Auto-generated method stub
		return financedao.get_all_finance_list();
	}
	
	
	
	//�����
	@Override
	public Manager manager_login(String id,String password) {
		 return managerdao.manager_login(id, password);
	}
	@Override
	public Manager get_manager(String id) {
		// TODO Auto-generated method stub
		return managerdao.get_manager(id);
	}

	@Override
	public void new_vote_start(Vote_start vote_start) {
		managerdao.new_vote_start(vote_start);
	} 
	@Override
	public void new_vote(Vote vote) {
		managerdao.new_vote(vote);
	} 
	@Override
	public Vote_start get_vote_start(String manager_id,Date date) {
		return managerdao.get_vote_start(manager_id, date);
	}
	@Override
	public List<Vote_start> get_vote_start_list(String manager_id){
		return managerdao.get_vote_start_list(manager_id);
	}
	@Override
	public Vote_start get_vote_start_info(int id){
		return managerdao.get_vote_start_info(id);
	}
	
	@Override
	public List<Vote> get_votes(int vote_start_id){
		return managerdao.get_votes(vote_start_id);
	}
	@Override
	public void update_vote_start(Vote_start vote_start) {
		managerdao.update_vote_start(vote_start);
	}
	@Override
	public void update_vote(Vote vote) {
		managerdao.update_vote(vote);
	}
	@Override
	public void delete_vote(int vote_start_id) {
		managerdao.delete_vote(vote_start_id);
	}
	@Override
	public void delete_vote_start(int id) {
		managerdao.delete_vote_start(id);
	}
	@Override
	public void insert_manager(Manager manager) {
		// TODO Auto-generated method stub
		managerdao.insert_manager(manager);
	}
	
	@Override
	public void delete_manager(String id) {
		// TODO Auto-generated method stub
		managerdao.delete_manager(id);
	}
	@Override
	public void update_manager(Manager manager) {
		// TODO Auto-generated method stub
		managerdao.update_manager(manager);
	}
	@Override
	public List<Manager> get_all_manager_list() {
		// TODO Auto-generated method stub
		return managerdao.get_all_manager_list();
	}

	
	//�����
	@Override
	public void new_notice(Notice notice) {
		noticedao.new_notice(notice);
	}
	@Override
    public Notice get_notice(String id,Timestamp date) {
    	return noticedao.get_notice(id, date);
    }
	@Override
    public void delete_notice(String id,Timestamp date) {
    	noticedao.delete_notice(id, date);
    }

	@Override
	public List<Notice> get_notice_list(String manager_id) {
		// TODO Auto-generated method stub
		return noticedao.get_notice_list(manager_id);
	}
	
	
	//���ű�
	@Override
	public Apartment get_apartment(String id) {
		return apartmentdao.get_apartment(id);
	}
	@Override
	public List<Apartment> get_apartment_list() {
		// TODO Auto-generated method stub
		return apartmentdao.get_apartment_list();
	}

	//��������
		//�½����������
		@Override
		public void insert_Business(Business job) {
			businessdao.insert_Business(job);
				
		}	
		@Override
		//����ȫ�����������
		public List<Business> get_All_Business() {
			// TODO Auto-generated method stub
			return businessdao.get_All_Business();
		}
		@Override
		public List<Business> get_Allnow_Business(){
			return businessdao.get_Allnow_Business();
		}
		
		//����ĳ��Ա������׼����׼�������
		@Override
		public List<Business> get_Business_pastList(String id) {
			// TODO Auto-generated method stub
			return businessdao.get_Business_pastList(id);
		}	
		//����ĳ��Ա������״̬�е������
		@Override
		public List<Business> get_Business_nowList(String id) {
			// TODO Auto-generated method stub
			return businessdao.get_Business_nowList(id);
		}		
		@Override
		//����ĳ�����������
		public Business get_Business_LikeList(String id,Timestamp content) {
			return businessdao.get_Business_LikeList(id,content);
		}
		//Ա���޸ĳ��������
		@Override
		public void update_Business(Business job) {
			// TODO Auto-generated method stub
			businessdao.update_Business(job);	
		}		
		//�����޸ĳ��������״̬
		@Override
		public void update_Business_status(Business job) {
			// TODO Auto-generated method stub
			businessdao.update_Business_status(job);
		}
		@Override
		public void delete_Business(String id,Timestamp date) {
			// TODO Auto-generated method stub
			businessdao.delete_Business(id,date);
		}
		@Override
		public Apartment get_apartment_manager(String manager_id) {
			// TODO Auto-generated method stub
			return apartmentdao.get_apartment_manager(manager_id);
		}
		
		
//������
	//�½����������
	@Override
	public void insert_Expense(Expense job) {
		expensedao.insert_Expense(job);			
	}			
	//����ȫ�����������
	@Override
	public List<Expense> get_All_Expense() {
		// TODO Auto-generated method stub
		return expensedao.get_All_Expense();
	}		
	@Override
	public List<Expense> get_now_List() {
		// TODO Auto-generated method stub
		return expensedao.get_now_List();
	}
	//����ĳ��Ա������׼����׼�������
	@Override
	public List<Expense> get_Expense_pastList(String id) {
		// TODO Auto-generated method stub
		return expensedao.get_Expense_pastList(id);
	}
	//����ĳ��Ա������״̬�е������
	@Override
	public List<Expense> get_Expense_nowList(String id) {
		// TODO Auto-generated method stub
		return expensedao.get_Expense_nowList(id);
	}		
	@Override
	//����ĳ�����������
	public Expense get_Expense_LikeList(String id,Timestamp date) {
		return expensedao.get_Expense_LikeList(id,date);
	}
	//Ա���޸ĳ��������
	@Override
	public void update_Expense(Expense job) {
		// TODO Auto-generated method stub
		expensedao.update_Expense(job);
				
	}
	//�����޸ĳ��������״̬
	@Override
	public void update_Expense_status(Expense job) {
		// TODO Auto-generated method stub
		expensedao.update_Expense_status(job);			
	}
	@Override
	public void delete_Expense(String id,Timestamp date) {
		// TODO Auto-generated method stub
		expensedao.delete_Expense(id,date);
	}


}
