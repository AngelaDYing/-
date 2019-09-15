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
    //����Ա��
	Admin admin_login(String id,String password);
	Admin get_admin(String id);
	void insert_admin(Admin admin);
	void delete_admin(String id);
	List<Admin> get_all_admin_list();
	
	//��ͨԱ����
	Staff staff_login(String id,String password);
	Staff get_staff(String id);
	StaffVote get_staff_vote( String staff_id,int vote_start_id);
	void insert_staff(Staff staff);//���Ա��
	void delete_staff(String id);//ɾ��Ա��
	void update_staff(Staff staff);//�޸�Ա����Ϣ
	List<Staff>get_all_staff_list();
	List<Business> apartment_id_business(String apartment_id);
	List<Business> apartment_id_nowbusiness(String apartment_id);
	
	
	//ͶƱѡ���
	void update_vote_sum(Vote v);
	
	//ͶƱ��¼��
	void new_staff_vote(StaffVote staffvote);
	
	//������Ա��
	Finance finance_login(String id,String password);
	Finance get_finance(String id);
	void insert_finance(Finance finance);//��Ӳ���
	void delete_finance(String id);//ɾ������
	void update_finance(Finance finance);//�޸Ĳ�����Ϣ
	List<Finance> get_all_finance_list();   
	
	//�����
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
	
	//�����
	void new_notice(Notice notice);
	Notice get_notice(String id,Timestamp date);
	void delete_notice(String id,Timestamp date);
	List<Notice> get_notice_list(String manager_id);
	
	//���ű�
	List<Apartment> get_apartment_list();


	//��������
		//�½����������
		void insert_Business(Business job);
		//����ĳ��Ա������׼����׼�������
		List<Business> get_Business_pastList(String id);
		//����ĳ��Ա������״̬�е������
		List<Business> get_Business_nowList(String id);
		//����ȫ���ĳ��������
		List<Business> get_All_Business();
		List<Business> get_Allnow_Business();
		//����ĳ�������
		Business get_Business_LikeList(String id,Timestamp content);
		//Ա���޸ĳ��������
		void update_Business(Business job);
		//�����޸ĳ��������
		void update_Business_status(Business job);
		//ɾ�����������
		void delete_Business(String is,Timestamp date);


//��������
	//�½����������
	void insert_Expense(Expense job);
	//����ĳ��Ա������׼����׼�������
	List<Expense> get_Expense_pastList(String id);
	//����ĳ��Ա������״̬�е������
	List<Expense> get_Expense_nowList(String id);
	//����ȫ���ĳ��������
	List<Expense> get_All_Expense();
	//����δ��׼��ȫ�������
	List<Expense> get_now_List();
	//����ĳ�������
	Expense get_Expense_LikeList(String id,Timestamp date);
	//Ա���޸ĳ��������
	void update_Expense(Expense job);
	//�����޸ĳ��������
	void update_Expense_status(Expense job);
	//ɾ�����������
	void delete_Expense(String id,Timestamp date);
}
