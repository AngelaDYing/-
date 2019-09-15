package com.travel.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.web.bind.annotation.PathVariable;
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
import com.travel.domain.Staff;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Expense;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class ExpenseController {
	//员工新增出差报销表
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
	//新增申请表,job是前端传过来的表单
	@RequestMapping(value = "/staff/expense/add", method = RequestMethod.GET)
	public String add_expense(Model model)
	{
		System.out.println("员工创建报销申请！");
		return "/staff/expense/add";
		
	}
	@RequestMapping(value = "/staff/expense/add", method = RequestMethod.POST)
	public String add_expense_info(ModelAndView model, @ModelAttribute Expense job, HttpSession session,HttpServletRequest request )
			throws Exception
	{
		//从session拿到用户的Id。
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		job.setId(staff_id);
		job.setDate(date);
		int status= 0;
		job.setStatus(status);
		
		//获得物理路径所在路径
		
		//String path = "D:\\Angela\\生产实习\\CompanyTravelSystem\\WebContent\\public\\images\\";
		String filename = job.getFile().getOriginalFilename();
		File tempFile = new File(filename);
		 
		job.getFile().transferTo(tempFile);
		job.setPicture(filename);
		//job.setPicture(path+filename);
		travelservice.insert_Expense(job);
		return "/staff/expense/nowlist";
	
	}
	
	

	//查找某个员工申请中的申请表（这部分申请表是可以修改的）	
	@RequestMapping(value = "/staff/expense/nowlist", method = RequestMethod.GET)
	public String now_expense(Model model,HttpSession session,HttpServletRequest request) {
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		//选出id等于session的id的申请表内容
		List<Expense> expense_list = travelservice.get_Expense_nowList(staff_id);		
		model.addAttribute("expense_list", expense_list);
		return "/staff/expense/nowlist";
	} 
	
	

	//查找某个员工已批准或不批准的申请表（这部分申请表是无法修改的）	
	@RequestMapping(value = "/staff/expense/pastlist", method = RequestMethod.GET)
	public String past_expense(Model model,HttpSession session,HttpServletRequest request) {
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		//选出id等于session的id的申请表内容
		List<Expense> expense_list = travelservice.get_Expense_pastList(staff_id);		
		model.addAttribute("expense_list", expense_list);
		return "/staff/expense/pastlist";
	} 
	
	//员工删除某个申请表
	//点击删除按钮就会进行删除操作,date作为主键
	@RequestMapping(value = "/staff/expense/delete", method = RequestMethod.GET)
	public String delete_expense(Model model,HttpSession session,HttpServletRequest request,Timestamp date) {
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		if (date != null) {
			travelservice.delete_Expense(staff_id,date);
		}
		return "/staff/expense/nowlist";
	}
	
	//员工修改某个申请表
	//先显示这个申请表的内容
	@RequestMapping(value = "/staff/expense/myupdate", method = RequestMethod.GET)
	public String update_expense(Model model, HttpSession session,Timestamp date,HttpServletRequest request)
			{	
		Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
		String staff_id=staff.getId();
		//选出id等于session的id的申请表内容
		Expense expense = travelservice.get_Expense_LikeList(staff_id,date);		
		model.addAttribute("expense", expense);
		return "/staff/expense/myupdate";
	}
	
	//然后将修改后的内容更新到数据库
		@RequestMapping(value = "/staff/expense/myupdate", method = RequestMethod.POST)
		public String update_expense( Model model,@ModelAttribute Expense job,HttpServletRequest request,Timestamp date)
				throws Exception{	
			Staff staff=(Staff)request.getSession().getAttribute(Constants.USER_SESSION);
			String staff_id=staff.getId();
			System.out.println(date);
			travelservice.delete_Expense(staff_id,date);
			job.setId(staff_id);
			Timestamp newdate = new Timestamp(System.currentTimeMillis());
			job.setDate(newdate);
			int status= 0;
			job.setStatus(status);
			
			//获得物理路径所在路径
			
			//String path = "D:\\Angela\\生产实习\\CompanyTravelSystem\\WebContent\\public\\images\\";
			String filename = job.getFile().getOriginalFilename();
			File tempFile = new File(filename);
			 
			job.getFile().transferTo(tempFile);
			job.setPicture(filename);
			//job.setPicture(path+filename);
			travelservice.insert_Expense(job);
			return "/staff/expense/nowlist";
		}
		
		
		
	
//经理创建
		@RequestMapping(value = "/manager/expense/add", method = RequestMethod.GET)
		public String add_expense_manager(Model model)
		{
			System.out.println("经理创建报销申请！");
			return "/manager/expense/add";
		}
		
		@RequestMapping(value = "/manager/expense/add", method = RequestMethod.POST)
		public String add_expense_manager_info(ModelAndView model, @ModelAttribute Expense job, HttpSession session,HttpServletRequest request)
				throws Exception
		{
			//从session拿到用户的Id。
			Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			String manager_id=manager.getId();
			Timestamp date = new Timestamp(System.currentTimeMillis());
			job.setId(manager_id);
			job.setDate(date);
			int status= 0;
			job.setStatus(status);
			//获得物理路径所在路径
			//String path = "D:\\Angela\\生产实习\\CompanyTravelSystem\\WebContent\\public\\images\\";
			String filename = job.getFile().getOriginalFilename();
			File tempFile = new File(filename);
			 
			job.getFile().transferTo(tempFile);
			job.setPicture(filename);
			//job.setPicture(path+filename);
			travelservice.insert_Expense(job);
			
			
			return "/manager/expense/nowlist";
		}
		
		

		//查找某个申请中的申请表（这部分申请表是可以修改的）	
		@RequestMapping(value = "/manager/expense/nowlist", method = RequestMethod.GET)
		public String now_expense_manager(Model model,HttpSession session,HttpServletRequest request) {
			Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			String manager_id=manager.getId();
			//选出id等于session的id的申请表内容
			List<Expense> expense_list = travelservice.get_Expense_nowList(manager_id);		
			model.addAttribute("expense_list", expense_list);
			return "/manager/expense/nowlist";
		} 
		
		

		//查找某个财务已批准或不批准的申请表（这部分申请表是无法修改的）	
		@RequestMapping(value = "/manager/expense/pastlist", method = RequestMethod.GET)
		public String past_expense_manager(Model model,HttpSession session,HttpServletRequest request) {
			Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			String manager_id=manager.getId();
			//选出id等于session的id的申请表内容
			List<Expense> expense_list = travelservice.get_Expense_pastList(manager_id);		
			model.addAttribute("expense_list", expense_list);
			return "/manager/expense/pastlist";
		} 
		
		//经理删除某个申请表
		//点击删除按钮就会进行删除操作,date作为主键
		@RequestMapping(value = "/manager/expense/delete", method = RequestMethod.GET)
		public String delete_expense_manager(Model model,HttpSession session,HttpServletRequest request,Timestamp date) {
			Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			String manager_id=manager.getId();
			
			if (date != null) {
				travelservice.delete_Expense(manager_id,date);
			}
			return "/manager/expense/nowlist";
		}
		
		//经理修改某个申请表
		//先显示这个申请表的内容
		@RequestMapping(value = "/manager/expense/myupdate", method = RequestMethod.GET)
		public String update_expense_manager(Model model, HttpSession session,Timestamp date,HttpServletRequest request){	
			Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
			String manager_id=manager.getId();
			//选出id等于session的id的申请表内容
			Expense expense = travelservice.get_Expense_LikeList(manager_id,date);		
			model.addAttribute("expense", expense);
			return "/manager/expense/myupdate";
		}
		
		//然后将修改后的内容更新到数据库
			@RequestMapping(value = "/manager/expense/myupdate", method = RequestMethod.POST)
			public String update_expense_manager(Model model,@ModelAttribute Expense job,HttpServletRequest request,Timestamp date)
					throws Exception{	
				Manager manager=(Manager)request.getSession().getAttribute(Constants.USER_SESSION);
				String manager_id=manager.getId();
				System.out.println(date);
				travelservice.delete_Expense(manager_id,date);
				job.setId(manager_id);
				Timestamp newdate = new Timestamp(System.currentTimeMillis());
				job.setDate(newdate);
				int status= 0;
				job.setStatus(status);
				
				//获得物理路径所在路径
				
				//String path = "D:\\Angela\\生产实习\\CompanyTravelSystem\\WebContent\\public\\images\\";
				String filename = job.getFile().getOriginalFilename();
				File tempFile = new File(filename);
				 
				job.getFile().transferTo(tempFile);
				job.setPicture(filename);
				//job.setPicture(path+filename);
				travelservice.insert_Expense(job);
				return "/manager/expense/nowlist";
			}
		
			
					
	//财务查看全部申请表
	@RequestMapping(value = "/finance/expense/pastlist", method = RequestMethod.GET)
	public String past_list(Model model) {
		List<Expense> expense_list = travelservice.get_All_Expense();
		model.addAttribute("list", expense_list);
		return "/finance/expense/pastlist";
	}
	
	//财务查看未批准申请表
		@RequestMapping(value = "/finance/expense/nowlist", method = RequestMethod.GET)
		public String now_list(Model model) {
			List<Expense> expense_list = travelservice.get_now_List();
			model.addAttribute("list", expense_list);
			return "/finance/expense/nowlist";
		}
	
	//财务修改申请表状态
	/*@RequestMapping(value = "/finance/expense/myupdate", method = RequestMethod.GET)
	public String update_expense_status(Model model){	
		List<Expense> expense_list = travelservice.get_now_List();
		model.addAttribute("list", expense_list);
		return "/finance/expense/nowlist";
	}*/
	
	@RequestMapping(value = "/finance/expense/myupdate", method = RequestMethod.GET)
	public ModelAndView update_expense_status( ModelAndView model,String id,Timestamp date, int status){	
		Expense expense = travelservice.get_Expense_LikeList(id,date);	
		System.out.println(date);
		expense.setStatus(status);
		travelservice.update_Expense_status(expense);
		model.setViewName("redirect:/finance/expense/pastlist");
		return model;
	}
	//查看报销单详情
	@RequestMapping(value="/finance/expense/detail",method=RequestMethod.GET)
	 public String get_detail(Model model,String id,Timestamp date){

	    Expense expense = travelservice.get_Expense_LikeList(id,date);
	model.addAttribute("expense",expense);

	return "/finance/expense/detail";
	} 

}

