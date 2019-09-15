package com.travel.controller;

import java.sql.Timestamp;
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
import com.travel.domain.Staff;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.domain.Vote_start;
import com.travel.domain.Vote;
import com.travel.domain.VoteContent;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class ManagerController {
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
	//�����Ž���Ŀ
	@RequestMapping(value="/manager/activity/new",method=RequestMethod.GET)
	 public String add(Model model){
		System.out.println("�������Ž�ͶƱ��");
		return "/manager/activity/new";//�����Ž�JSP
	}
	@RequestMapping(value="/manager/activity/new",method=RequestMethod.POST)
	 public String add(Model mv,@ModelAttribute VoteContent votecontent,HttpServletRequest request){
		System.out.println("�Ž�ͶƱ�����ݳɹ���");
		if(votecontent==null) {
			System.out.println("votecontentΪnull");
		}
		Vote_start vote_start=votecontent.getVote_start();
		if(vote_start==null) {
			System.out.println("vote_startΪnull");
			//System.out.println(votecontent.getVote_start());
		}
		List<Vote> votes=votecontent.getVotes();
		if(votes==null) {
			System.out.println("votesΪnull");
		}
		//Timestamp date = new Timestamp(System.currentTimeMillis()); 
		Date d = new Date();       
		Timestamp date = new Timestamp(d.getTime());
		
		if(vote_start!=null) {
		vote_start.setDate(date);
		}
		vote_start.setStatus(1);
		System.out.println("�����Ž�ʱ��ɹ�");
	    Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
	    String manager_id=manager.getId();
	    System.out.println(manager_id);
	    vote_start.setManager_id(manager_id);
		travelservice.new_vote_start(vote_start);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		vote_start = travelservice.get_vote_start(manager_id,Timestamp.valueOf(sdf.format(date)));
		System.out.println(date);
		if(vote_start==null) {
			System.out.println("vote_startΪnull");
		}
		int vote_start_id = vote_start.getId();
		for (Vote v : votes) {
		   if(v!=null) {
		       v.setSum(0);
		       v.setVote_start_id(vote_start_id);
               travelservice.new_vote(v);
               System.out.println("�ɹ����һ��ͶƱѡ��!");
		   }
        }
		//mv.setViewName("redirect:/manager/activity/search");//�����ɹ�����ת�����е��Ž���Ŀ
		return "/manager/activity/search";
	}
	
	//�鿴��ʷ�Ž���Ŀ�б�
	@RequestMapping(value="/manager/activity/search",method=RequestMethod.GET)
	 public String get_vote_start_list(Model model,HttpServletRequest request){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		List<Vote_start> vote_start_list = travelservice.get_vote_start_list(manager_id);
		model.addAttribute("vote_start_list",vote_start_list);
		return "/manager/activity/search";
	}
	
	//�鿴����ͶƱ��Ŀ�Ľ�������鿴����
	@RequestMapping(value="/manager/activity/result",method=RequestMethod.GET)
	 public String get_detail(Model model,int id){
		if(id>=0) {
			Vote_start vote_start=travelservice.get_vote_start_info(id);
			int vote_start_id=vote_start.getId();
			List<Vote> votes = travelservice.get_votes(vote_start_id);
			VoteContent votecontent = new VoteContent();
		    votecontent.setVote_start(vote_start);
		    votecontent.setVotes(votes);
			model.addAttribute("votecontent",votecontent);
		}
		return "/manager/activity/result";
	}
	
	//�޸ĵ����Ž���Ŀ
	@RequestMapping(value="/manager/activity/update",method=RequestMethod.GET)
	 public String update_votecontent(Model model,int id){
		if(id>=0) {
			Vote_start vote_start=travelservice.get_vote_start_info(id);
			int vote_start_id=vote_start.getId();
			List<Vote> votes = travelservice.get_votes(vote_start_id);
			VoteContent votecontent = new VoteContent();
		    votecontent.setVote_start(vote_start);
		    votecontent.setVotes(votes);
			model.addAttribute("votecontent",votecontent);
		}
		System.out.println("ת���޸�ͶƱ����");
		return "/manager/activity/update";
	}
	
	@RequestMapping(value="/manager/activity/update",method=RequestMethod.POST)
	 public ModelAndView update_votecontent(ModelAndView mv,@ModelAttribute VoteContent votecontent,@RequestParam(value="id") int id){
		System.out.println("�ύ�޸��Ž�����");
		List<Vote> votes=votecontent.getVotes();
		Vote_start vote_start=votecontent.getVote_start();
		if (votes==null) {
			System.out.println("û��votes");
		}
		if (vote_start==null) {
			System.out.println("û��votestart");
		}

		vote_start.setId(id);
		System.out.println(id);
		travelservice.update_vote_start(vote_start);
		
		int vote_start_id=vote_start.getId();
		//travelservice.delete_vote(vote_start_id);
		for (Vote v : votes) {
			   if(v!=null) {
				   travelservice.update_vote(v);
				   System.out.println(v.getName());
	              // travelservice.new_vote(v);
	               System.out.println("�ɹ�����һ��ͶƱѡ��!");
			   }
	        }
		mv.setViewName("redirect:/manager/activity/search");
		return mv;
	}
	
	
	//ɾ�������Ž���Ŀ
	@RequestMapping(value="/manager/activity/delete",method=RequestMethod.GET)
	 public void delete_vote(int id){
		System.out.println(id);
		System.out.println("ɾ���Ž���Ŀ��");
		if(Integer.toString(id)!=null){
			
			travelservice.delete_vote(id);
			travelservice.delete_vote_start(id);
			
		}
	}
	
	//�����Ž�����
	@RequestMapping(value="/manager/notice/new",method=RequestMethod.GET)
	 public String addnotice(Model model){
		System.out.println("��ת���½�����ҳ�棡");
		return "/manager/notice/new";
	}
	@RequestMapping(value="/manager/notice/new",method=RequestMethod.POST)
	 public String addnoticeinfo(Model model,@ModelAttribute Notice notice,HttpServletRequest request){
		System.out.println("���д������棡");
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		Timestamp date = new Timestamp(System.currentTimeMillis());  
		notice.setId(manager_id);
		notice.setDate(date);
		travelservice.new_notice(notice);
		return "/manager/notice/search";//���ز鿴���й���
	}
	
	//�޸��Ž�����
	@RequestMapping(value="/manager/notice/update",method=RequestMethod.GET)
	 public String updatenotice(Model model,String id,Timestamp date){
		Notice notice = travelservice.get_notice(id,date);
		model.addAttribute("notice",notice);
		return "/manager/notice/update";//��ת��updateҳ��
	}
	@RequestMapping(value="/manager/notice/update",method=RequestMethod.POST)
	 public String updatenoticeinfo(Model model,@ModelAttribute Notice notice,HttpServletRequest request,String id,Timestamp date){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		Notice oldnotice = travelservice.get_notice(id,date);
		System.out.println("ɾ���ɵĹ���");
		travelservice.delete_notice(id,date);
		Timestamp newdate = new Timestamp(System.currentTimeMillis()); 
		notice.setId(id);
		notice.setDate(newdate);
		travelservice.new_notice(notice);
		return "/manager/notice/search";//���ز鿴���й���
	}
	
	//ɾ���Ž�����
	@RequestMapping(value="/manager/notice/delete",method=RequestMethod.GET)
	 public String deletenotice(Model model,String id,Timestamp date){
		System.out.println(id);
		System.out.println(date);
	    travelservice.delete_notice(id, date);
		System.out.println("ɾ���ɵĹ���");
		return "/manager/notice/search";
	}
	
	//�鿴�Լ����е��Ž�����
	@RequestMapping(value="/manager/notice/search",method=RequestMethod.GET)
	 public String notice(Model model,HttpServletRequest request){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		List<Notice> noticelist=travelservice.get_notice_list(manager_id);
		System.out.println("�鿴���й���");
		model.addAttribute("noticelist", noticelist);
		return "/manager/notice/search";
	}
	
	//�޸ľ���ĸ�����Ϣmanagerid�����޸ģ�ǰ�˿�����ʾ������׼�޸�
	@RequestMapping(value="/manager/update/password",method=RequestMethod.GET)
	public String updatemanagerinfo(Model model,HttpServletRequest request){
        System.out.println("�޸ľ�����Ϣ");
        Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
        String id=manager.getId();
        manager=travelservice.get_manager(id);
	    model.addAttribute("manager", manager);
		return "/manager/update/password";
	}
	@RequestMapping(value="/manager/update/password",method=RequestMethod.POST)
	public String addmanagerinfo(Model model,@ModelAttribute Manager manager,HttpServletRequest request,String id){
		manager.setId(id);
		travelservice.update_manager(manager);
		return "/manager/update/password";
		
	
	}
	
}
