package com.travel.controller;


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
import com.travel.domain.Notice;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
	
	    //新建普通员工账号
		@RequestMapping(value="/admin/staff_account/add",method=RequestMethod.GET)
		 public String addstaff(Model model){
			List<Apartment> apartmentlist=travelservice.get_apartment_list();
			model.addAttribute("apartmentlist", apartmentlist);
			return "/admin/staff_account/add";
		}
		@RequestMapping(value="/admin/staff_account/add",method=RequestMethod.POST)
		 public String addstaffinfo(Model model,@ModelAttribute Staff staff){
			System.out.println(staff.getId());
			travelservice.insert_staff(staff);
			return "/admin/staff_account/delete";
		}
		
		//修改普通员工账号
		@RequestMapping(value="/admin/staff_account/update",method=RequestMethod.GET)
		 public String updatestaff(Model model,String id){
			Staff oldstaff=travelservice.get_staff(id);
			model.addAttribute("oldadmin", oldstaff);
			return "/admin/staff_account/update";
		}
		@RequestMapping(value="/admin/staff_account/update",method=RequestMethod.POST)
		 public String updatestaffinfo(Model model,String id,@ModelAttribute Staff staff){
			staff.setId(id);
			travelservice.update_staff(staff);
			return "/admin/staff_account/update";
		}
		//删除普通员工账号
		@RequestMapping(value="/admin/staff_account/delete",method=RequestMethod.GET)
		 public String deletestaff(Model model,String id){
			List<Staff> stafflist=travelservice.get_all_staff_list();
			model.addAttribute("stafflist", stafflist);
			for(Staff s:stafflist) {
				System.out.println(s.getBirthday());
			}
			if(id!=null) {
				travelservice.delete_staff(id);
			}
			return "/admin/staff_account/delete";
		}
//		@RequestMapping(value="/admin/staff_account/delete",method=RequestMethod.POST)
//		 public String deletestaffinfo(Model model,String id){
//			travelservice.delete_staff(id);
//			return "/admin/staff_account/delete";
//		}		
	   
		//新建经理账号
		@RequestMapping(value="/admin/manager_account/add",method=RequestMethod.GET)
		public String managerstaff(Model model){
			List<Apartment> apartmentlist=travelservice.get_apartment_list();
			model.addAttribute("apartmentlist", apartmentlist);
			return "/admin/manager_account/add";
		}
		@RequestMapping(value="/admin/manager_account/add",method=RequestMethod.POST)
		public String addmanagerinfo(Model model,@ModelAttribute Manager manager){
			System.out.println(manager.getId());
			travelservice.insert_manager(manager);
			return "/admin/manager_account/delete";
		}
		//修改经理账号
		@RequestMapping(value="/admin/manager_account/update",method=RequestMethod.GET)
		 public String updatemanager(Model model,String id){
			return "/admin/manager_account/update";
		}
		@RequestMapping(value="/admin/manager_account/update",method=RequestMethod.POST)
		 public String updatemanagerinfo(Model model,String id,@ModelAttribute Manager manager){
			manager.setId(id);
			travelservice.update_manager(manager);
			return "/admin/manager_account/update";
		}
		//删除经理账号
		@RequestMapping(value="/admin/manager_account/delete",method=RequestMethod.GET)
		 public String deletemanager(Model model,String id){
			List<Manager> managerlist=travelservice.get_all_manager_list();
			model.addAttribute("managerlist", managerlist);
			if(id!=null) {
				System.out.println("开始数据库删除！");
				travelservice.delete_manager(id);
				System.out.println("删除成功");
			}
			return "/admin/manager_account/delete";
		}
//		@RequestMapping(value="/admin/manager_account/delete",method=RequestMethod.GET)
//		 public String deletemanagerinfo(Model model,String id){
//			travelservice.delete_manager(id);
//			return "/admin/manager_account/delete";
//		}		
		
		
		
		
		//新建财务账号
		@RequestMapping(value="/admin/finance_account/add",method=RequestMethod.GET)
		public String addfinance(Model model){
			return "/admin/finance_account/add";
		}
		@RequestMapping(value="/admin/finance_account/add",method=RequestMethod.POST)
		public String addfinanceinfo(Model model,@ModelAttribute Finance finance){
			travelservice.insert_finance(finance);
			return "/admin/finance_account/delete";
		}
		//修改财务帐号
		@RequestMapping(value="/admin/finance_account/update",method=RequestMethod.GET)
		 public String updatefianance(Model model,String id){
			return "/admin/finance_account/update";
		}
		@RequestMapping(value="/admin/finance_account/update",method=RequestMethod.POST)
		 public String updatefianaceinfo(Model model,String id,@ModelAttribute Finance finance){
			finance.setId(id);
			travelservice.update_finance(finance);
			return "/admin/finanace_account/delete";
		}
		//删除财务账号
		@RequestMapping(value="/admin/finance_account/delete",method=RequestMethod.GET)
		 public String deletefinance(Model model,String id){
			List<Finance> financelist=travelservice.get_all_finance_list();
			model.addAttribute("financelist", financelist);
			if(id!=null) {
				travelservice.delete_finance(id);
			}
			return "/admin/finance_account/delete";
		}
//		@RequestMapping(value="/admin/finance_account/delete",method=RequestMethod.POST)
//		 public String deletefinanceinfo(Model model,String id){
//			travelservice.delete_finance(id);
//			return "/admin/finance_account/delete";
//		}	
		
		
		
		//增加管理员账户
		@RequestMapping(value="/admin/admin_account/add",method=RequestMethod.GET)
		public String addadmin(Model model){
			return "/admin/admin_account/add";
		}
		@RequestMapping(value="/admin/admin_account/add",method=RequestMethod.POST)
		public String addadmininfo(Model model,@ModelAttribute Admin admin){
			travelservice.insert_admin(admin);
			return "/admin/admin_account/delete";
		}		
		//删除管理员账户
		@RequestMapping(value="/admin/admin_account/delete",method=RequestMethod.GET)
		 public String deleteadmin(Model model,String id){
			List<Admin> adminlist=travelservice.get_all_admin_list();
			if(id!=null) {
				travelservice.delete_admin(id);
			}
			model.addAttribute("adminlist",adminlist);
			return "/admin/admin_account/delete";
			
		}
//		@RequestMapping(value="/admin/admin_account/delete",method=RequestMethod.POST)
//		 public String deleteadmininfo(Model model,String id){
//			travelservice.delete_admin(id);
//			return "/admin/admin_account/delete";
//		}
		//修改管理员账户
		@RequestMapping(value="/admin/update",method=RequestMethod.GET)
		 public String updateadmin(Model model,HttpServletRequest request){
			Admin admin=(Admin)request.getSession().getAttribute(Constants.USER_SESSION);
			String id=admin.getId();
			admin=travelservice.get_admin(id);
			model.addAttribute("admin", admin);
			return "/admin/update";
		}
		@RequestMapping(value="/admin/update",method=RequestMethod.POST)
		 public String updateadmininfo(Model model,String id,@ModelAttribute Admin admin){
			admin.setId(id);
			//travelservice.update_admin(admin);
			
			travelservice.delete_admin(id);
			travelservice.insert_admin(admin);
			return "/admin/update";
			
		}				
}
