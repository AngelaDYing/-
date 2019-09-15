package com.travel.controller;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Business;
import com.travel.domain.Expense;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class BusinessController {
	//员工新增出差申请表
	@Autowired 
	@Qualifier("TravelService")
	private TravelService travelservice;
	
	//新增申请表,job是前端传过来的表单
	@RequestMapping(value = "/staff/business/add", method = RequestMethod.GET)
	public String add_business(Model model) {
		System.out.println("员工创建出差申请！");
		return "/staff/business/add";
	}
	
	@RequestMapping(value = "/staff/business/add", method = RequestMethod.POST)
	public ModelAndView add_business_info(ModelAndView model, @ModelAttribute Business job, 
			HttpSession session,HttpServletRequest request) {
		//从session拿到用户的Id。
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		System.out.println("1111");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		job.setId(staff_id);
		job.setDate(date);
		int status= 0;
		job.setStatus(status);
		travelservice.insert_Business(job);
		//return "/staff/business/nowlist";
		model.setViewName("redirect:/staff/business/nowlist");
		return model;
	}
	
	//查找某个员工申请中的申请表（这部分申请表是可以修改的）	
	@RequestMapping(value = "/staff/business/nowlist", method = RequestMethod.GET)
	public String now_business(Model model,HttpSession session,HttpServletRequest request) {
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		//选出id等于session的id的申请表内容
		List<Business> business_list = travelservice.get_Business_nowList(staff_id);
		for(Business s:business_list) {
			System.out.println(s.getDays());
		}
		model.addAttribute("business_list", business_list);
		return "/staff/business/nowlist";
	} 
	
	//查找某个员工已批准或不批准的申请表（这部分申请表是无法修改的）	
	@RequestMapping(value = "/staff/business/pastlist", method = RequestMethod.GET)
	public String past_business(Model model,HttpSession session,HttpServletRequest request) {
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		//选出id等于session的id的申请表内容
		List<Business> business_list = travelservice.get_Business_pastList(staff_id);
		for(Business s:business_list) {
			System.out.println(s.getDays());
		}
		model.addAttribute("business_list", business_list);
		return "/staff/business/pastlist";
	} 
	 
	//员工删除某个申请表
		//点击删除按钮就会进行删除操作,date和员工id作为主键
		@RequestMapping(value = "/staff/business/delete", method = RequestMethod.GET)
		public String delete_business(Model model,HttpSession session,HttpServletRequest request,Timestamp date) {
			Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
			
			String staff_id=staff.getId();
			if(date!=null) {
				travelservice.delete_Business(staff_id,date);
			}
			return "/staff/business/nowlist";
		}
		
	
		//员工修改某个申请表
		//先显示这个申请表的内容
		
		@RequestMapping(value = "/staff/business/myupdate", method = RequestMethod.GET)
		public String update_business(Model model, HttpSession session,Timestamp date,HttpServletRequest request) {
			Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
			String staff_id=staff.getId();
			//选出id等于session的id的申请表内容
			System.out.println("123456");
			Business business = travelservice.get_Business_LikeList(staff_id,date);		
			model.addAttribute("business_trip", business);
			travelservice.delete_Business(staff_id,date);
			return "/staff/business/myupdate";
		}
		
		
		//然后将修改后的内容更新到数据库
		@RequestMapping(value = "/staff/business/myupdate", method = RequestMethod.POST)
		public ModelAndView update_expense(ModelAndView model,@ModelAttribute Business job,HttpServletRequest request)
				throws Exception{	
			Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
			String staff_id=staff.getId();
			//travelservice.delete_Business(staff_id,date);
			job.setId(staff_id);
			Timestamp newdate = new Timestamp(System.currentTimeMillis());
			job.setDate(newdate);
			int status= 0;
			job.setStatus(status);
			travelservice.insert_Business(job);
			model.setViewName("redirect:/staff/business/nowlist");
			return model;
		}

	
	//经理查看该部门员工的全部申请表
	@RequestMapping(value = "/manager/business/alllist", method = RequestMethod.GET)
	public String get_apartment_business(Model model,HttpSession session,HttpServletRequest request) {
		//需得先找到manager的id，然后再到员工里面匹配对应的部门id
		//Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
		//String manager_id=manager.getId();
		//根据查询语句，找manager_id与该登录的经理id一致的部门id	
		//Apartment apartment=travelservice.get_apartment_manager(manager_id);
		//String apartment_id=apartment.getId();
		//List<Business> business_list=travelservice.apartment_id_business(apartment_id);
		List<Business> business_list=travelservice.get_All_Business();
		model.addAttribute("business_list", business_list);
		return "/manager/business/alllist";
	}
	
	//经理查看该部门员工的未被批准的申请表
		@RequestMapping(value = "/manager/business/nowlist", method = RequestMethod.GET)
		public String get_apartment_nowbusiness(Model model,HttpSession session,HttpServletRequest request) {
			//需得先找到manager的id，然后再到员工里面匹配对应的部门id
			//Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			//String manager_id=manager.getId();
			//根据查询语句，找manager_id与该登录的经理id一致的部门id	
			//Apartment apartment=travelservice.get_apartment_manager(manager_id);
			//String apartment_id=apartment.getId();
			//System.out.println(apartment_id);
			List<Business> business_list=travelservice.get_Allnow_Business();
			for(Business s:business_list) {
				System.out.println(s.getDays());
			}
			
			
			model.addAttribute("business_list",business_list);
			/*
			List<Business> business_list=travelservice.apartment_id_nowbusiness(apartment_id);
			model.addAttribute("business_list", business_list);
		*/	
			return "/manager/business/nowlist";
		}
	
	
	//经理修改申请表状态
	
		@RequestMapping(value = "/manager/business/myupdate", method = RequestMethod.GET)
		public ModelAndView update_expense_status( ModelAndView model,String id,Timestamp date, int status){	
			Business business = travelservice.get_Business_LikeList(id,date);	
			System.out.println(date);
			business.setStatus(status);
			travelservice.update_Business_status(business);
			model.setViewName("redirect:/manager/business/nowlist");
			return model;
		}
}
	
