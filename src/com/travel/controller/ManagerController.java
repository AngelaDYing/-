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
	//创建团建项目
	@RequestMapping(value="/manager/activity/new",method=RequestMethod.GET)
	 public String add(Model model){
		System.out.println("经理创建团建投票！");
		return "/manager/activity/new";//创建团建JSP
	}
	@RequestMapping(value="/manager/activity/new",method=RequestMethod.POST)
	 public String add(Model mv,@ModelAttribute VoteContent votecontent,HttpServletRequest request){
		System.out.println("团建投票表单传递成功！");
		if(votecontent==null) {
			System.out.println("votecontent为null");
		}
		Vote_start vote_start=votecontent.getVote_start();
		if(vote_start==null) {
			System.out.println("vote_start为null");
			//System.out.println(votecontent.getVote_start());
		}
		List<Vote> votes=votecontent.getVotes();
		if(votes==null) {
			System.out.println("votes为null");
		}
		//Timestamp date = new Timestamp(System.currentTimeMillis()); 
		Date d = new Date();       
		Timestamp date = new Timestamp(d.getTime());
		
		if(vote_start!=null) {
		vote_start.setDate(date);
		}
		vote_start.setStatus(1);
		System.out.println("设置团建时间成功");
	    Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
	    String manager_id=manager.getId();
	    System.out.println(manager_id);
	    vote_start.setManager_id(manager_id);
		travelservice.new_vote_start(vote_start);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		vote_start = travelservice.get_vote_start(manager_id,Timestamp.valueOf(sdf.format(date)));
		System.out.println(date);
		if(vote_start==null) {
			System.out.println("vote_start为null");
		}
		int vote_start_id = vote_start.getId();
		for (Vote v : votes) {
		   if(v!=null) {
		       v.setSum(0);
		       v.setVote_start_id(vote_start_id);
               travelservice.new_vote(v);
               System.out.println("成功添加一个投票选项!");
		   }
        }
		//mv.setViewName("redirect:/manager/activity/search");//创建成功后跳转到所有的团建项目
		return "/manager/activity/search";
	}
	
	//查看历史团建项目列表
	@RequestMapping(value="/manager/activity/search",method=RequestMethod.GET)
	 public String get_vote_start_list(Model model,HttpServletRequest request){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		List<Vote_start> vote_start_list = travelservice.get_vote_start_list(manager_id);
		model.addAttribute("vote_start_list",vote_start_list);
		return "/manager/activity/search";
	}
	
	//查看单个投票项目的结果。即查看详情
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
	
	//修改单个团建项目
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
		System.out.println("转入修改投票界面");
		return "/manager/activity/update";
	}
	
	@RequestMapping(value="/manager/activity/update",method=RequestMethod.POST)
	 public ModelAndView update_votecontent(ModelAndView mv,@ModelAttribute VoteContent votecontent,@RequestParam(value="id") int id){
		System.out.println("提交修改团建表单！");
		List<Vote> votes=votecontent.getVotes();
		Vote_start vote_start=votecontent.getVote_start();
		if (votes==null) {
			System.out.println("没有votes");
		}
		if (vote_start==null) {
			System.out.println("没有votestart");
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
	               System.out.println("成功更新一个投票选项!");
			   }
	        }
		mv.setViewName("redirect:/manager/activity/search");
		return mv;
	}
	
	
	//删除单个团建项目
	@RequestMapping(value="/manager/activity/delete",method=RequestMethod.GET)
	 public void delete_vote(int id){
		System.out.println(id);
		System.out.println("删除团建项目！");
		if(Integer.toString(id)!=null){
			
			travelservice.delete_vote(id);
			travelservice.delete_vote_start(id);
			
		}
	}
	
	//发布团建公告
	@RequestMapping(value="/manager/notice/new",method=RequestMethod.GET)
	 public String addnotice(Model model){
		System.out.println("跳转到新建公告页面！");
		return "/manager/notice/new";
	}
	@RequestMapping(value="/manager/notice/new",method=RequestMethod.POST)
	 public String addnoticeinfo(Model model,@ModelAttribute Notice notice,HttpServletRequest request){
		System.out.println("进行创建公告！");
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		Timestamp date = new Timestamp(System.currentTimeMillis());  
		notice.setId(manager_id);
		notice.setDate(date);
		travelservice.new_notice(notice);
		return "/manager/notice/search";//返回查看所有公告
	}
	
	//修改团建公告
	@RequestMapping(value="/manager/notice/update",method=RequestMethod.GET)
	 public String updatenotice(Model model,String id,Timestamp date){
		Notice notice = travelservice.get_notice(id,date);
		model.addAttribute("notice",notice);
		return "/manager/notice/update";//跳转到update页面
	}
	@RequestMapping(value="/manager/notice/update",method=RequestMethod.POST)
	 public String updatenoticeinfo(Model model,@ModelAttribute Notice notice,HttpServletRequest request,String id,Timestamp date){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		Notice oldnotice = travelservice.get_notice(id,date);
		System.out.println("删除旧的公告");
		travelservice.delete_notice(id,date);
		Timestamp newdate = new Timestamp(System.currentTimeMillis()); 
		notice.setId(id);
		notice.setDate(newdate);
		travelservice.new_notice(notice);
		return "/manager/notice/search";//返回查看所有公告
	}
	
	//删除团建公告
	@RequestMapping(value="/manager/notice/delete",method=RequestMethod.GET)
	 public String deletenotice(Model model,String id,Timestamp date){
		System.out.println(id);
		System.out.println(date);
	    travelservice.delete_notice(id, date);
		System.out.println("删除旧的公告");
		return "/manager/notice/search";
	}
	
	//查看自己所有的团建公告
	@RequestMapping(value="/manager/notice/search",method=RequestMethod.GET)
	 public String notice(Model model,HttpServletRequest request){
		Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		String manager_id=manager.getId();
		List<Notice> noticelist=travelservice.get_notice_list(manager_id);
		System.out.println("查看所有公告");
		model.addAttribute("noticelist", noticelist);
		return "/manager/notice/search";
	}
	
	//修改经理的个人信息managerid不能修改，前端可以显示，但不准修改
	@RequestMapping(value="/manager/update/password",method=RequestMethod.GET)
	public String updatemanagerinfo(Model model,HttpServletRequest request){
        System.out.println("修改经理信息");
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
