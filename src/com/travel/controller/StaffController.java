package com.travel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.travel.domain.Admin;
import com.travel.domain.Apartment;
import com.travel.domain.Staff;
import com.travel.domain.StaffVote;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote_start;
import com.travel.domain.Vote;
import com.travel.domain.VoteContent;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class StaffController {
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
	
	//�鿴�Ž��(ֻ���侭��ķ�����ͶƱ)
	@RequestMapping(value="/staff/activity/search",method=RequestMethod.GET)
	 public String get_vote_start_list(Model model,HttpServletRequest request){
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String apartment_id=staff.getApartment_id();
		Apartment apartment=travelservice.get_apartment(apartment_id);
		String manager_id=apartment.getManager_id();
		List<Vote_start> vote_start_list=travelservice.get_vote_start_list(manager_id);
		model.addAttribute("vote_start_list",vote_start_list);
		return "/staff/activity/search";
	}
	
	//����ͶƱ
	@RequestMapping(value="/staff/activity/vote",method=RequestMethod.GET)
	 public String vote(int id,Model model,HttpServletRequest request){//��֤�Ƿ����ͶƱ
		System.out.println("����֪�����ͶƱ");
		Vote_start vote_start=travelservice.get_vote_start_info(id);
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		int status=vote_start.getStatus();
		System.out.println("�����ж�");
		if(status==0) {
			model.addAttribute("message", "��ͶƱ�Ѿ�������������ͶƱ��");
			System.out.println("��ͶƱ�Ѿ�������������ͶƱ��");
			return "/staff/activity/search";
		}
		System.out.println("��ͶƱδ��ֹ��");
		StaffVote staff_vote=travelservice.get_staff_vote(staff_id, id);
		if(staff_vote!=null) {
			model.addAttribute("message", "���Ѳ����ͶƱ���������ظ�ͶƱ��");
			System.out.println("���Ѳ����ͶƱ���������ظ�ͶƱ��");
			return "/staff/activity/search";
		}
		System.out.println("δ����ͶƱ��");
		System.out.println("ת��ͶƱҳ�棡");
	//	Vote_start vote_start=travelservice.get_vote_start_info(id);
		int vote_start_id=vote_start.getId();
		List<Vote> votes = travelservice.get_votes(vote_start_id);
		VoteContent votecontent = new VoteContent();
	    votecontent.setVote_start(vote_start);
	    votecontent.setVotes(votes);
	    model.addAttribute("votecontent",votecontent);//������Ⱦ
		return "/staff/activity/vote";
		
	}
	@RequestMapping(value="/staff/activity/vote",method=RequestMethod.POST)
	public ModelAndView vote(ModelAndView mv,@RequestParam("option") String option,int id,HttpServletRequest request){
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		System.out.println("��ʼͶƱ��");
		String staff_id=staff.getId();
		StaffVote staffvote=new StaffVote();
		staffvote.setStaff_id(staff_id);
		staffvote.setVote_start_id(id);
	//	travelservice.new_staff_vote(staffvote);
		Vote_start vote_start=travelservice.get_vote_start_info(id);
		int vote_start_id=vote_start.getId();
		List<Vote> votes = travelservice.get_votes(vote_start_id);
		VoteContent votecontent = new VoteContent();
	    votecontent.setVote_start(vote_start);
	    votecontent.setVotes(votes);
	    mv.addObject("votecontent",votecontent);//������Ⱦ
	    for (Vote v : votes) {
			   if(v!=null) {
	              if(v.getName().equals(option)) {
	            	  int sum=v.getSum();
	            	  sum=sum+1;
	            	  v.setSum(sum);
	            	  travelservice.update_vote_sum(v);//��1
	            	  System.out.println("�ɹ�ͶƱ��");
	              }
			   }
	        }
	    travelservice.new_staff_vote(staffvote);
		mv.setViewName("/staff/activity/search");
		return mv;
	}
	
	//Ա���鿴����
	@RequestMapping(value="/welcomestaff",method=RequestMethod.GET)
	 public void get_notice_list(Model model,HttpServletRequest request){
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String apartment_id=staff.getApartment_id();
		Apartment apartment=travelservice.get_apartment(apartment_id);
		String manager_id=apartment.getManager_id();
		List<Notice> noticelist=travelservice.get_notice_list(manager_id);
		model.addAttribute("noticelist",noticelist);
		//return "/staff/notice/search";
	}
	
	//�޸���ͨԱ���˺�
	@RequestMapping(value="/staff/update/password",method=RequestMethod.GET)
	public String updatestaff(Model model,HttpServletRequest request){
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String id=staff.getId();
		staff=travelservice.get_staff(id);
		model.addAttribute("staff",staff);
		List<Apartment> apartmentlist=travelservice.get_apartment_list();
		model.addAttribute("apartmentlist", apartmentlist);
		return "/staff/update/password";
	}
	@RequestMapping(value="/staff/update/password",method=RequestMethod.POST)
	public String updatestaffinfo(Model model,String id,@ModelAttribute Staff staff){
		staff.setId(id);
		travelservice.update_staff(staff);
		return "/staff/update/password";
	}
}
