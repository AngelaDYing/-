package com.travel.controller;

import java.util.List;

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
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class LoginContoller {
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
    //退出功能
	@RequestMapping(value="/user/logout")
	 public ModelAndView logout(ModelAndView mv, HttpSession session){
		session.setAttribute(Constants.USER_SESSION, null);
		session.setAttribute("tag", null);
		mv.setViewName("redirect:/index");
		
		return mv;
	}
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("id") String id,
			 @RequestParam("password") String password,@RequestParam("tag") String tag,
			 HttpSession session,
			 ModelAndView mv){
		// 调用业务逻辑组件判断用户是否可以登录
		boolean flag = false;
		if("1".equals(tag)) {//管理员登陆条件判断
			Admin admin = travelservice.admin_login(id, password);
			if(admin!=null){
				// 将用户保存到HttpSession当中
				System.out.println("HttpSession管理员");
				session.setAttribute(Constants.USER_SESSION, admin);
				session.setAttribute("tag", "1");
				// 客户端跳转到main页面
				mv.setViewName("redirect:/adminindex");//管理员总界面
			}else{
				// 设置登录失败提示信息
				System.out.println("设置登录失败提示信息");
				mv.addObject("message", "账号或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
		}
		else if("2".equals(tag)){//经理登陆条件判断
			Manager manager = travelservice.manager_login(id, password);
			if(manager!=null){
				// 将用户保存到HttpSession当中
				System.out.println("HttpSession经理");
				session.setAttribute(Constants.USER_SESSION, manager);
				session.setAttribute("tag", "2");
				// 客户端跳转到main页面
				mv.setViewName("redirect:/managerindex");//经理总界面
			}else{
				// 设置登录失败提示信息
				System.out.println("设置登录失败提示信息");
				mv.addObject("message", "账号或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
		}
		else if("3".equals(tag)){//财务登陆条件判断
			Finance finance = travelservice.finance_login(id, password);
			if(finance!=null){
				// 将用户保存到HttpSession当中
				System.out.println("HttpSession财务");
				session.setAttribute(Constants.USER_SESSION, finance);
				session.setAttribute("tag", "3");
				// 客户端跳转到main页面
				mv.setViewName("redirect:/financeindex");//财务总界面
			}else{
				// 设置登录失败提示信息
				System.out.println("设置登录失败提示信息");
				mv.addObject("message", "账号或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
		}
		else {//普通员工判断登陆
			Staff staff = travelservice.staff_login(id, password);
			if(staff!=null){
				// 将用户保存到HttpSession当中
				System.out.println("HttpSession普通员工");
				session.setAttribute(Constants.USER_SESSION, staff);
				session.setAttribute("tag", "4");
				// 客户端跳转到main页面
				mv.setViewName("redirect:/staffindex/");//普通用户总页面
			}else{
				// 设置登录失败提示信息
				System.out.println("设置登录失败提示信息");
				mv.addObject("message", "账号或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
			
		}
		return mv;
	}
}
